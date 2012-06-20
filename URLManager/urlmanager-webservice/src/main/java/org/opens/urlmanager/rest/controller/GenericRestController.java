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

import java.io.IOException;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.opens.urlmanager.rest.api.IRestOperation;
import org.opens.urlmanager.rest.exception.GenericRestException;
import org.opens.urlmanager.rest.exception.ResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author bcareil
 */
@Controller
public class GenericRestController {
    
    @Resource
    private Map<String, Map<String, IRestOperation>> restEntitiesOperationsMap;
    
    /*
     * Endpoint
     */
    @RequestMapping(
            value = "/{entity}/{operation}/**",
            method = { RequestMethod.GET, RequestMethod.POST }
            )
    public String restRequestHandler(
            Model model,
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable("entity") String entity,
            @PathVariable("operation") String operation
            ) {
        Map<String, IRestOperation> restOperationMap;
        IRestOperation restOperation;
        
        // get operation map
        restOperationMap = restEntitiesOperationsMap.get(entity);
        if (restOperationMap == null) {
            throw new ResourceNotFoundException("Entity " + entity + " not found");
        }

        // get required operation
        restOperation = restOperationMap.get(operation);
        if (restOperation == null) {
            throw new ResourceNotFoundException("Operation " + operation + " not found");
        }
        
        // process operation
        return restOperation.process(model, request);
    }
    
    /*
     * Exception handler
     */
    @ExceptionHandler(GenericRestException.class)
    public void handleRestException(
            GenericRestException e,
            HttpServletRequest request,
            HttpServletResponse response
            ) throws IOException {
        response.sendError(e.getCode(), e.getMessage());
    }
    
    /*
     * Getter & Setters
     */

    public Map<String, Map<String, IRestOperation>> getRestEntitiesOperationsMap() {
        return restEntitiesOperationsMap;
    }

    public void setRestEntitiesOperationsMap(Map<String, Map<String, IRestOperation>> restEntitiesOperationsMap) {
        this.restEntitiesOperationsMap = restEntitiesOperationsMap;
    }
}
