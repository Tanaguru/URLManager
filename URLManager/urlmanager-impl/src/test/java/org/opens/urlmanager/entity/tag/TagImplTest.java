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
package org.opens.urlmanager.entity.tag;

import junit.framework.TestCase;

/**
 *
 * @author bcareil
 */
public class TagImplTest extends TestCase {
    
    public TagImplTest(String testName) {
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
     * Test of equals method, of class TagImpl.
     */
    public void testEquals() {
        System.out.println("equals");
        TagImpl instance = new TagImpl(1L, "lol");
        TagImpl other;
        boolean result;

        // check that the label and the id are tested.
        other = new TagImpl(1L, "lol");
        result = instance.equals(other);
        assertTrue(result);

        // check that the label is not ignored.
        other = new TagImpl(1L, "lil");
        result = instance.equals(other);
        assertFalse(result);

        // check that the id is not ignored.
        other = new TagImpl(0L, "lol");
        result = instance.equals(other);
        assertFalse(result);
        
        // trivial test
        other = new TagImpl(0L, "lil");
        result = instance.equals(other);
        assertFalse(result);
        
        // error case : label is null
        other = new TagImpl(0L, null);
        result = instance.equals(other);
        assertFalse(result);

        // error case : label is null
        other = null;
        result = instance.equals(other);
        assertFalse(result);
    }

    /**
     * Test of hashCode method, of class TagImpl.
     */
    public void testHashCode() {
        System.out.println("hashCode");
        int expResult = new TagImpl(1L, "lol").hashCode();
        int result;
        
        // check that the label and the id are hashed
        result = new TagImpl(1L, "lol").hashCode();
        assertEquals(expResult, result);

        // check that the label is not ignored
        result = new TagImpl(1L, "lil").hashCode();
        assertTrue(expResult != result);

        // check that the label and the id are hashed
        result = new TagImpl(0L, "lol").hashCode();
        assertTrue(expResult != result);

        // error case : label null
        new TagImpl(0L, null).hashCode();
        assertTrue(true);
    }
}
