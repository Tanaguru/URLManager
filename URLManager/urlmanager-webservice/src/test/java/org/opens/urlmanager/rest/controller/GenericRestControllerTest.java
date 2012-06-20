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
package org.opens.urlmanager.rest.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import static org.easymock.EasyMock.*;
import org.opens.urlmanager.rest.api.IRestOperation;
import org.opens.urlmanager.rest.exception.GenericRestException;
import org.opens.urlmanager.rest.exception.ResourceNotFoundException;
import org.springframework.ui.Model;

/**
 *
 * @author bcareil
 */
public class GenericRestControllerTest extends TestCase {
    
    private Map<String, Map<String, IRestOperation>> mockedRestEntitiesOperationMap;
    private Map<String, IRestOperation> mockedRestOperationsMap;
    private IRestOperation mockedOperation;
    
    public GenericRestControllerTest() {
        mockedRestEntitiesOperationMap = createMock(Map.class);
        mockedRestOperationsMap = createMock(Map.class);
        mockedOperation = createMock(IRestOperation.class);
    }

    public static Test suite() {
        TestSuite ts = new TestSuite(GenericRestControllerTest.class);
        return ts;
    }
    
    @Override
    public void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of restRequestHandler method, of class GenericRestController.
     */
    public void testRestRequestHandler() {
        HttpServletRequest request;
        HttpServletResponse response;
        Model model;
        String entity;
        String operation;
        String expResult;
        String result;
        
        System.out.println("restRequestHandler");
        /*
         * nominal UC
         */
        System.out.println("-> nominal UC");
        /* Set up arguments */
        model = createMock(Model.class);
        request = createMock(HttpServletRequest.class);
        response = createMock(HttpServletResponse.class);
        entity = "tag";
        operation = "list";
        expResult = "tagview";
        /* Set up instance */
        GenericRestController instance = new GenericRestController();
        /* Set up mock scenario */
        expect(mockedRestEntitiesOperationMap.get(entity)).andReturn(mockedRestOperationsMap);
        expect(mockedRestOperationsMap.get(operation)).andReturn(mockedOperation);
        expect(mockedOperation.process(model, request)).andReturn(expResult);
        /* replay mocks */
        replay(mockedRestEntitiesOperationMap);
        replay(mockedRestOperationsMap);
        replay(mockedOperation);
        
        instance.setRestEntitiesOperationsMap(mockedRestEntitiesOperationMap);
        /* running test : should return the view stored in expResult */
        result = instance.restRequestHandler(model, request, response, entity, operation);
        /* testing result */
        assertEquals(expResult, result);
        /* verifying mocks */
        verify(mockedRestEntitiesOperationMap);
        verify(mockedRestOperationsMap);
        verify(mockedOperation);
        
        /*
         * error UC 1 : invalid entity
         */
        System.out.println("-> invalid entity");
        /* reseting mock */
        reset(mockedRestEntitiesOperationMap);
        reset(mockedRestOperationsMap);
        reset(mockedOperation);
        /* set up arguments */
        entity = "invalid";
        operation = "list";
        //expResult = "tagview";
        /* set up instance */
        instance  = new GenericRestController();
        /* set up mock scenario */
        expect(mockedRestEntitiesOperationMap.get(entity)).andReturn(null);
        // we do not expect anything else since the process should stop once
        // it detects the entity as invalid
        /* replay mocks */
        replay(mockedRestEntitiesOperationMap);
        replay(mockedRestOperationsMap);
        replay(mockedOperation);
        
        instance.setRestEntitiesOperationsMap(mockedRestEntitiesOperationMap);
        try {
            /* running test : should throw a ResourceNotFoundException */
            instance.restRequestHandler(model, request, response, entity, operation);
            /* testing result */
            assertFalse(true);
        } catch (ResourceNotFoundException e) {
            assertTrue(true);
            verify(mockedRestEntitiesOperationMap);
            verify(mockedRestOperationsMap);
            verify(mockedOperation);
        }
        
        /*
         * error UC 2 : invalid operation
         */
        System.out.println("-> invalid operation");
        /* reseting mock */
        reset(mockedRestEntitiesOperationMap);
        reset(mockedRestOperationsMap);
        reset(mockedOperation);
        /* set up arguments */
        entity = "tag";
        operation = "invalid";
        //expResult = "tagview";
        /* set up instance */
        instance  = new GenericRestController();
        /* set up mock scenario */
        expect(mockedRestEntitiesOperationMap.get(entity)).andReturn(mockedRestOperationsMap);
        expect(mockedRestOperationsMap.get(operation)).andReturn(null);
        // we do not expect anything else since the process should stop once
        // it detects the operation as invalid
        /* replay mocks */
        replay(mockedRestEntitiesOperationMap);
        replay(mockedRestOperationsMap);
        replay(mockedOperation);
        
        instance.setRestEntitiesOperationsMap(mockedRestEntitiesOperationMap);
        try {
            /* running test : should throw a ResourceNotFoundException */
            instance.restRequestHandler(model, request, response, entity, operation);
            /* testing result */
            assertFalse(true);
        } catch (ResourceNotFoundException e) {
            assertTrue(true);
            verify(mockedRestEntitiesOperationMap);
            verify(mockedRestOperationsMap);
            verify(mockedOperation);
        }
    }

    /**
     * Test of handleRestException method, of class GenericRestController.
     */
    public void testHandleRestException() throws Exception {
        System.out.println("handleRestException");
        /* parameters */
        int errorCode = 404;
        String errorMessage = "resource not found";
        /* creating mock */
        GenericRestException exception = createMock(GenericRestException.class);
        HttpServletRequest request = createMock(HttpServletRequest.class);
        HttpServletResponse response = createMock(HttpServletResponse.class);
        /* set up mock */
        expect(exception.getCode()).andReturn(errorCode);
        expect(exception.getMessage()).andReturn(errorMessage);
        response.sendError(errorCode, errorMessage);
        /* create instance */
        GenericRestController instance = new GenericRestController();
        /* playing mock */
        replay(exception);
        replay(request);
        replay(response);
        
        /* running test */
        instance.handleRestException(exception, request, response);
        /* verifying mock */
        verify(exception);
        verify(request);
        verify(response);
    }
}
