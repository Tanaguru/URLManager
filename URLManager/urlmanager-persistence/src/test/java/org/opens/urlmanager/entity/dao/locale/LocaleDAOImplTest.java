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
package org.opens.urlmanager.entity.dao.locale;

import java.util.Arrays;
import java.util.List;
import junit.framework.Test;
import junit.framework.TestSuite;
import org.opens.urlmanager.entity.locale.Locale;
import org.opens.urlmanager.entity.locale.LocaleImpl;
import org.opens.urlmanager.entity.service.locale.LocaleDataServiceImpl;
import org.opens.urlmanager.persistence.AbstractDaoTestCase;
import org.opens.urlmanager.persistence.ListContentComparator;

/**
 *
 * @author bcareil
 */
public class LocaleDAOImplTest extends AbstractDaoTestCase {

    private LocaleDAO localeDAO;

    public LocaleDAOImplTest(String testName) {
        super(testName, "src/test/resources/dataSets/flatDataSet.xml");
        localeDAO = (LocaleDAO) springBeanFactory.getBean("localeDAO");
    }

    public static Test suite() {
        TestSuite ts = new TestSuite(LocaleDAOImplTest.class);
        return ts;
    }

    /**
     * Test of getEntityClass method, of class LocaleDAOImpl.
     */
    public void testGetEntityClass() {
        System.out.println("getEntityClass");
        LocaleDAOImpl instance = new LocaleDAOImpl();
        Class expResult = LocaleImpl.class;
        Class result = instance.getEntityClass();
        assertEquals(expResult, result);
    }

    /**
     * Test of findLocaleListFromLanguage method, of class LocaleDAOImpl.
     */
    public void testFindLocaleListFromLanguage() {
        System.out.println("findLocaleListFromLanguage");
        LocaleDAO instance = localeDAO;
        List<Locale> expResult;
        List<Locale> result;

        System.out.println("-> existing language");
        expResult = Arrays.asList(
                (Locale) new LocaleImpl(1L, "fr", "french", "FR", "France"),
                (Locale) new LocaleImpl(2L, "fr", "french", "CA", "Canada")
                );
        result = instance.findLocaleListFromLanguage("fr");
        assertEquals(0, new ListContentComparator< Locale>().compare(
                expResult, result,
                new LocaleDataServiceImpl.Comparator()));

        System.out.println("-> invalid language");
        result = instance.findLocaleListFromLanguage("lol");
        assertTrue(result.isEmpty());
    }

    /**
     * Test of findLocaleListFromCountry method, of class LocaleDAOImpl.
     */
    public void testFindLocaleListFromCountry() {
        System.out.println("findLocaleListFromCountry");
        LocaleDAO instance = localeDAO;
        List<Locale> expResult;
        List<Locale> result;

        System.out.println("-> existing country");
        expResult = Arrays.asList(
                (Locale) new LocaleImpl(3L, "en", "english", "GB", "Greate Britain"));
        result = instance.findLocaleListFromCountry("GB");
        assertEquals(0, new ListContentComparator<Locale>().compare(
                expResult, result,
                new LocaleDataServiceImpl.Comparator()));

        System.out.println("-> invalid country");
        result = instance.findLocaleListFromCountry("lol");
        assertTrue(result.isEmpty());
    }

    /**
     * Test of findLocaleListFromLongLanguage method, of class LocaleDAOImpl.
     */
    public void testFindLocaleListFromLongLanguage() {
        System.out.println("findLocaleListFromLongLanguage");
        LocaleDAO instance = localeDAO;
        List<Locale> expResult;
        List<Locale> result;

        System.out.println("-> existing language");
        expResult = Arrays.asList(
                (Locale) new LocaleImpl(3L, "en", "english", "GB", "Greate Britain"),
                (Locale) new LocaleImpl(4L, "en", "english", "US", "United States"));
        result = instance.findLocaleListFromLongLanguage("english");
        assertEquals(0, new ListContentComparator<Locale>().compare(
                expResult, result,
                new LocaleDataServiceImpl.Comparator()));

        System.out.println("-> invalid language");
        result = instance.findLocaleListFromLongLanguage("lol");
        assertTrue(result.isEmpty());
    }

    /**
     * Test of findLocaleListFromLongCountry method, of class LocaleDAOImpl.
     */
    public void testFindLocaleListFromLongCountry() {
        System.out.println("findLocaleListFromLongCountry");
        LocaleDAO instance = localeDAO;
        List<Locale> expResult;
        List<Locale> result;

        System.out.println("-> existing language");
        expResult = Arrays.asList(
                (Locale) new LocaleImpl(1L, "fr", "french", "FR", "France"));
        result = instance.findLocaleListFromLongCountry("France");
        assertEquals(0, new ListContentComparator<Locale>().compare(
                expResult, result,
                new LocaleDataServiceImpl.Comparator()));

        System.out.println("-> invalid language");
        result = instance.findLocaleListFromLongCountry("lol");
        assertTrue(result.isEmpty());
    }

    /**
     * Test of findLocaleFromLanguageAndCountry method, of class LocaleDAOImpl.
     */
    public void testFindLocaleFromLanguageAndCountry() {
        System.out.println("findLocaleFromLanguageAndCountry");
        LocaleDAO instance = localeDAO;
        Locale result;

        System.out.println("-> existing language");
        result = instance.findLocaleFromLanguageAndCountry("fr", "FR");
        // expected result : locale fr_FR of Id_Locale=1
        assertNotNull(result);
        assertEquals(Long.valueOf(1), result.getId());

        System.out.println("-> invalid language");
        result = instance.findLocaleFromLanguageAndCountry("lol", "lol");
        assertNull(result);
    }

    /**
     * Test of findLocaleFromLongLanguageAndLongCountry method, of class
     * LocaleDAOImpl.
     */
    public void testFindLocaleFromLongLanguageAndLongCountry() {
        System.out.println("findLocaleFromLongLanguageAndLongCountry");
        LocaleDAO instance = localeDAO;
        Locale result;

        System.out.println("-> existing language");
        result = instance.findLocaleFromLongLanguageAndLongCountry("french", "France");
        // expected result : local fr_FR of Id_Locale=1
        assertNotNull(result);
        assertEquals(Long.valueOf(1), result.getId());

        System.out.println("-> invalid language");
        result = instance.findLocaleFromLongLanguageAndLongCountry("lol", "lol");
        assertNull(result);
    }
}
