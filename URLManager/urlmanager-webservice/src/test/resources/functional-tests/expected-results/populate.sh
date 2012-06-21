#!/bin/bash

URL="http://localhost:${1:-8084}/urlmanager/rest"
FORMATS=( "xml;application/xml" "json;application/json" "html;text/html" )

docurl() {
    declare output=$1
    declare entity=$2
    declare operation=$3
    declare id=$4
    
    [[ -z "$id" ]] || declare argument="?id=$id"
    result=$(
    for format in ${FORMATS[@]}; do
	declare ext=${format%;*}
	declare mime=${format#*;}
	declare outputext="$output.$ext"

	curl -f -H "Accept: $mime" \
	    "$URL/$entity/$operation$argument" \
	    2>/dev/null >"$outputext"
	[[ $? -ne 0 ]] && echo fail && rm "$outputext" && break
    done)

    [[ "$result" = "fail" ]] && return 1
    return 0
}

for entity in $(ls); do
    [[ -d "$entity" ]] || continue
    for operation in $(ls "$entity"); do
	[[ -d "$entity/$operation" ]] || continue
	if [[ "$operation" = "list" ]]; then
	    docurl "$entity/$operation/list" "$entity" "$operation"
	else
	    declare i=1;

	    declare nb_entities=$(
	    while true; do
		declare output="$entity/$operation/$i"
		docurl "$output" "$entity" "$operation" "$i"
		[[ $? -ne 0 ]] && echo $i && break
		i=$(($i+1))
	    done)
	    nb_entities=$(($nb_entities-1))
	    echo "$entity/$operation : $nb_entities fetched"
	fi
    done
done
