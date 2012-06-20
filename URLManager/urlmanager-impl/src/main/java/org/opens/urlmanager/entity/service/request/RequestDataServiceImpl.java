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
package org.opens.urlmanager.entity.service.request;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.LogFactory;
import org.opens.urlmanager.entity.dao.request.RequestDAO;
import org.opens.urlmanager.entity.request.Request;
import org.opens.urlmanager.entity.service.LocaleAndTagAssociatedDataService;
import org.opens.urlmanager.entity.service.exception.EntityNotFoundException;
import org.opens.urlmanager.entity.webpage.Webpage;

/**
 *
 * @author bcareil
 */
public class RequestDataServiceImpl extends LocaleAndTagAssociatedDataService<Request>
    implements RequestDataService {
    
    public Request getRequestFromLabel(String label) {
        return ((RequestDAO)entityDao).findRequestFromLabel(label);
    }

    public Collection<Webpage> getMatchingWebpages(Request request) {
        if (request.getId() != null && request.getId() != 0) {
            preprocessRequest(request);
        } else {
            request.setTags(preprocessTags(request.getTags(), false));
            request.setLocales(preprocessLocales(request.getLocales()));
        }
        return ((RequestDAO)entityDao).findMatchingWebpages(request);
    }

    @Override
    public void create(Request entity) {
        entity.setTags(preprocessTags(entity.getTags(), false));
        entity.setLocales(preprocessLocales(entity.getLocales()));
        super.create(entity);
    }

    @Override
    public Request update(Request entity) {
        entity.setTags(preprocessTags(entity.getTags(), false));
        entity.setLocales(preprocessLocales(entity.getLocales()));
        return super.update(entity);
    }

    
    
    private void preprocessRequest(Request request) {
        Request persistedRequest = this.read(request.getId());

        if (persistedRequest == null) {
            throw new EntityNotFoundException("request", "invalid id");
        }
        try {
            BeanUtils.copyProperties(request, persistedRequest);
        } catch (Exception ex) {
            LogFactory.getLog(RequestDataServiceImpl.class).error("Unable to copy 'Request' bean", ex);
        }
    }    
    
}
