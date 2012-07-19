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
package org.opens.urlmanager.entity.service.request;

import junit.framework.TestCase;
import static org.easymock.EasyMock.*;
import org.opens.urlmanager.entity.dao.request.RequestDAO;
import org.opens.urlmanager.entity.request.Request;
import org.opens.urlmanager.entity.service.EntityDTOWrapper;

/**
 *
 * @author bcareil
 */
public class RequestDataServiceImplTest extends TestCase {
    
    RequestDAO requestDao;
    EntityDTOWrapper wrapper;
    
    public RequestDataServiceImplTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        requestDao = createMock(RequestDAO.class);
        wrapper = createMock(EntityDTOWrapper.class);
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    private RequestDataServiceImpl getInstance() {
        RequestDataServiceImpl ret;
        
        ret = new RequestDataServiceImpl();
        ret.setEntityDao(requestDao);
        ret.setWrapper(wrapper);
        return ret;
    }

    /**
     * Test of getRequestFromLabel method, of class RequestDataServiceImpl.
     */
    public void testGetRequestFromLabel() {
        System.out.println("getRequestFromLabel");
        String label = "lol related";
        RequestDataServiceImpl instance = getInstance();
        Request requestEntity = createMock(Request.class);
        Request requestDto = createMock(Request.class);
        Request result;

        /*
         * set-up mock
         */
        expect(requestDao.findRequestFromLabel(label)).andReturn(requestEntity);
        expect(wrapper.entityToDto(requestEntity)).andReturn(requestDto);
        
        replay(requestDao);
        replay(requestEntity);
        replay(requestDto);
        replay(wrapper);
        /*
         * run test
         */
        result = instance.getRequestFromLabel(label);
        /*
         * asserts
         */
        assertEquals(requestDto, result);
        /*
         * check mock state
         */
        verify(requestDao);
        verify(requestEntity);
        verify(requestDto);
        verify(wrapper);
    }
}
