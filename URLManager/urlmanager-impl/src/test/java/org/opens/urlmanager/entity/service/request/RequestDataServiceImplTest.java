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

/**
 *
 * @author bcareil
 */
public class RequestDataServiceImplTest extends TestCase {
    
    RequestDAO mock;
    
    public RequestDataServiceImplTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        mock = createMock(RequestDAO.class);
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of getRequestFromLabel method, of class RequestDataServiceImpl.
     */
    public void testGetRequestFromLabel() {
        System.out.println("getRequestFromLabel");
        String label = "lol related";
        RequestDataServiceImpl instance = new RequestDataServiceImpl();

        /*
         * set-up mock
         */
        // We dont care of the return value since getRequestFromLabel is just a return
        expect(mock.findRequestFromLabel(label)).andReturn(null);
        replay(mock);
        /*
         * run test
         */
        instance.setEntityDao(mock);
        instance.getRequestFromLabel(label);
        /*
         * check mock state
         */
        verify(mock);
    }
}
