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
package org.opens.urlmanager.rest.operation.command.unserializer.generic;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.LogFactory;
import org.opens.tanaguru.sdk.entity.Entity;
import org.opens.urlmanager.rest.api.IRestUnserializer;
import org.opens.urlmanager.rest.exception.BadClientRequestException;
import org.opens.urlmanager.rest.operation.command.api.IUnserializerCommand;

/**
 *
 * @author bcareil
 */
public class NaiveUnserializerVoter <T extends Entity>
        implements IUnserializerCommand<T> {

    /**
     * Choose the first capable unserializer of the given list of
     * @a unserializers to unserialize the entity of type @a T
     * from the given @a request.
     * 
     * @param <T> The type of the entity to unserialize
     * @param request The resquest to unserialize the entity from
     * @param unserializers The list of unserializer to try
     * @return The unserialized entity
     * @throws BadClientRequestException if no suitable unserializer was found.
     */
    @Override
    public T process(
            HttpServletRequest request,
            List<IRestUnserializer<T>> unserializers
            ) {
        LogFactory.getLog(NaiveUnserializerVoter.class).debug(
                "looking for capable unserializer for request method " +
                request.getMethod() + " among " + unserializers.toString()
                );
        for (IRestUnserializer<T> unserializer : unserializers) {
            if (unserializer.canUnserializeRequest(request)) {
                return unserializer.unserialize(request);
            }
        }
        throw new BadClientRequestException("No suitable unserializer found");        
    }
}
