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
package org.opens.urlmanager.entity.service.webpage;

import java.util.ArrayList;
import java.util.List;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import static org.easymock.EasyMock.*;
import org.opens.urlmanager.entity.dao.webpage.WebpageDAO;
import org.opens.urlmanager.entity.locale.Locale;
import org.opens.urlmanager.entity.request.Request;
import org.opens.urlmanager.entity.request.RequestImpl;
import org.opens.urlmanager.entity.tag.Tag;

/**
 *
 * @author bcareil
 */
public class WebpageDataServiceImplTest extends TestCase {

    WebpageDAO mock;
    
    public WebpageDataServiceImplTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(WebpageDataServiceImplTest.class);
        return suite;
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        mock = createMock(WebpageDAO.class);
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of getWebpageFromURL method, of class WebpageDataServiceImpl.
     */
    public void testGetWebpageFromURL() {
        System.out.println("getWebpageFromURL");
        String url = "http://lol.com";
        WebpageDataServiceImpl instance = new WebpageDataServiceImpl();
        
        /*
         * set-up mock
         */
        // We dont care of the return value since getWebpageFromURL is just a return
        expect(mock.findWebpageFromURL(url)).andReturn(null);
        replay(mock);
        /*
         * run test
         */
        instance.setEntityDao(mock);
        instance.getWebpageFromURL(url);
        /*
         * verify mock
         */
        verify(mock);
    }

    /**
     * Test of getRootWebpageList method, of class WebpageDataServiceImpl.
     */
    public void testGetRootWebpageList() {
        System.out.println("getRootWebpageList");
        WebpageDataServiceImpl instance = new WebpageDataServiceImpl();
        
        /*
         * set-up mock
         */
        // We dont care of the return value since getRootWebpageList is just a return
        expect(mock.findRootWebpageList()).andReturn(null);
        replay(mock);
        /*
         * run test
         */
        instance.setEntityDao(mock);
        instance.getRootWebpageList();
        /*
         * verify mock
         */
        verify(mock);
    }

    /**
     * Test of getWebpageListFromRequest method, of class WebpageDataServiceImpl.
     */
    public void testGetWebpageListFromRequest() {
        System.out.println("getWebpageListFromRequest");
        Request request = new RequestImpl(1L, "lol related");
        WebpageDataServiceImpl instance = new WebpageDataServiceImpl();
        
        /*
         * set-up mock
         */
        // We dont care of the return value since getWebpageListFromRequest is just a return
        expect(mock.findWebpageListFromRequest(request)).andReturn(null);
        replay(mock);
        /*
         * run test
         */
        instance.setEntityDao(mock);
        instance.getWebpageListFromRequest(request);
        /*
         * verify mock
         */
        verify(mock);
    }

    /**
     * Test of getWebpageListFromRequestParameters method, of class WebpageDataServiceImpl.
     */
    public void testGetWebpageListFromRequestParameters() {
        System.out.println("getWebpageListFromRequestParameters");
        List<Locale> locales = new ArrayList<Locale>();
        List<Tag> tags = new ArrayList<Tag>();
        WebpageDataServiceImpl instance = new WebpageDataServiceImpl();
        
        /*
         * set-up mock
         */
        // We dont care of the return value since getWebpageListFromRequestParameters is just a return
        expect(mock.findWebpageListFromRequestParameters(locales, tags)).andReturn(null);
        replay(mock);
        /*
         * run test
         */
        instance.setEntityDao(mock);
        instance.getWebpageListFromRequestParameters(locales, tags);
        /*
         * verify mock
         */
        verify(mock);
    }

    /**
     * Test of getWebpagesWithoutTag method, of class WebpageDataServiceImpl.
     */
    public void testGetWebpagesWithoutTag() {
        System.out.println("getWebpagesWithoutTag");
        WebpageDataServiceImpl instance = new WebpageDataServiceImpl();
        
        /*
         * set-up mock
         */
        // We dont care of the return value since getWebpagesWithoutTag is just a return
        expect(mock.findWebpagesWithoutTag()).andReturn(null);
        replay(mock);
        /*
         * run test
         */
        instance.setEntityDao(mock);
        instance.getWebpagesWithoutTag();
        /*
         * verify mock
         */
        verify(mock);
    }

    /**
     * Test of getWebpagesWithoutLocale method, of class WebpageDataServiceImpl.
     */
    public void testGetWebpagesWithoutLocale() {
        System.out.println("getWebpagesWithoutLocale");
        WebpageDataServiceImpl instance = new WebpageDataServiceImpl();
        
        /*
         * set-up mock
         */
        // We dont care of the return value since getWebpagesWithoutLocale is just a return
        expect(mock.findWebpagesWithoutLocale()).andReturn(null);
        replay(mock);
        /*
         * run test
         */
        instance.setEntityDao(mock);
        instance.getWebpagesWithoutLocale();
        /*
         * verify mock
         */
        verify(mock);
    }

    /**
     * Test of getWebpagesWithoutRelations method, of class WebpageDataServiceImpl.
     */
    public void testGetWebpagesWithoutRelations() {
        System.out.println("getWebpagesWithoutRelations");
        WebpageDataServiceImpl instance = new WebpageDataServiceImpl();
        
        /*
         * set-up mock
         */
        // We dont care of the return value since getWebpagesWithoutRelations is just a return
        expect(mock.findWebpagesWithoutRelations()).andReturn(null);
        replay(mock);
        /*
         * run test
         */
        instance.setEntityDao(mock);
        instance.getWebpagesWithoutRelations();
        /*
         * verify mock
         */
        verify(mock);
    }
}
