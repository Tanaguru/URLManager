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
package org.opens.urlmanager.rest.operation.command.service.entity.tag;

import org.apache.commons.logging.LogFactory;
import org.opens.tanaguru.sdk.entity.service.GenericDataService;
import org.opens.urlmanager.entity.service.tag.TagDataService;
import org.opens.urlmanager.entity.tag.Tag;
import org.opens.urlmanager.rest.exception.InternalErrorException;
import org.opens.urlmanager.rest.operation.command.api.IDataServiceOperationCommand;

/**
 *
 * @author bcareil
 */
public class TagDSOGetTagFromLabel
        implements IDataServiceOperationCommand<Tag> {

    @Override
    public Object process(GenericDataService<Tag, Long> genericDataService, Tag entity) {
        try {
            return ((TagDataService)genericDataService).getTagFromLabel(entity.getLabel());
        } catch (ClassCastException e) {
            LogFactory.getLog(TagDSOGetTagFromLabel.class).debug("Invalid parameter : " + e.getMessage());
            throw new InternalErrorException("Internal error", e);
        }
    }
    
}
