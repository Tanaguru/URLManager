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
package org.opens.urlmanager.entity.service.locale;

import junit.framework.TestCase;
import static org.easymock.EasyMock.*;
import org.opens.urlmanager.entity.dao.locale.LocaleDAO;

/**
 *
 * @author bcareil
 */
public class LocaleDataServiceImplTest extends TestCase {
    
    LocaleDAO mock;
    
    public LocaleDataServiceImplTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        mock = createMock(LocaleDAO.class);
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of getLocaleListFromLanguage method, of class LocaleDataServiceImpl.
     */
    public void testGetLocaleListFromLanguage() {
        System.out.println("getLocaleListFromLanguage");
        String language = "fr";
        LocaleDataServiceImpl instance = new LocaleDataServiceImpl();

        /*
         * set-up mock
         */
        // ignore return value since getLocaleListFromLanguage is just a return
        expect(mock.findLocaleListFromLanguage(language)).andReturn(null);
        replay(mock);
        /*
         * run test
         */
        instance.setEntityDao(mock);
        instance.getLocaleListFromLanguage(language);
        /*
         * check mock state
         */
        verify(mock);
    }

    /**
     * Test of getLocaleListFromCountry method, of class LocaleDataServiceImpl.
     */
    public void testGetLocaleListFromCountry() {
        System.out.println("getLocaleListFromCountry");
        String country = "FR";
        LocaleDataServiceImpl instance = new LocaleDataServiceImpl();

        /*
         * set-up mock
         */
        // ignore return value since getLocaleListFromCountry is just a return
        expect(mock.findLocaleListFromCountry(country)).andReturn(null);
        replay(mock);
        /*
         * run test
         */
        instance.setEntityDao(mock);
        instance.getLocaleListFromCountry(country);
        /*
         * check mock state
         */
        verify(mock);
}

    /**
     * Test of getLocaleListFromLongLanguage method, of class LocaleDataServiceImpl.
     */
    public void testGetLocaleListFromLongLanguage() {
        System.out.println("getLocaleListFromLongLanguage");
        String longLanguage = "french";
        LocaleDataServiceImpl instance = new LocaleDataServiceImpl();

        /*
         * set-up mock
         */
        // ignore return value since getLocaleListFromLongLanguage is just a return
        expect(mock.findLocaleListFromLongLanguage(longLanguage)).andReturn(null);
        replay(mock);
        /*
         * run test
         */
        instance.setEntityDao(mock);
        instance.getLocaleListFromLongLanguage(longLanguage);
        /*
         * check mock state
         */
        verify(mock);
}

    /**
     * Test of getLocaleListFromLongCountry method, of class LocaleDataServiceImpl.
     */
    public void testGetLocaleListFromLongCountry() {
        System.out.println("getLocaleListFromLongCountry");
        String longCountry = "France";
        LocaleDataServiceImpl instance = new LocaleDataServiceImpl();

        /*
         * set-up mock
         */
        // ignore return value since findLocaleListFromLongCountry is just a return
        expect(mock.findLocaleListFromLongCountry(longCountry)).andReturn(null);
        replay(mock);
        /*
         * run test
         */
        instance.setEntityDao(mock);
        instance.getLocaleListFromLongCountry(longCountry);
        /*
         * check mock state
         */
        verify(mock);
}

    /**
     * Test of getLocaleFromLanguageAndCountry method, of class LocaleDataServiceImpl.
     */
    public void testGetLocaleFromLanguageAndCountry() {
        System.out.println("getLocaleFromLanguageAndCountry");
        String language = "fr";
        String country = "FR";
        LocaleDataServiceImpl instance = new LocaleDataServiceImpl();

        /*
         * set-up mock
         */
        // ignore return value since getLocaleFromLanguageAndCountry is just a return
        expect(mock.findLocaleFromLanguageAndCountry(language, country)).andReturn(null);
        replay(mock);
        /*
         * run test
         */
        instance.setEntityDao(mock);
        instance.getLocaleFromLanguageAndCountry(language, country);
        /*
         * check mock state
         */
        verify(mock);
}

    /**
     * Test of getLocaleFromLongLanguageAndLongCountry method, of class LocaleDataServiceImpl.
     */
    public void testGetLocaleFromLongLanguageAndLongCountry() {
        System.out.println("getLocaleFromLongLanguageAndLongCountry");
        String longLanguage = "french";
        String longCountry = "France";
        LocaleDataServiceImpl instance = new LocaleDataServiceImpl();

        /*
         * set-up mock
         */
        // ignore return value since getLocaleFromLongLanguageAndLongCountry is just a return
        expect(mock.findLocaleFromLongLanguageAndLongCountry(longLanguage, longCountry)).andReturn(null);
        replay(mock);
        /*
         * run test
         */
        instance.setEntityDao(mock);
        instance.getLocaleFromLongLanguageAndLongCountry(longLanguage, longCountry);
        /*
         * check mock state
         */
        verify(mock);
    }
}
