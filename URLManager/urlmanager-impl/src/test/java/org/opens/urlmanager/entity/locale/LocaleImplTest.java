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
package org.opens.urlmanager.entity.locale;

import junit.framework.TestCase;

/**
 *
 * @author bcareil
 */
public class LocaleImplTest extends TestCase {
    
    public LocaleImplTest(String testName) {
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
     * Test of equals method, of class LocaleImpl.
     */
    public void testEquals() {
        System.out.println("equals");
        LocaleImpl other;
        LocaleImpl instance = new LocaleImpl(1L, "fr", "french", "FR", "France");
        boolean result;

        // check if a locale is well identified using only its language and country
        other = new LocaleImpl(0L, "fr", "", "FR", "");
        result = instance.equals(other);
        assertTrue(result);

        // check if two locales differ if the country differ
        other = new LocaleImpl(0L, "fr", "", "CA", "");
        result = instance.equals(other);
        assertFalse(result);

        // check if two locales differ if the langauge differ
        other = new LocaleImpl(0L, "pro", "", "FR", "");
        result = instance.equals(other);
        assertFalse(result);

        // trivial test
        other = new LocaleImpl(0L, "en", "", "GB", "");
        result = instance.equals(other);
        assertFalse(result);
        
        // error case : compare with null
        other = null;
        result = instance.equals(other);
        assertFalse(result);

        // error case : main values are null
        other = new LocaleImpl(null, null, null, null, null);
        result = instance.equals(other);
        assertFalse(result);
    }

    /**
     * Test of hashCode method, of class LocaleImpl.
     */
    public void testHashCode() {
        System.out.println("hashCode");
        int expResult = new LocaleImpl(1L, "fr", "french", "FR", "France").hashCode();
        int result;

        // check that only the language and the country are hashed
        result = new LocaleImpl(0L, "fr", "", "FR", "").hashCode();
        assertEquals(expResult, result);

        // error case
        new LocaleImpl(null, null, null, null, null).hashCode();
        assertTrue(true);
    }
}
