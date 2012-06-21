#!/bin/sh

# Run hsqldb in background
mvn -P hsqldb exec:java &
# and save its PID
hsqldb_pid=$!
# Wait for hsqldb to be up
until netcat 127.0.0.1 9001; do sleep .1; done
# Then run the integration tests
mvn integration-test -P jetty-hsqldb-integration-test "$@"
# And finally, close gracefully (I swear) hsqldb.
kill $hsqldb_pid
