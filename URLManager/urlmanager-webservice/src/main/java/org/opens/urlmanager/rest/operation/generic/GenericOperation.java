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
import org.apache.commons.logging.LogFactory;
import org.opens.tanaguru.sdk.entity.Entity;
import org.opens.tanaguru.sdk.entity.service.GenericDataService;
import org.opens.urlmanager.rest.api.IRestOperation;
import org.opens.urlmanager.rest.api.IRestUnserializer;
import org.opens.urlmanager.rest.operation.command.api.IControlOperationCommand;
import org.opens.urlmanager.rest.operation.command.api.IDataServiceOperationCommand;
import org.opens.urlmanager.rest.operation.command.api.ITransformerCommand;
import org.opens.urlmanager.rest.operation.command.api.IUnserializerCommand;
import org.springframework.ui.Model;

/**
 * Bean implementing a generic and fully configurable REST operation
 * 
 * The configuration of the beans is done by setting its properties.
 * The following are required:
 * - operationName : the name of the operation (eg. list, read, delete)
 * - modelAttributeName : the name of the attribute to add to the model
 * - view : the view to return
 * - genericDataService : the data service to use
 * - dataServiceOperation : the data service operation
 * 
 * The following are optional:
 * - postUnserializeControl : the command to run after the unserialization
 * - postDataServiceOperationControl : the command to run after
 *                                     the data service operation, on the
 *                                     entity
 * - entityTransformer : the command to run to transform the return value
 *                       of the data service operation
 * - unserializerCommand : the command to run after the unserialization,
 *                         on the unserialized entity
 * 
 * The following is required if unserializerCommand AND
 * postUnserializeControl are set :
 * - unserializers : the list of available unserializers.
 * 
 * NOTE: if only one of unserializerCommand or postUnserializeControl is
 *       set, the init method will issue a warning.
 * 
 * @author bcareil
 */
