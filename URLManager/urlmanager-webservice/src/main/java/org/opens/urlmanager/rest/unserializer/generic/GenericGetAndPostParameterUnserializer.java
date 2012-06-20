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
package org.opens.urlmanager.rest.unserializer.generic;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.beanutils.BeanUtils;
import org.opens.tanaguru.sdk.entity.Entity;
import org.opens.urlmanager.rest.api.IRestUnserializer;
import org.opens.urlmanager.rest.exception.BadClientRequestException;

/**
 *
 * @author bcareil
 */
public abstract class GenericGetAndPostParameterUnserializer<T extends Entity> implements IRestUnserializer<T> {

    private Class<T> targetClass;

    protected abstract void prePopulate(T bean, Map<String, String[]> parameters);
    
    @Override
    public Boolean canUnserializeRequest(HttpServletRequest request) {
        return (request.getParameter("id") != null &&
                ("POST".equals(request.getMethod()) ||
                 "GET".equals(request.getMethod())));
    }

    @Override
    public T unserialize(HttpServletRequest request) {
        T bean;
        Map httpParameterMap;

        httpParameterMap = new HashMap(request.getParameterMap());
        try {
            bean = targetClass.newInstance();
        } catch (Exception ex) {
            Logger.getLogger(
                    GenericGetAndPostParameterUnserializer.class.getName()
                    ).log(Level.SEVERE, null, ex);
            throw new BadClientRequestException("Unable to unserialize requested entity", ex);
        }
        
        prePopulate(bean, httpParameterMap);
        
        try {
            BeanUtils.populate(bean, httpParameterMap);
        } catch (Exception ex) {
            Logger.getLogger(
                    GenericGetAndPostParameterUnserializer.class.getName()
                    ).log(Level.SEVERE, null, ex);
            throw new BadClientRequestException("Unable to unserialize requested entity", ex);
        }
        return bean;
    }

    public Class<T> getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class<T> targetClass) {
        this.targetClass = targetClass;
    }
    
}
