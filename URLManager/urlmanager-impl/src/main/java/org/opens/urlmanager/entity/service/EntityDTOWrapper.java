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
package org.opens.urlmanager.entity.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LazyInitializationException;

/**
 *
 * @author bcareil
 */
public class EntityDTOWrapper {
    
    /*
     * The two following members store the classes needed to make the
     * correspondance between the Entity and the DTO layer
     */
    private Map<Class, Class> dtoClassForEntityClass;
    private Map<Class, Class> entityClassForDtoClass;
    /*
     * The two following members are filled at runtime.
     */
    private Map<Object, Object> entityForDto;
    private Map<Object, Object> dtoForEntity;
    
    /*
     * Accessors
     */

    public Map<Class, Class> getDtoClassForEntityClass() {
        return dtoClassForEntityClass;
    }

    public void setDtoClassForEntityClass(Map<Class, Class> dtoClassForEntityClass) {
        this.dtoClassForEntityClass = dtoClassForEntityClass;
    }

    public Map<Class, Class> getEntityClassForDtoClass() {
        return entityClassForDtoClass;
    }

    public void setEntityClassForDtoClass(Map<Class, Class> entityClassForDtoClass) {
        this.entityClassForDtoClass = entityClassForDtoClass;
    }
    
    /*
     * Reflexivity methods
     */
    
    private boolean isGetter(Method m) {
        return (m.getName().startsWith("get")
                && m.getName().length() > 3
                && m.getParameterTypes().length == 0
                );
    }
    
    private boolean isSetterOf(Method setter, Method getter) {
        return (setter.getName().startsWith("set")
                && setter.getName().length() == getter.getName().length()
                && setter.getName().endsWith(getter.getName().substring(3))
                && setter.getParameterTypes().length == 1
                && setter.getParameterTypes()[0] == getter.getReturnType()
                && setter.getReturnType() == void.class
                );
    }
    
    public void copyBeanProperties(Object dst, Object org, boolean isEntityToDTO)
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class cDst = dst.getClass();
        Class cOrg = org.getClass();
        
        // TODO: something better than O(n^2)
        for (Method getter : cOrg.getMethods()) {
            if (isGetter(getter)) {
                for (Method setter : cDst.getMethods()) {
                    if (isSetterOf(setter, getter)) {
                        Object value;
                        
                        try {
                            value = getter.invoke(org);
                            
                            if (value != null && Collection.class.isAssignableFrom(value.getClass())) {
                                if (isEntityToDTO) {
                                    setter.invoke(dst, entityCollectionToDtoCollection((Collection) value));
                                } else {
                                    setter.invoke(dst, dtoCollectionToEntityCollection((Collection) value));
                                }
                            } else {
                                setter.invoke(dst, value);
                            }
                        } catch (LazyInitializationException ex) {
                            setter.invoke(dst, (Object) null);
                        }
                        break;
                    }
                }
            }
        }
    }
    
    private Object convert(
            Object entity,
            Map<Object, Object> convertHistory,
            Map<Class, Class> classConvertMap,
            boolean isEntityToDto
            ) {
        Object ret = null;
        
        if (convertHistory.containsKey(entity)) {
            ret = convertHistory.get(entity);
        } else {
            try {
                ret = classConvertMap.get(entity.getClass()).newInstance();
                
                convertHistory.put(entity, ret);
                copyBeanProperties(ret, entity, isEntityToDto);
            } catch (InstantiationException ex) {
                LogFactory.getLog(AbstractGenericDataServiceWithDTO.class).fatal("Unable to instantiate the given collection type, will return null.", ex);
            } catch (IllegalAccessException ex) {
                LogFactory.getLog(AbstractGenericDataServiceWithDTO.class).fatal("Unable to instantiate the given collection type, will return null.", ex);
            } catch (InvocationTargetException ex) {
                LogFactory.getLog(AbstractGenericDataServiceWithDTO.class).fatal("Unable to instantiate the given collection type, will return null.", ex);
            }
        }
        return ret;
    }
    
    /*
     * wrappers
     */
    
    public Object entityToDto(Object entity) {
        boolean isMapCreator = (dtoForEntity == null);
        Object ret;
        
        if (isMapCreator) {
            dtoForEntity = new HashMap<Object, Object>();
        }
        ret = convert(entity, dtoForEntity, dtoClassForEntityClass, true);
        if (isMapCreator) {
            dtoForEntity = null;
        }
        return ret;
    }
    
    public Object dtoToEntity(Object dto) {
        boolean isMapCreator = (entityForDto == null);
        Object ret;
        
        if (isMapCreator) {
            entityForDto = new HashMap<Object, Object>();
        }
        ret = convert(dto, entityForDto, entityClassForDtoClass, false);
        if (isMapCreator) {
            entityForDto = null;
        }
        return ret;
    }
    
    public Collection<Object> entityCollectionToDtoCollection(Collection<Object> entities) {
        Collection collection = null;
        
        try {
            collection = entities.getClass().newInstance();
            
            for (Object entity : entities) {
                collection.add(entityToDto(entity));
            }
        } catch (InstantiationException ex) {
            LogFactory.getLog(AbstractGenericDataServiceWithDTO.class).fatal("Unable to instantiate the given collection type, will return null.", ex);
        } catch (IllegalAccessException ex) {
            LogFactory.getLog(AbstractGenericDataServiceWithDTO.class).fatal("Unable to instantiate the given collection type, will return null.", ex);
        }
        return collection;
    }
    
    public Collection<Object> dtoCollectionToEntityCollection(Collection<Object> dtos) {
        Collection collection = null;
        
        try {
            collection = dtos.getClass().newInstance();
            
            for (Object dto : dtos) {
                collection.add(dtoToEntity(dto));
            }
        } catch (InstantiationException ex) {
            LogFactory.getLog(AbstractGenericDataServiceWithDTO.class).fatal("Unable to instantiate the given collection type, will return null.", ex);
        } catch (IllegalAccessException ex) {
            LogFactory.getLog(AbstractGenericDataServiceWithDTO.class).fatal("Unable to instantiate the given collection type, will return null.", ex);
        }
        return collection;
    }
}