public class GenericOperation<T extends Entity>
        implements IRestOperation {

    private IControlOperationCommand<T> postUnserializeControl;
    private IControlOperationCommand<T> postDataServiceOperationControl;
    private IDataServiceOperationCommand<T> dataServiceOperation;
    private ITransformerCommand<T> entityTransformer;
    private IUnserializerCommand<T> unserializerCommand;
    private GenericDataService<T, Long> genericDataService;
    private List<IRestUnserializer<T>> unserializers;
    private String modelAttributeName;
    private String modelEntityName;
    private String operationName;
    private String view;

//    public void init() {
//        boolean postUnserializControlNull = (postUnserializeControl == null);
//        //boolean postDataServiceOperationControlNull = (postDataServiceOperationControl == null);
//        boolean dataServiceOperationNull = (dataServiceOperation == null);
//        //boolean entityTransformerNull = (entityTransformer == null);
//        boolean unserializerCommandNull = (unserializerCommand == null);
//        boolean genericDataServiceNull = (genericDataService == null);
//        boolean unserializersNull = (unserializers == null);
//        boolean modelAttributeNameNull = (modelAttributeName == null);
//        //boolean modelEntityNameNull = (modelEntityName == null);
//        boolean operationNameNull = (operationName == null);
//        boolean viewNull = (view == null);
//        
//        // check for required properties
//        if (operationNameNull) {
//            throw new NullPointerException(
//                    "The required operationName property is null");
//        }
//        if (genericDataServiceNull) {
//            throw new NullPointerException(
//                    "The required genericDataService property is null for " +
//                    "the operation " + operationName);
//        }
//        if (dataServiceOperationNull) {
//            throw new NullPointerException(
//                    "The required dataServiceOperation property is null for " +
//                    "the operation " + operationName);
//        }
//        if (modelAttributeNameNull) {
//            throw new NullPointerException(
//                   "The required modelAttributeName property is null for " +
//                    "the operation " + operationName);
//        }
//        if (viewNull) {
//            throw new NullPointerException(
//                    "The required view property is null for " +
//                    "the operation " + operationName);
//        }
//        if (unserializerCommandNull ^ postUnserializControlNull) {
//            // warning
//            LogFactory.getLog(GenericOperation.class).warn(
//                    "One of property unserializerCommand and " +
//                    "postUnserializeControl is set and not the other. " +
//                    "There will be no unserialization for the operation " +
//                    operationName);
//        } else if (
//                unserializerCommandNull == false &&
//                postUnserializControlNull == false
//                ) {
//            // info & required
//            LogFactory.getLog(GenericOperation.class).debug(
//                    "Unserialization activated for operation " +
//                    operationName);
//            if (unserializersNull) {
//                throw new NullPointerException(
//                        "The property unserializers, required in this " +
//                        "context, is null for operation " + operationName);
//            }
//        }
//    }
    
    /**
     * Execute the generic patern of a REST operation.
     *
     * The pattern is the following
     *
     * if both not null { entity = unserializerCommand;
     *                    postUnserializeControl(entity) }
     * required { attribute = dataServiceOperation(entity) }
     * if not null { postDataServiceOperationControl(entity) }
     * if not null { attribute = entityTransformer(attribute) }
     * if not null { model.addAttribute(modelEntityName, entity) }
     * required { model.addAttribute(modelAttributeName, attribute) }
     * required { return view }
     *
     * The role of the unserialiwerCommand is to find a suitable unserializer
     * among the given unserializer list to extract and return the entity from
     * the given HTTP request
     *
     * The role of the postUnserialiwerControl is to perform a control on the
     * entity integrity before the later is processed by the
     * dataServiceOperation
     *
     * The role of the dataServiceOperation is to apply a persistence operation
     * on the given genericDataService and the entity potentialy null and return
     * the attribute that will be displayed but will pass through the
     * entityTransformer first.
     *
     * The postDataServiceControl will process the entity, and NOT the attribute
     * returned by the dataServiceOperation. Later, the entity will be added
     * to the model under the name of modelEntityName.
     *
     * The entityTransformer process the attribute returned by the
     * dataServiceOperation. The return value of this transformer will then be
     * added to the model under the name contained in modelAttributeName.
     *
     * @param model The spring model
     * @param request The HTTP request to process
     * @return The view variable member
     */
    @Override
    public String process(Model model, HttpServletRequest request) {
        Object attribute;
        T entity = null;

        if (unserializerCommand != null
                && postUnserializeControl != null) {
            entity = unserializerCommand.process(request, unserializers);
            postUnserializeControl.process(entity);
        }

        attribute = dataServiceOperation.process(genericDataService, entity);

        if (postDataServiceOperationControl != null) {
            postDataServiceOperationControl.process(entity);
        }

        if (entityTransformer != null) {
            attribute = entityTransformer.process(attribute);
        }

        if (modelEntityName != null) {
            model.addAttribute(modelEntityName, entity);
        }
        
        model.addAttribute(modelAttributeName, attribute);
        return view;
    }

    public IControlOperationCommand<T> getPostDataServiceOperationControl() {
        return postDataServiceOperationControl;
    }

    public void setPostDataServiceOperationControl(IControlOperationCommand<T> postDataServiceOperationControl) {
        this.postDataServiceOperationControl = postDataServiceOperationControl;
    }

    public IControlOperationCommand<T> getPostUnserializeControl() {
        return postUnserializeControl;
    }

    public void setPostUnserializeControl(IControlOperationCommand<T> postUnserializeControl) {
        this.postUnserializeControl = postUnserializeControl;
    }

    public IDataServiceOperationCommand<T> getDataServiceOperation() {
        return dataServiceOperation;
    }

    public void setDataServiceOperation(IDataServiceOperationCommand<T> dataServiceOperation) {
        this.dataServiceOperation = dataServiceOperation;
    }

    public ITransformerCommand<T> getEntityTransformer() {
        return entityTransformer;
    }

    public void setEntityTransformer(ITransformerCommand<T> entityTransformer) {
        this.entityTransformer = entityTransformer;
    }

    public IUnserializerCommand<T> getUnserializerCommand() {
        return unserializerCommand;
    }

    public void setUnserializerCommand(IUnserializerCommand<T> unserializerCommand) {
        this.unserializerCommand = unserializerCommand;
    }

    public GenericDataService<T, Long> getGenericDataService() {
        return genericDataService;
    }

    public void setGenericDataService(GenericDataService<T, Long> genericDataService) {
        this.genericDataService = genericDataService;
    }

    public List<IRestUnserializer<T>> getUnserializers() {
        return unserializers;
    }

    public void setUnserializers(List<IRestUnserializer<T>> unserializers) {
        this.unserializers = unserializers;
    }

    @Override
    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public String getModelEntityName() {
        return modelEntityName;
    }

    public void setModelEntityName(String modelEntityName) {
        this.modelEntityName = modelEntityName;
    }

    public String getModelAttributeName() {
        return modelAttributeName;
    }

    public void setModelAttributeName(String modelAttributeName) {
        this.modelAttributeName = modelAttributeName;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }
}
