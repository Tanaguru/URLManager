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
package org.opens.urlmanager.entity.dao.webpage;

import java.util.*;
import junit.framework.Test;
import junit.framework.TestSuite;
import org.opens.urlmanager.entity.locale.Locale;
import org.opens.urlmanager.entity.locale.LocaleImpl;
import org.opens.urlmanager.entity.service.webpage.WebpageDataServiceImpl;
import org.opens.urlmanager.entity.tag.Tag;
import org.opens.urlmanager.entity.tag.TagImpl;
import org.opens.urlmanager.entity.webpage.Webpage;
import org.opens.urlmanager.entity.webpage.WebpageImpl;
import org.opens.urlmanager.persistence.AbstractDaoTestCase;
import org.opens.urlmanager.persistence.ListContentComparator;

/**
 *
 * @author bcareil
 */
public class WebpageDAOImplTest extends AbstractDaoTestCase {
    
    private WebpageDAO webpageDAO;
    
    public WebpageDAOImplTest(String testName) {
        super(testName, "src/test/resources/dataSets/flatDataSet.xml");
        webpageDAO = (WebpageDAO) springBeanFactory.getBean("webpageDAO");
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(WebpageDAOImplTest.class);
        return suite;
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
     * Test of getEntityClass method, of class WebpageDAOImpl.
     */
    public void testGetEntityClass() {
        System.out.println("getEntityClass");
        WebpageDAOImpl instance = new WebpageDAOImpl();
        Class expResult = WebpageImpl.class;
        Class result = instance.getEntityClass();
        assertEquals(expResult, result);
    }

    /**
     * Test of findAll method, of class WebpageDAOImpl
     */
    public void testFindAll() {
        System.out.println("testFindAll");
        Collection<Webpage> result;
        Webpage expWebpage = new WebpageImpl(1L, "http://lol.com/", Boolean.TRUE);
        Set<Tag> expTag = new HashSet<Tag>(Arrays.asList(
                (Tag) new TagImpl(1L, "lol"),
                (Tag) new TagImpl(2L, "lil")
                ));
        Set<Locale> expLocale = new HashSet<Locale>(Arrays.asList(
                (Locale) new LocaleImpl(1L, "fr", "french",  "FR", "France"),
                (Locale) new LocaleImpl(3L, "en", "english", "GB", "Great Britain"),
                (Locale) new LocaleImpl(4L, "en", "english", "US", "United States")
                ));
        
        
        // run method
        result = webpageDAO.findAll();
        // expect 5 results
        assertEquals(5, result.size());
        Iterator<Webpage> it = result.iterator();
        while (it.hasNext()) {
            Webpage webpage = it.next();
            
            if (webpage.equals(expWebpage)) {
                it = null;
                assertEquals(expTag, webpage.getTags());
                assertEquals(expLocale, webpage.getLocales());
                break;
            }
        }
        if (it != null) {
            fail("Webpage with URL " + expWebpage.getURL() + " does not exist");
        }
    }
    
    /**
     * Test of read method, of class WebpageDAOImpl
     */
    public void testRead() {
        System.out.println("testRead");
        Webpage result;
        Webpage expWebpage = new WebpageImpl(1L, "http://lol.com/", Boolean.TRUE);
        Set<Tag> expTag = new HashSet<Tag>(Arrays.asList(
                (Tag) new TagImpl(1L, "lol"),
                (Tag) new TagImpl(2L, "lil")
                ));
        Set<Locale> expLocale = new HashSet<Locale>(Arrays.asList(
                (Locale) new LocaleImpl(1L, "fr", "french",  "FR", "France"),
                (Locale) new LocaleImpl(3L, "en", "english", "GB", "Great Britain"),
                (Locale) new LocaleImpl(4L, "en", "english", "US", "United States")
                ));
        
        /* nominal UC */
        // run method
        result = webpageDAO.read(1L);
        // test result;
        assertEquals(expWebpage, result);
        assertEquals(expTag, result.getTags());
        assertEquals(expLocale, result.getLocales());
        
        /* error UC : invalid ID */
        result = webpageDAO.read(0L);
        // test result
        assertNull(result);
    }
    
    /**
     * Test of findWebpageFromURL method, of class WebpageDAOImpl.
     */
    public void testFindWebpageFromURL() {
        System.out.println("findWebpageFromURL");
        Webpage result;

        System.out.println("-> existing webpage");
        result = webpageDAO.findWebpageFromURL("http://lol.com/");
        // expected result : webpage of Id_Webpage=1
        assertNotNull(result);
        assertEquals(Long.valueOf(1), result.getId());

        System.out.println("-> invalid webpage");
        result = webpageDAO.findWebpageFromURL("blah");
        // expected result : webpage of Id_Webpage=1
        assertNull(result);
    }

    /**
     * Test of findRootWebpageList method, of class WebpageDAOImpl.
     */
    public void testFindRootWebpageList() {
        System.out.println("findRootWebpageList");
        Set<Webpage> result;
        Set<Webpage> expResult;
        
        System.out.println("-> with matching webpages in DB");
        expResult = new HashSet<Webpage>(Arrays.asList(
                (Webpage) new WebpageImpl(1L, "http://lol.com/", true),
                (Webpage) new WebpageImpl(4L, "http://toto.com/", true),
                (Webpage) new WebpageImpl(5L, "http://foreveralone.com/", true)
                ));
        result = webpageDAO.findRootWebpageList();
        //
        assertEquals(expResult, result);
        
        // delete matching entries to test the same method without them
        for (Webpage webpage : result) {
            webpage.getLocales().clear();
            webpage.getTags().clear();
            webpage = webpageDAO.update(webpage);
            webpageDAO.delete(webpage.getId());
        }
        
        System.out.println("-> without matching webpages in DB");
        result = webpageDAO.findRootWebpageList();
        //
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    /**
     * Test of findWebpagesWithoutTag method, of class WebpageDAOImpl.
     */
    public void testFindWebpagesWithoutTag() {
        System.out.println("findWebpagesWithoutTag");
        List<Webpage> expResult;
        List<Webpage> result;
        
        System.out.println("-> with matching webpages");
        expResult = Arrays.asList(
                (Webpage) new WebpageImpl(5L, "http://foreveralone.com/", Boolean.TRUE)
                );
        result = webpageDAO.findWebpagesWithoutTag();
        assertEquals(0, new ListContentComparator<Webpage>().compare(
                expResult, result,
                new WebpageDataServiceImpl.Comparator()));
        
        // TODO: delete result
        
        System.out.println("-> without matching webpages");
        // TODO: test without possible matching
    }

    /**
     * Test of findWebpagesWithoutLocale method, of class WebpageDAOImpl.
     */
    public void testFindWebpagesWithoutLocale() {
        System.out.println("findWebpagesWithoutLocale");
        List<Webpage> expResult;
        List<Webpage> result;
        
        System.out.println("-> with matching webpages");
        expResult = Arrays.asList(
                (Webpage) new WebpageImpl(5L, "http://foreveralone.com/", Boolean.TRUE)
                );
        result = webpageDAO.findWebpagesWithoutLocale();
        assertEquals(0, new ListContentComparator<Webpage>().compare(
                expResult, result,
                new WebpageDataServiceImpl.Comparator()));
        
        // TODO: delete result
        
        System.out.println("-> without matching webpages");
        // TODO: test without possible matching
    }

    /**
     * Test of findWebpagesWithoutRelations method, of class WebpageDAOImpl.
     */
    public void testFindWebpagesWithoutRelations() {
        System.out.println("findWebpagesWithoutRelations");
        List<Webpage> expResult;
        List<Webpage> result;
        
        System.out.println("-> with matching webpages");
        expResult = Arrays.asList(
                (Webpage) new WebpageImpl(5L, "http://foreveralone.com/", Boolean.TRUE)
                );
        result = webpageDAO.findWebpagesWithoutRelations();
        assertEquals(0, new ListContentComparator<Webpage>().compare(
                expResult, result,
                new WebpageDataServiceImpl.Comparator()));
        
        // TODO: delete result
        
        System.out.println("-> without matching webpages");
        // TODO: test without possible matching
    }
}
