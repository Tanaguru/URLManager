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
package org.opens.urlmanager.entity.dao.request;

import junit.framework.Test;
import junit.framework.TestSuite;
import org.opens.urlmanager.entity.request.Request;
import org.opens.urlmanager.entity.request.RequestImpl;
import org.opens.urlmanager.persistence.AbstractDaoTestCase;

/**
 *
 * @author bcareil
 */
public class RequestDAOImplTest extends AbstractDaoTestCase {
    
    private RequestDAO requestDAO;
    
    public RequestDAOImplTest(String testName) {
        super(testName, "src/test/resources/dataSets/flatDataSet.xml");
        requestDAO = (RequestDAO) springBeanFactory.getBean("requestDAO");
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(RequestDAOImplTest.class);
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
     * Test of getEntityClass method, of class RequestDAOImpl.
     */
    public void testGetEntityClass() {
        System.out.println("getEntityClass");
        RequestDAOImpl instance = new RequestDAOImpl();
        Class expResult = RequestImpl.class;
        Class result = instance.getEntityClass();
        assertEquals(expResult, result);
    }

    /**
     * Test of findRequestFromLabel method, of class RequestDAOImpl.
     */
    public void testFindRequestFromLabel() {
        System.out.println("findRequestFromLabel");
        Request result;
        
        System.out.println("-> existing request");
        result = requestDAO.findRequestFromLabel("lol related");
        // expected result : The request labalized "lol related" with the Id_Request=3
        assertNotNull(result);
        assertEquals(Long.valueOf(3), result.getId());
        
        System.out.println("-> invalid request");
        result = requestDAO.findRequestFromLabel("blah");
        assertNull(result);
    }
}
