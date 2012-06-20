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
package org.opens.urlmanager.entity.webpage;

import junit.framework.TestCase;

/**
 *
 * @author bcareil
 */
public class WebpageImplTest extends TestCase {
    
    public WebpageImplTest(String testName) {
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
     * Test of equals method, of class WebpageImpl.
     */
    public void testEquals() {
        System.out.println("equals");
        WebpageImpl instance = new WebpageImpl(1L, "http://lol.com", Boolean.TRUE);
        WebpageImpl other;
        boolean result;
        
        // check that only the url and the id are tested
        other = new WebpageImpl(1L, "http://lol.com", Boolean.FALSE);
        result = instance.equals(other);
        assertTrue(result);

        // check that the url is not ignored
        other = new WebpageImpl(1L, "blah", Boolean.FALSE);
        result = instance.equals(other);
        assertFalse(result);

        // check that only the id is not ignored
        other = new WebpageImpl(0L, "http://lol.com", Boolean.FALSE);
        result = instance.equals(other);
        assertFalse(result);

        // trivial
        other = new WebpageImpl(0L, "blah", Boolean.FALSE);
        result = instance.equals(other);
        assertFalse(result);

        // error case : null attributs
        other = new WebpageImpl(null, null, null);
        result = instance.equals(other);
        assertFalse(result);

        // error case : null attributs
        other = null;
        result = instance.equals(other);
        assertFalse(result);
    }

    /**
     * Test of hashCode method, of class WebpageImpl.
     */
    public void testHashCode() {
        System.out.println("hashCode");
        int expResult = new WebpageImpl(1L, "http://lol.com/", Boolean.TRUE).hashCode();
        int result;
        
        // check that only the url and the id are hashed
        result = new WebpageImpl(1L, "http://lol.com/", Boolean.FALSE).hashCode();
        assertEquals(expResult, result);

        // check that the url is not ignored
        result = new WebpageImpl(1L, "blah", Boolean.FALSE).hashCode();
        assertTrue(expResult != result);

        // check that the id is not ignored
        result = new WebpageImpl(0L, "http://lol.com/", Boolean.FALSE).hashCode();
        assertTrue(expResult != result);

        // trivial
        result = new WebpageImpl(0L, "blah", Boolean.FALSE).hashCode();
        assertTrue(expResult != result);

        // error case : null attributs
        result = new WebpageImpl(null, null, null).hashCode();
        assertTrue(expResult != result);
    }

}
