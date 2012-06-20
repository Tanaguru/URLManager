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
package org.opens.urlmanager.rest.operation.command.control.generic;

import java.lang.reflect.Method;
import org.apache.commons.logging.LogFactory;
import org.opens.tanaguru.sdk.entity.Entity;
import org.opens.urlmanager.rest.exception.BadClientRequestException;
import org.opens.urlmanager.rest.exception.InternalErrorException;

/**
 *
 * @author bcareil
 */
public class ControlOperationCheckPropertyNotNull <T extends Entity>
        extends AControlOperationDecorator<T> {

    private String property;
    
    @Override
    public void process(T entity) {
        super.process(entity);
        String getterName;
        
        // sanity checks
        assert(property != null);
        assert(property.length() > 0);
        // building getter name
        getterName = "get" + Character.toUpperCase(property.charAt(0));
        if (property.length() > 0) {
            getterName += property.substring(1);
        }
        // call the getter
        Method getter;
        Object propertyValue;
        
        try {
            getter = entity.getClass().getMethod(getterName);
            propertyValue = getter.invoke(entity);
        } catch (Exception ex) {
            LogFactory.getLog(ControlOperationCheckPropertyNotNull.class).debug(
                    "property not found : " + ex.getMessage()
                    );
            throw new InternalErrorException("Internal error", ex);
        }
        // check property value
        if (propertyValue == null) {
            throw new BadClientRequestException("Operation expect id");
        }
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }
    
}
