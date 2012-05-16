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
package org.opens.urlmanager.entity.request;

import junit.framework.TestCase;

/**
 *
 * @author bcareil
 */
public class RequestImplTest extends TestCase {
    
    public RequestImplTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of equals method, of class RequestImpl.
     */
    public void testEquals() {
        System.out.println("equals");
        RequestImpl instance = new RequestImpl(1L, "lol related");
        RequestImpl other;
        boolean result;
        
        // check that only the label is tested
        other = new RequestImpl(0L, "lol related");
        result = instance.equals(other);
        assertTrue(result);

        // trivial test
        other = new RequestImpl(0L, "LOL");
        result = instance.equals(other);
        assertFalse(result);

        // trivial test : label null
        other = new RequestImpl(null, null);
        result = instance.equals(other);
        assertFalse(result);

        // trivial test
        other = null;
        result = instance.equals(other);
        assertFalse(result);
    }

    /**
     * Test of hashCode method, of class RequestImpl.
     */
    public void testHashCode() {
        System.out.println("hashCode");
        int expResult = new RequestImpl(1L, "lol related").hashCode();
        int result;
        
        // check that only the label is hashed
        result = new RequestImpl(0L, "lol related").hashCode();
        assertEquals(expResult, result);
        
        // trivial
        result = new RequestImpl(0L, "LOL").hashCode();
        assertTrue(expResult != result);
        
        // error case : null attributs
        new RequestImpl(null, null).hashCode();
        assertTrue(true);
        
    }

}
