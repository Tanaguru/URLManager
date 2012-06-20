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
package org.opens.urlmanager.rest.operation.generic;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import static org.easymock.EasyMock.*;
import org.opens.tanaguru.sdk.entity.service.GenericDataService;
import org.opens.urlmanager.entity.tag.TagImpl;
import org.opens.urlmanager.rest.api.IRestUnserializer;
import org.opens.urlmanager.rest.operation.command.api.IControlOperationCommand;
import org.opens.urlmanager.rest.operation.command.api.IDataServiceOperationCommand;
import org.opens.urlmanager.rest.operation.command.api.ITransformerCommand;
import org.opens.urlmanager.rest.operation.command.api.IUnserializerCommand;
import org.springframework.ui.Model;

/**
 *
 * @author bcareil
 */
public class GenericOperationTest extends TestCase {
    
    public GenericOperationTest() {
    }

    public static Test suite() {
        return new TestSuite(GenericOperationTest.class);
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
     * Test of init method, of class GenericOperation.
     */
//    public void testInit() {
//        System.out.println("init");
//        /* properties */
//        IControlOperationCommand<TagImpl> postUnserializeControl;
//        IControlOperationCommand<TagImpl> postDataServiceOperationControl;
//        IDataServiceOperationCommand<TagImpl> dataServiceOperation;
//        ITransformerCommand<TagImpl> entityTransformer;
//        IUnserializerCommand<TagImpl> unserializerCommand;
//        GenericDataService<TagImpl, Long> genericDataService;
//        List<IRestUnserializer<TagImpl>> unserializers;
//        String modelAttributeName;
//        String operationName;
//        String view;
//        /* instance */
//        GenericOperation<TagImpl> instance;
//
//        /*
//         * error UC 0 : invalid operationName
//         */
//        System.out.println("-> invalid operation name");
//        /* initialization */
//        postUnserializeControl = createMock(IControlOperationCommand.class);
//        postDataServiceOperationControl = createMock(IControlOperationCommand.class);
//        dataServiceOperation = createMock(IDataServiceOperationCommand.class);
//        entityTransformer = createMock(ITransformerCommand.class);
//        unserializerCommand = createMock(IUnserializerCommand.class);
//        genericDataService = createMock(GenericDataService.class);
//        unserializers = createMock(List.class);
//        modelAttributeName = "";
//        operationName = null;
//        view = "";
//        /* set */
//        instance = new GenericOperation<TagImpl>();
//        instance.setPostUnserializeControl(postUnserializeControl);
//        instance.setPostDataServiceOperationControl(postDataServiceOperationControl);
//        instance.setDataServiceOperation(dataServiceOperation);
//        instance.setEntityTransformer(entityTransformer);
//        instance.setUnserializerCommand(unserializerCommand);
//        instance.setGenericDataService(genericDataService);
//        instance.setUnserializers(unserializers);
//        instance.setModelAttributeName(modelAttributeName);
//        instance.setOperationName(operationName);
//        instance.setView(view);
//
//        try {
//            instance.init();
//            assertFalse(true);
//        } catch (NullPointerException e) {
//            assertTrue(true);
//        }
//
//        /*
//         * error UC 1 : invalid data service
//         */
//        System.out.println("-> invalid data service");
//        /* initialization */
//        postUnserializeControl = createMock(IControlOperationCommand.class);
//        postDataServiceOperationControl = createMock(IControlOperationCommand.class);
//        dataServiceOperation = createMock(IDataServiceOperationCommand.class);
//        entityTransformer = createMock(ITransformerCommand.class);
//        unserializerCommand = createMock(IUnserializerCommand.class);
//        genericDataService = null;
//        unserializers = createMock(List.class);
//        modelAttributeName = "";
//        operationName = "";
//        view = "";
//        /* set */
//        instance = new GenericOperation<TagImpl>();
//        instance.setPostUnserializeControl(postUnserializeControl);
//        instance.setPostDataServiceOperationControl(postDataServiceOperationControl);
//        instance.setDataServiceOperation(dataServiceOperation);
//        instance.setEntityTransformer(entityTransformer);
//        instance.setUnserializerCommand(unserializerCommand);
//        instance.setGenericDataService(genericDataService);
//        instance.setUnserializers(unserializers);
//        instance.setModelAttributeName(modelAttributeName);
//        instance.setOperationName(operationName);
//        instance.setView(view);
//
//        try {
//            instance.init();
//            assertFalse(true);
//        } catch (NullPointerException e) {
//            assertTrue(true);
//        }
//
//        /*
//         * error UC 2 : invalid data service operation
//         */
//        System.out.println("-> invalid data service operation");
//        /* initialization */
//        postUnserializeControl = createMock(IControlOperationCommand.class);
//        postDataServiceOperationControl = createMock(IControlOperationCommand.class);
//        dataServiceOperation = null;
//        entityTransformer = createMock(ITransformerCommand.class);
//        unserializerCommand = createMock(IUnserializerCommand.class);
//        genericDataService = createMock(GenericDataService.class);
//        unserializers = createMock(List.class);
//        modelAttributeName = "";
//        operationName = "";
//        view = "";
//        /* set */
//        instance = new GenericOperation<TagImpl>();
//        instance.setPostUnserializeControl(postUnserializeControl);
//        instance.setPostDataServiceOperationControl(postDataServiceOperationControl);
//        instance.setDataServiceOperation(dataServiceOperation);
//        instance.setEntityTransformer(entityTransformer);
//        instance.setUnserializerCommand(unserializerCommand);
//        instance.setGenericDataService(genericDataService);
//        instance.setUnserializers(unserializers);
//        instance.setModelAttributeName(modelAttributeName);
//        instance.setOperationName(operationName);
//        instance.setView(view);
//
//        try {
//            instance.init();
//            assertFalse(true);
//        } catch (NullPointerException e) {
//            assertTrue(true);
//        }
//
//        /*
//         * error UC 3 : invalid model attribute name
//         */
//        System.out.println("-> invalid attribute name");
//        /* initialization */
//        postUnserializeControl = createMock(IControlOperationCommand.class);
//        postDataServiceOperationControl = createMock(IControlOperationCommand.class);
//        dataServiceOperation = createMock(IDataServiceOperationCommand.class);
//        entityTransformer = createMock(ITransformerCommand.class);
//        unserializerCommand = createMock(IUnserializerCommand.class);
//        genericDataService = createMock(GenericDataService.class);
//        unserializers = createMock(List.class);
//        modelAttributeName = null;
//        operationName = "";
//        view = "";
//        /* set */
//        instance = new GenericOperation<TagImpl>();
//        instance.setPostUnserializeControl(postUnserializeControl);
//        instance.setPostDataServiceOperationControl(postDataServiceOperationControl);
//        instance.setDataServiceOperation(dataServiceOperation);
//        instance.setEntityTransformer(entityTransformer);
//        instance.setUnserializerCommand(unserializerCommand);
//        instance.setGenericDataService(genericDataService);
//        instance.setUnserializers(unserializers);
//        instance.setModelAttributeName(modelAttributeName);
//        instance.setOperationName(operationName);
//        instance.setView(view);
//
//        try {
//            instance.init();
//            assertFalse(true);
//        } catch (NullPointerException e) {
//            assertTrue(true);
//        }
//
//        /*
//         * error UC 4 : invalid view
//         */
//        System.out.println("-> invalid view");
//        /* initialization */
//        postUnserializeControl = createMock(IControlOperationCommand.class);
//        postDataServiceOperationControl = createMock(IControlOperationCommand.class);
//        dataServiceOperation = createMock(IDataServiceOperationCommand.class);
//        entityTransformer = createMock(ITransformerCommand.class);
//        unserializerCommand = createMock(IUnserializerCommand.class);
//        genericDataService = createMock(GenericDataService.class);
//        unserializers = createMock(List.class);
//        modelAttributeName = "";
//        operationName = "";
//        view = null;
//        /* set */
//        instance = new GenericOperation<TagImpl>();
//        instance.setPostUnserializeControl(postUnserializeControl);
//        instance.setPostDataServiceOperationControl(postDataServiceOperationControl);
//        instance.setDataServiceOperation(dataServiceOperation);
//        instance.setEntityTransformer(entityTransformer);
//        instance.setUnserializerCommand(unserializerCommand);
//        instance.setGenericDataService(genericDataService);
//        instance.setUnserializers(unserializers);
//        instance.setModelAttributeName(modelAttributeName);
//        instance.setOperationName(operationName);
//        instance.setView(view);
//
//        try {
//            instance.init();
//            assertFalse(true);
//        } catch (NullPointerException e) {
//            assertTrue(true);
//        }
//
//        /*
//         * error UC 5 : invalid unserializers with unserialization active
//         */
//        System.out.println("-> invalid unserializers with unserialize active");
//        /* initialization */
//        postUnserializeControl = createMock(IControlOperationCommand.class);
//        postDataServiceOperationControl = createMock(IControlOperationCommand.class);
//        dataServiceOperation = createMock(IDataServiceOperationCommand.class);
//        entityTransformer = createMock(ITransformerCommand.class);
//        unserializerCommand = createMock(IUnserializerCommand.class);
//        genericDataService = createMock(GenericDataService.class);
//        unserializers = null;
//        modelAttributeName = "";
//        operationName = "";
//        view = "";
//        /* set */
//        instance = new GenericOperation<TagImpl>();
//        instance.setPostUnserializeControl(postUnserializeControl);
//        instance.setPostDataServiceOperationControl(postDataServiceOperationControl);
//        instance.setDataServiceOperation(dataServiceOperation);
//        instance.setEntityTransformer(entityTransformer);
//        instance.setUnserializerCommand(unserializerCommand);
//        instance.setGenericDataService(genericDataService);
//        instance.setUnserializers(unserializers);
//        instance.setModelAttributeName(modelAttributeName);
//        instance.setOperationName(operationName);
//        instance.setView(view);
//
//        try {
//            instance.init();
//            assertFalse(true);
//        } catch (NullPointerException e) {
//            assertTrue(true);
//        }
//
//        /*
//         * UC 6 : invalid unserializers with unserialization disabled
//         */
//        System.out.println("-> invalid unserializers with unserialize disabled");
//        /* initialization */
//        postUnserializeControl = null;
//        postDataServiceOperationControl = createMock(IControlOperationCommand.class);
//        dataServiceOperation = createMock(IDataServiceOperationCommand.class);
//        entityTransformer = createMock(ITransformerCommand.class);
//        unserializerCommand = null;
//        genericDataService = createMock(GenericDataService.class);
//        unserializers = null;
//        modelAttributeName = "";
//        operationName = "";
//        view = "";
//        /* set */
//        instance = new GenericOperation<TagImpl>();
//        instance.setPostUnserializeControl(postUnserializeControl);
//        instance.setPostDataServiceOperationControl(postDataServiceOperationControl);
//        instance.setDataServiceOperation(dataServiceOperation);
//        instance.setEntityTransformer(entityTransformer);
//        instance.setUnserializerCommand(unserializerCommand);
//        instance.setGenericDataService(genericDataService);
//        instance.setUnserializers(unserializers);
//        instance.setModelAttributeName(modelAttributeName);
//        instance.setOperationName(operationName);
//        instance.setView(view);
//
//        try {
//            instance.init();
//            assertTrue(true);
//        } catch (Exception e) {
//            assertFalse(true);
//        }
//    }

    /**
     * Test of process method, of class GenericOperation.
     */
    public void testProcess() {
        System.out.println("process");
        /* parameters */
        Model model;
        HttpServletRequest request;
        /* properties */
        IControlOperationCommand<TagImpl> postUnserializeControl;
        IControlOperationCommand<TagImpl> postDataServiceOperationControl;
        IDataServiceOperationCommand<TagImpl> dataServiceOperation;
        ITransformerCommand<TagImpl> entityTransformer;
        IUnserializerCommand<TagImpl> unserializerCommand;
        GenericDataService<TagImpl, Long> genericDataService;
        List<IRestUnserializer<TagImpl>> unserializers;
        String modelAttributeName;
        String modelEntityName;
        String operationName;
        String view;
        /* instance */
        GenericOperation<TagImpl> instance;
        /* others */
        String expResult;
        String result;
        TagImpl entity;
        Object attribute;
        Object transformedAttribute;

        /*
         * nominal UC 0 : with only the required properties set.
         */
        System.out.println("-> nominal UC 0 : the easiest");
        /* initialization */
        model = createMock(Model.class);
        request = createMock(HttpServletRequest.class);
        postUnserializeControl = null;
        postDataServiceOperationControl = null;
        dataServiceOperation = createMock(IDataServiceOperationCommand.class);
        entityTransformer = null;
        unserializerCommand = null;
        genericDataService = createMock(GenericDataService.class);
        unserializers = null;
        modelAttributeName = "tag";
        modelEntityName = null;
        operationName = "read";
        view = "tagview";
        instance = new GenericOperation<TagImpl>();
        expResult = view;
        entity = null;
        attribute = createMock(TagImpl.class);
        transformedAttribute = attribute;
        /* initializing instance */
        instance.setPostUnserializeControl(postUnserializeControl);
        instance.setPostDataServiceOperationControl(postDataServiceOperationControl);
        instance.setDataServiceOperation(dataServiceOperation);
        instance.setEntityTransformer(entityTransformer);
        instance.setUnserializerCommand(unserializerCommand);
        instance.setGenericDataService(genericDataService);
        instance.setUnserializers(unserializers);
        instance.setModelAttributeName(modelAttributeName);
        instance.setModelEntityName(modelEntityName);
        instance.setOperationName(operationName);
        instance.setView(view);
        /* creating scenarion */
        expect(dataServiceOperation.process(genericDataService, entity)).andReturn(attribute);
        expect(model.addAttribute(modelAttributeName, transformedAttribute)).andReturn(model);
        /* activating mock */
        replay(model);
        replay(request);
        //replay(postUnserializeControl);
        //replay(postDataServiceOperationControl);
        replay(dataServiceOperation);
        //replay(entityTransformer);
        //replay(unserializerCommand);
        replay(genericDataService);
        //replay(unserializers);
        //replay(entity);
        replay(attribute);
        //replay(transformedAttribute);
       
        /* running test */
        result = instance.process(model, request);
        /* check */
        assertEquals(expResult, result);
        verify(model);
        verify(request);
        //verify(postUnserializeControl);
        //verify(postDataServiceOperationControl);
        verify(dataServiceOperation);
        //verify(entityTransformer);
        //verify(unserializerCommand);
        verify(genericDataService);
        //verify(unserializers);
        //verify(entity);
        verify(attribute);
        //verify(transformedAttribute);


        /*
         * nominal UC 1 : with post data service transform.
         */
        System.out.println("-> nominal UC 1 : with transform");
        /* initialization */
        model = createMock(Model.class);
        request = createMock(HttpServletRequest.class);
        postUnserializeControl = null;
        postDataServiceOperationControl = null;
        dataServiceOperation = createMock(IDataServiceOperationCommand.class);
        entityTransformer = createMock(ITransformerCommand.class);
        unserializerCommand = null;
        genericDataService = createMock(GenericDataService.class);
        unserializers = null;
        modelAttributeName = "taglist";
        modelEntityName = null;
        operationName = "list";
        view = "taglistview";
        instance = new GenericOperation<TagImpl>();
        expResult = view;
        entity = null;
        attribute = createMock(TagImpl.class);
        transformedAttribute = createMock(List.class);
        /* initializing instance */
        instance.setPostUnserializeControl(postUnserializeControl);
        instance.setPostDataServiceOperationControl(postDataServiceOperationControl);
        instance.setDataServiceOperation(dataServiceOperation);
        instance.setEntityTransformer(entityTransformer);
        instance.setUnserializerCommand(unserializerCommand);
        instance.setGenericDataService(genericDataService);
        instance.setUnserializers(unserializers);
        instance.setModelAttributeName(modelAttributeName);
        instance.setModelEntityName(modelEntityName);
        instance.setOperationName(operationName);
        instance.setView(view);
        /* creating scenarion */
        expect(dataServiceOperation.process(genericDataService, entity)).andReturn(attribute);
        expect(entityTransformer.process(attribute)).andReturn(transformedAttribute);
        expect(model.addAttribute(modelAttributeName, transformedAttribute)).andReturn(model);
        /* activating mock */
        replay(model);
        replay(request);
        //replay(postUnserializeControl);
        //replay(postDataServiceOperationControl);
        replay(dataServiceOperation);
        replay(entityTransformer);
        //replay(unserializerCommand);
        replay(genericDataService);
        //replay(unserializers);
        //replay(entity);
        replay(attribute);
        replay(transformedAttribute);
        
        /* running test */
        result = instance.process(model, request);
        /* check */
        assertEquals(expResult, result);
        verify(model);
        verify(request);
        //verify(postUnserializeControl);
        //verify(postDataServiceOperationControl);
        verify(dataServiceOperation);
        verify(entityTransformer);
        //verify(unserializerCommand);
        verify(genericDataService);
        //verify(unserializers);
        //verify(entity);
        verify(attribute);
        verify(transformedAttribute);
        
        /*
         * nominal UC 2 : with unserialization and post data service control.
         */
        System.out.println("-> nominal UC 2 : with unserialization");
        /* initialization */
        model = createMock(Model.class);
        request = createMock(HttpServletRequest.class);
        postUnserializeControl = createMock(IControlOperationCommand.class);
        postDataServiceOperationControl = createMock(IControlOperationCommand.class);
        dataServiceOperation = createMock(IDataServiceOperationCommand.class);
        entityTransformer = null;
        unserializerCommand = createMock(IUnserializerCommand.class);
        genericDataService = createMock(GenericDataService.class);
        unserializers = createMock(List.class);
        modelAttributeName = "tag";
        modelEntityName = null;
        operationName = "read";
        view = "tagview";
        instance = new GenericOperation<TagImpl>();
        expResult = view;
        entity = createMock(TagImpl.class);
        attribute = entity;
        transformedAttribute = attribute;
        /* initializing instance */
        instance.setPostUnserializeControl(postUnserializeControl);
        instance.setPostDataServiceOperationControl(postDataServiceOperationControl);
        instance.setDataServiceOperation(dataServiceOperation);
        instance.setEntityTransformer(entityTransformer);
        instance.setUnserializerCommand(unserializerCommand);
        instance.setGenericDataService(genericDataService);
        instance.setUnserializers(unserializers);
        instance.setModelAttributeName(modelAttributeName);
        instance.setModelEntityName(modelEntityName);
        instance.setOperationName(operationName);
        instance.setView(view);
        /* creating scenarion */
        expect(unserializerCommand.process(request, unserializers)).andReturn(entity);
        postUnserializeControl.process(entity);
        expect(dataServiceOperation.process(genericDataService, entity)).andReturn(attribute);
        postDataServiceOperationControl.process(entity);
        expect(model.addAttribute(modelAttributeName, transformedAttribute)).andReturn(model);
        /* activating mock */
        replay(model);
        replay(request);
        replay(postUnserializeControl);
        replay(postDataServiceOperationControl);
        replay(dataServiceOperation);
        //replay(entityTransformer);
        replay(unserializerCommand);
        replay(genericDataService);
        replay(unserializers);
        replay(entity);
        //replay(attribute);
        //replay(transformedAttribute);

        
        /* running test */
        result = instance.process(model, request);
        /* check */
        assertEquals(expResult, result);
        verify(model);
        verify(request);
        verify(postUnserializeControl);
        verify(postDataServiceOperationControl);
        verify(dataServiceOperation);
        //verify(entityTransformer);
        verify(unserializerCommand);
        verify(genericDataService);
        verify(unserializers);
        verify(entity);
        //verify(attribute);
        //verify(transformedAttribute);
        
        
        /*
         * nominal UC 3 : complete.
         */
        System.out.println("-> nominal UC 3 : complete");
        /* initialization */
        model = createMock(Model.class);
        request = createMock(HttpServletRequest.class);
        postUnserializeControl = createMock(IControlOperationCommand.class);
        postDataServiceOperationControl = createMock(IControlOperationCommand.class);
        dataServiceOperation = createMock(IDataServiceOperationCommand.class);
        entityTransformer = createMock(ITransformerCommand.class);
        unserializerCommand = createMock(IUnserializerCommand.class);
        genericDataService = createMock(GenericDataService.class);
        unserializers = createMock(List.class);
        modelAttributeName = "tag";
        modelEntityName = "tagEntity";
        operationName = "read";
        view = "tagview";
        instance = new GenericOperation<TagImpl>();
        expResult = view;
        entity = createMock(TagImpl.class);
        attribute = createMock(List.class);
        transformedAttribute = createMock(List.class);
        /* initializing instance */
        instance.setPostUnserializeControl(postUnserializeControl);
        instance.setPostDataServiceOperationControl(postDataServiceOperationControl);
        instance.setDataServiceOperation(dataServiceOperation);
        instance.setEntityTransformer(entityTransformer);
        instance.setUnserializerCommand(unserializerCommand);
        instance.setGenericDataService(genericDataService);
        instance.setUnserializers(unserializers);
        instance.setModelAttributeName(modelAttributeName);
        instance.setModelEntityName(modelEntityName);
        instance.setOperationName(operationName);
        instance.setView(view);
        /* creating scenarion */
        expect(unserializerCommand.process(request, unserializers)).andReturn(entity);
        postUnserializeControl.process(entity);
        expect(dataServiceOperation.process(genericDataService, entity)).andReturn(attribute);
        postDataServiceOperationControl.process(entity);
        expect(entityTransformer.process(attribute)).andReturn(transformedAttribute);
        expect(model.addAttribute(modelEntityName, entity)).andReturn(model);
        expect(model.addAttribute(modelAttributeName, transformedAttribute)).andReturn(model);
        /* activating mock */
        replay(model);
        replay(request);
        replay(postUnserializeControl);
        replay(postDataServiceOperationControl);
        replay(dataServiceOperation);
        replay(entityTransformer);
        replay(unserializerCommand);
        replay(genericDataService);
        replay(unserializers);
        replay(entity);
        replay(attribute);
        replay(transformedAttribute);

        
        /* running test */
        result = instance.process(model, request);
        /* check */
        assertEquals(expResult, result);
        verify(model);
        verify(request);
        verify(postUnserializeControl);
        verify(postDataServiceOperationControl);
        verify(dataServiceOperation);
        verify(entityTransformer);
        verify(unserializerCommand);
        verify(genericDataService);
        verify(unserializers);
        verify(entity);
        verify(attribute);
        verify(transformedAttribute);
    }

}
