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
package org.opens.urlmanager.rest.api;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author bcareil
 */
public interface IRestUnserializer <T> {
    
    /**
     * Evaluate if the instance can handle the type of the specified request.
     * 
     * @param request The HTTP request to process
     * @return true whether this instance can handle this kind of request.
     */
    Boolean canUnserializeRequest(HttpServletRequest request);
    
    /**
     * Unserialize the entity enclosed in the given request
     * 
     * @param request The HTTP request to process
     * @return The unserialized entity
     * @throws GenericRestException to handle errors and return the appropriate
     *                              HTTP error code
     */
    T unserialize(HttpServletRequest request);
}
