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

import java.util.Collection;
import java.util.List;
import junit.framework.TestCase;
import static org.easymock.EasyMock.*;
import org.opens.urlmanager.entity.dao.locale.LocaleDAO;
import org.opens.urlmanager.entity.locale.Locale;
import org.opens.urlmanager.entity.service.EntityDTOWrapper;

/**
 *
 * @author bcareil
 */
public class LocaleDataServiceImplTest extends TestCase {
    
    LocaleDAO localeDao;
    EntityDTOWrapper wrapper;
    
    public LocaleDataServiceImplTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        localeDao = createMock(LocaleDAO.class);
        wrapper = createMock(EntityDTOWrapper.class);
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    private LocaleDataServiceImpl getInstance() {
        LocaleDataServiceImpl ret = new LocaleDataServiceImpl();
        
        ret.setEntityDao(localeDao);
        ret.setWrapper(wrapper);
        return ret;
    }

    /**
     * Test of getLocaleListFromLanguage method, of class LocaleDataServiceImpl.
     */
    public void testGetLocaleListFromLanguage() {
        System.out.println("getLocaleListFromLanguage");
        String language = "fr";
        LocaleDataServiceImpl instance = getInstance();
        List<Locale> localeEntity = createMock(List.class);
        List<Locale> localeDto = createMock(List.class);
        List<Locale> result;

        /*
         * set-up mock
         */
        expect(localeDao.findLocaleListFromLanguage(language)).andReturn(localeEntity);
        expect(wrapper.entityCollectionToDtoCollection((Collection) localeEntity)).andReturn((Collection) localeDto);
        
        replay(localeDao);
        replay(wrapper);
        replay(localeEntity);
        replay(localeDto);
        /*
         * run test
         */
        result = instance.getLocaleListFromLanguage(language);
        /*
         * asserts
         */
        assertEquals(localeDto, result);
        /*
         * check mock state
         */
        verify(localeDao);
        verify(wrapper);
        verify(localeEntity);
        verify(localeDto);
    }

    /**
     * Test of getLocaleListFromCountry method, of class LocaleDataServiceImpl.
     */
    public void testGetLocaleListFromCountry() {
        System.out.println("getLocaleListFromCountry");
        String country = "FR";
        LocaleDataServiceImpl instance = getInstance();
        List<Locale> localeEntity = createMock(List.class);
        List<Locale> localeDto = createMock(List.class);
        List<Locale> result;

        /*
         * set-up mock
         */
        expect(localeDao.findLocaleListFromCountry(country)).andReturn(localeEntity);
        expect(wrapper.entityCollectionToDtoCollection((Collection) localeEntity)).andReturn((Collection) localeDto);

        replay(localeDao);
        replay(wrapper);
        replay(localeEntity);
        replay(localeDto);
        /*
         * run test
         */
        result = instance.getLocaleListFromCountry(country);
        /*
         * asserts
         */
        assertEquals(localeDto, result);
        /*
         * check mock state
         */
        verify(localeDao);
        verify(wrapper);
        verify(localeEntity);
        verify(localeDto);
    }

    /**
     * Test of getLocaleListFromLongLanguage method, of class LocaleDataServiceImpl.
     */
    public void testGetLocaleListFromLongLanguage() {
        System.out.println("getLocaleListFromLongLanguage");
        String longLanguage = "french";
        LocaleDataServiceImpl instance = getInstance();
        List<Locale> localeEntity = createMock(List.class);
        List<Locale> localeDto = createMock(List.class);
        List<Locale> result;

        /*
         * set-up mock
         */
        expect(localeDao.findLocaleListFromLongLanguage(longLanguage)).andReturn(localeEntity);
        expect(wrapper.entityCollectionToDtoCollection((Collection) localeEntity)).andReturn((Collection) localeDto);

        replay(localeDao);
        replay(wrapper);
        replay(localeEntity);
        replay(localeDto);
        /*
         * run test
         */
        result = instance.getLocaleListFromLongLanguage(longLanguage);
        /*
         * asserts
         */
        assertEquals(localeDto, result);
        /*
         * check mock state
         */
        verify(localeDao);
        verify(wrapper);
        verify(localeEntity);
        verify(localeDto);
    }

    /**
     * Test of getLocaleListFromLongCountry method, of class LocaleDataServiceImpl.
     */
    public void testGetLocaleListFromLongCountry() {
        System.out.println("getLocaleListFromLongCountry");
        String longCountry = "France";
        LocaleDataServiceImpl instance = getInstance();
        List<Locale> localeEntity = createMock(List.class);
        List<Locale> localeDto = createMock(List.class);
        List<Locale> result;

        /*
         * set-up mock
         */
        expect(localeDao.findLocaleListFromLongCountry(longCountry)).andReturn(localeEntity);
        expect(wrapper.entityCollectionToDtoCollection((Collection) localeEntity)).andReturn((Collection) localeDto);

        replay(localeDao);
        replay(wrapper);
        replay(localeEntity);
        replay(localeDto);
        /*
         * run test
         */
        result = instance.getLocaleListFromLongCountry(longCountry);
        /*
         * asserts
         */
        assertEquals(localeDto, result);
        /*
         * check mock state
         */
        verify(localeDao);
        verify(wrapper);
        verify(localeEntity);
        verify(localeDto);
    }

    /**
     * Test of getLocaleFromLanguageAndCountry method, of class LocaleDataServiceImpl.
     */
    public void testGetLocaleFromLanguageAndCountry() {
        System.out.println("getLocaleFromLanguageAndCountry");
        String language = "fr";
        String country = "FR";
        LocaleDataServiceImpl instance = getInstance();
        Locale localeEntity = createMock(Locale.class);
        Locale localeDto = createMock(Locale.class);
        Locale result;

        /*
         * set-up mock
         */
        expect(localeDao.findLocaleFromLanguageAndCountry(language, country)).andReturn(localeEntity);
        expect(wrapper.entityToDto(localeEntity)).andReturn(localeDto);

        replay(localeDao);
        replay(wrapper);
        replay(localeEntity);
        replay(localeDto);
        /*
         * run test
         */
        result = instance.getLocaleFromLanguageAndCountry(language, country);
        /*
         * asserts
         */
        assertEquals(localeDto, result);
        /*
         * check mock state
         */
        verify(localeDao);
        verify(wrapper);
        verify(localeEntity);
        verify(localeDto);
    }

    /**
     * Test of getLocaleFromLongLanguageAndLongCountry method, of class LocaleDataServiceImpl.
     */
    public void testGetLocaleFromLongLanguageAndLongCountry() {
        System.out.println("getLocaleFromLongLanguageAndLongCountry");
        String longLanguage = "french";
        String longCountry = "France";
        LocaleDataServiceImpl instance = getInstance();
        Locale localeEntity = createMock(Locale.class);
        Locale localeDto = createMock(Locale.class);
        Locale result;

        /*
         * set-up mock
         */
        // ignore return value since getLocaleFromLongLanguageAndLongCountry is just a return
        expect(localeDao.findLocaleFromLongLanguageAndLongCountry(longLanguage, longCountry)).andReturn(localeEntity);
        expect(wrapper.entityToDto(localeEntity)).andReturn(localeDto);

        replay(localeDao);
        replay(wrapper);
        replay(localeEntity);
        replay(localeDto);
        /*
         * run test
         */
        result = instance.getLocaleFromLongLanguageAndLongCountry(longLanguage, longCountry);
        /*
         * asserts
         */
        assertEquals(localeDto, result);
        /*
         * check mock state
         */
        verify(localeDao);
        verify(wrapper);
        verify(localeEntity);
        verify(localeDto);
    }
}
