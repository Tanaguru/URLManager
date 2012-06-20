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
package org.opens.urlmanager.rest.operation.command.api;

import org.opens.tanaguru.sdk.entity.Entity;

/**
 *
 * @author bcareil
 */
public interface ITransformerCommand <T extends Entity> {
    
    /**
     * Perform an operation on the object returned by a
     * IDataServiceOperationCommand.
     * 
     * @param attribute The attribute returned by the data service
     *                  operation
     * @return The object attribute or a copy, transformed, that will
     *         be pass to the view through the model.
     */
    Object process(Object attribute);
}
