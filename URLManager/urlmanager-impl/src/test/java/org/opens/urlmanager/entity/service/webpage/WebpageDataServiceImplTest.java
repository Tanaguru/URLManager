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

import java.util.Collection;
import java.util.List;
import java.util.Set;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import static org.easymock.EasyMock.*;
import org.opens.urlmanager.entity.dao.webpage.WebpageDAO;
import org.opens.urlmanager.entity.service.EntityDTOWrapper;
import org.opens.urlmanager.entity.webpage.Webpage;

/**
 *
 * @author bcareil
 */
public class WebpageDataServiceImplTest extends TestCase {

    WebpageDAO webpageDao;
    EntityDTOWrapper wrapper;
    
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
        
        webpageDao = createMock(WebpageDAO.class);
        wrapper = createMock(EntityDTOWrapper.class);
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    private WebpageDataServiceImpl getInstance() {
        WebpageDataServiceImpl ret;
        
        ret = new WebpageDataServiceImpl();
        ret.setEntityDao(webpageDao);
        ret.setWrapper(wrapper);
        return ret;
    }
    
    /**
     * Test of getWebpageFromURL method, of class WebpageDataServiceImpl.
     */
    public void testGetWebpageFromURL() {
        System.out.println("getWebpageFromURL");
        String url = "http://lol.com";
        WebpageDataServiceImpl instance = getInstance();
        Webpage webpageEntity = createMock(Webpage.class);
        Webpage webpageDto = createMock(Webpage.class);
        Webpage result;
        
        /*
         * set-up mock
         */
        expect(webpageDao.findWebpageFromURL(url)).andReturn(webpageEntity);
        expect(wrapper.entityToDto(webpageEntity)).andReturn(webpageDto);
        //
        replay(webpageDao);
        replay(webpageDto);
        replay(webpageEntity);
        replay(wrapper);
        /*
         * run test
         */
        result = instance.getWebpageFromURL(url);
        /*
         * asserts
         */
        assertEquals(webpageDto, result);
        /*
         * verify mock
         */
        verify(webpageDao);
        verify(webpageDto);
        verify(webpageEntity);
        verify(wrapper);
    }

    /**
     * Test of getRootWebpageList method, of class WebpageDataServiceImpl.
     */
    public void testGetRootWebpageList() {
        System.out.println("getRootWebpageList");
        WebpageDataServiceImpl instance = getInstance();
        Set<Webpage> webpageEntity = createMock(Set.class);
        Set<Webpage> webpageDto = createMock(Set.class);
        Set<Webpage> result;
        
        /*
         * set-up mock
         */
        expect(webpageDao.findRootWebpageList()).andReturn(webpageEntity);
        expect(wrapper.entityCollectionToDtoCollection((Collection) webpageEntity)).andReturn((Collection) webpageDto);
        //
        replay(webpageDao);
        replay(webpageDto);
        replay(webpageEntity);
        replay(wrapper);
        /*
         * run test
         */
        result = instance.getRootWebpageList();
        /*
         * asserts
         */
        assertEquals(webpageDto, result);
        /*
         * verify mock
         */
        verify(webpageDao);
        verify(webpageDto);
        verify(webpageEntity);
        verify(wrapper);
    }

    /**
     * Test of getWebpagesWithoutTag method, of class WebpageDataServiceImpl.
     */
    public void testGetWebpagesWithoutTag() {
        System.out.println("getWebpagesWithoutTag");
        WebpageDataServiceImpl instance = getInstance();
        List<Webpage> webpageEntity = createMock(List.class);
        List<Webpage> webpageDto = createMock(List.class);
        List<Webpage> result;
        
        /*
         * set-up mock
         */
        expect(webpageDao.findWebpagesWithoutTag()).andReturn(webpageEntity);
        expect(wrapper.entityCollectionToDtoCollection((Collection) webpageEntity)).andReturn((Collection) webpageDto);
        //
        replay(webpageDao);
        replay(webpageDto);
        replay(webpageEntity);
        replay(wrapper);
        /*
         * run test
         */
        result = instance.getWebpagesWithoutTag();
        /*
         * asserts
         */
        assertEquals(webpageDto, result);
        /*
         * verify mock
         */
        verify(webpageDao);
        verify(webpageDto);
        verify(webpageEntity);
        verify(wrapper);
    }

    /**
     * Test of getWebpagesWithoutLocale method, of class WebpageDataServiceImpl.
     */
    public void testGetWebpagesWithoutLocale() {
        System.out.println("getWebpagesWithoutLocale");
        WebpageDataServiceImpl instance = getInstance();
        List<Webpage> webpageEntity = createMock(List.class);
        List<Webpage> webpageDto = createMock(List.class);
        List<Webpage> result;
        
        /*
         * set-up mock
         */
        expect(webpageDao.findWebpagesWithoutLocale()).andReturn(webpageEntity);
        expect(wrapper.entityCollectionToDtoCollection((Collection) webpageEntity)).andReturn((Collection) webpageDto);
        //
        replay(webpageDao);
        replay(webpageDto);
        replay(webpageEntity);
        replay(wrapper);
        /*
         * run test
         */
        result = instance.getWebpagesWithoutLocale();
        /*
         * asserts
         */
        assertEquals(webpageDto, result);
        /*
         * verify mock
         */
        verify(webpageDao);
        verify(webpageDto);
        verify(webpageEntity);
        verify(wrapper);
    }

    /**
     * Test of getWebpagesWithoutRelations method, of class WebpageDataServiceImpl.
     */
    public void testGetWebpagesWithoutRelations() {
        System.out.println("getWebpagesWithoutRelations");
        WebpageDataServiceImpl instance = getInstance();
        List<Webpage> webpageEntity = createMock(List.class);
        List<Webpage> webpageDto = createMock(List.class);
        List<Webpage> result;
        
        /*
         * set-up mock
         */
        expect(webpageDao.findWebpagesWithoutRelations()).andReturn(webpageEntity);
        expect(wrapper.entityCollectionToDtoCollection((Collection) webpageEntity)).andReturn((Collection) webpageDto);
        //
        replay(webpageDao);
        replay(webpageDto);
        replay(webpageEntity);
        replay(wrapper);
        /*
         * run test
         */
        result = instance.getWebpagesWithoutRelations();
        /*
         * asserts
         */
        assertEquals(webpageDto, result);
        /*
         * verify mock
         */
        verify(webpageDao);
        verify(webpageDto);
        verify(webpageEntity);
        verify(wrapper);
    }
}
