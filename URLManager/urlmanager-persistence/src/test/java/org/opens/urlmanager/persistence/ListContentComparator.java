/*
 * URLManager - URL Indexer
 * Copyright (C) 2008-2012  Open-S Company
 *
 * This file is part of URLManager.
 *
 * URLManager is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.urlmanager.persistence;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author bcareil
 */
public class ListContentComparator<T> {
    
    /**
     * Compare two collection using the given comparator
     * 
     * First of all, this method compare the size of the Collections.
     * If there are a difference, it is returned as `o1.size() - o2.size`
     * If not, the collections are sort using the given comparator and
     * elements are compared one by one. If the comparator return non zero,
     * this value is immediatly returned.
     * Finaly, if the given comparator doesnt notice any difference, zero
     * is returned.
     * 
     * NOTE: since all entities have overriden equals method, we might not
     *       use this class anymore and replace its usages.
     * 
     * Complexity : depending of the sort algorithm (lets assume O(n * log(n)))
     *              plus O(n) for the one by one comparaison process.
     * 
     * @param o1
     * @param o2
     * @param c
     * @return The size difference if any,
     *         0 if collections are the same, according the comparator,
     *         The 
     */
    public int compare(List<T> o1, List<T> o2, Comparator<? super T> c) {
        int diffSize;
        
        // check for size differences
        diffSize = o1.size() - o2.size();
        if (diffSize != 0) {
            return diffSize;
        }
        // sort collections
        Collections.sort(o1, c);
        Collections.sort(o2, c);
        // compare each elements one by one
        Iterator it1 = o1.iterator();
        Iterator it2 = o2.iterator();
        
        while (it1.hasNext()) {
            int diff;
            T t1 = (T) it1.next();
            T t2 = (T) it2.next();
            
            diff = c.compare(t1, t2);
            if (diff != 0) {
                return diff;
            }
        }
        return 0;
    }
    
}
