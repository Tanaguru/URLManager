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
package org.opens.urlmanager.rest.operation.command.control.unserialize.generic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.opens.tanaguru.sdk.entity.Entity;
import org.opens.urlmanager.rest.operation.command.control.generic.AControlOperationDecorator;

/**
 *
 * @author bcareil
 */
public class ControlOperationSetNullBooleanToFalse <T extends Entity>
        extends AControlOperationDecorator<T> {
    
    @Override
    public void process(T entity) {
        super.process(entity);
        
        // FIXME: do something better than a O(n^2) complexity.
        Method[] methods = entity.getClass().getMethods();
        
        // look for the setter
        for (Method setter : methods) {
            if (validateSetter(setter)) {
                // look for the getter
                String getterName;

                getterName = "get" + setter.getName().substring(3);                    
                for (Method getter : methods) {
                    if (validateGetter(getter, getterName)) {
                        // getter and setter found, apply logic, finally
                        setNullBooleanToFalse(entity, getter, setter);
                        // no need to iterate over getter, look for another
                        // property instead
                        break;
                    }
                }
            }
        }
    }

    /**
     * 
     * @param methodName
     * @return 
     */
    private boolean validateSetterName(String methodName) {
        if (methodName.length() <= 3)
            return false;
        if ("set".equals(methodName.substring(0, 3)) == false)
            return false;
        if (Character.isUpperCase(methodName.charAt(3)) == false)
            return false;
        return true;
    }

    /**
     * 
     * @param setter
     * @return true If the given method begin with "set[A-Z]" and take a single
     *              Boolean argument.
     */
    private boolean validateSetter(Method setter) {
        Class[] parameterTypes;
        
        if (validateSetterName(setter.getName()) == false)
            return false;
        parameterTypes = setter.getParameterTypes();
        if (parameterTypes.length != 1)
            return false;
        if (parameterTypes[0] != Boolean.class)
            return false;
        return true;
    }

    /**
     * 
     * @param getter
     * @param expGetterName
     * @return true If the name of the given getter match the given
     *              expGetterName AND does not take any argument AND
     *              its return type is Boolean.
     */
    private boolean validateGetter(Method getter, String expGetterName) {
        if (expGetterName.equals(getter.getName()) == false)
            return false;
        if (getter.getParameterTypes().length != 0)
            return false;
        if (getter.getReturnType() != Boolean.class)
            return false;
        return true;
    }

    /**
     * Set the property of the given entity to false if it is evaluated to null.
     * 
     * @param entity The entity to modifiy
     * @param getter The getter of the property
     * @param setter The setter of the property
     */
    private void setNullBooleanToFalse(T entity, Method getter, Method setter) {
        try {
            Boolean propertyValue;

            propertyValue = (Boolean) getter.invoke(entity);
            if (propertyValue == null) {
                setter.invoke(entity, Boolean.FALSE);
            }
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ControlOperationSetNullBooleanToFalse.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(ControlOperationSetNullBooleanToFalse.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(ControlOperationSetNullBooleanToFalse.class.getName()).log(Level.SEVERE, null, ex);
        }                                
    }
    
}
