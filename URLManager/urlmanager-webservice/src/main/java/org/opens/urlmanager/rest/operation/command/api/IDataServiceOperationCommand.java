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
import org.opens.tanaguru.sdk.entity.service.GenericDataService;

/**
 *
 * @author bcareil
 */
public interface IDataServiceOperationCommand<T extends Entity> {

    /**
     * Perform a persistence operation using the given genericDataService and
     * entity.
     * <p>
     * Note: The entity argument may be null.
     *
     * @param genericDataService The dataService to use
     * @param entity The entity to handle (may be null)
     * @return The object that may be processed by a IEntityTransformer before
     * being added to the model.
     */
    Object process(
            GenericDataService<T, Long> genericDataService,
            T entity
            );
}
