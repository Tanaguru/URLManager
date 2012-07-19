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

import java.util.Collection;
import java.util.Set;
import org.opens.tanaguru.sdk.entity.Entity;
import org.opens.tanaguru.sdk.entity.service.AbstractGenericDataService;

/**
 *
 * @author bcareil
 */
public abstract class AbstractGenericDataServiceWithDTO <T extends Entity>
        extends AbstractGenericDataService<T, Long> {
    
    protected EntityDTOWrapper wrapper;
    
    /*
     * Accessors
     */

    public EntityDTOWrapper getWrapper() {
        return wrapper;
    }

    public void setWrapper(EntityDTOWrapper wrapper) {
        this.wrapper = wrapper;
    }
    
    /*
     * override AbstractGenericDataService to wrap the entity
     */

    @Override
    public T create() {
        return (T) wrapper.entityToDto(super.create());
    }

    @Override
    public void create(T entity) {
        super.create((T) wrapper.dtoToEntity(entity));
    }

    @Override
    public void delete(T entity) {
        super.delete((T) wrapper.dtoToEntity(entity));
    }

//    @Override
//    public void delete(Long key) {
//        super.delete(key);
//    }

    @Override
    public void delete(Set<T> entitySet) {
        super.delete((Set) wrapper.dtoCollectionToEntityCollection((Collection) entitySet));
    }

    @Override
    public Collection<? extends T> findAll() {
        return (Collection) wrapper.entityCollectionToDtoCollection((Collection) super.findAll());
    }

    @Override
    public T read(Long key) {
        return (T) wrapper.entityToDto(super.read(key));
    }

    @Override
    public T saveOrUpdate(T entity) {
        return (T) wrapper.entityToDto(super.saveOrUpdate((T) wrapper.dtoToEntity(entity)));
    }

    @Override
    public Set<T> saveOrUpdate(Set<T> entitySet) {
        return (Set) wrapper.entityCollectionToDtoCollection(
                (Collection) super.saveOrUpdate(
                    (Set) wrapper.dtoCollectionToEntityCollection(
                        (Collection) entitySet)
                    )
                );
    }

//    @Override
//    public void setEntityDao(GenericDAO<T, Long> entityDao) {
//        super.setEntityDao(entityDao);
//    }

//    @Override
//    public void setEntityFactory(GenericFactory<T> entityFactory) {
//        super.setEntityFactory(entityFactory);
//    }

    @Override
    public T update(T entity) {
        return (T) wrapper.entityToDto(super.update((T) wrapper.dtoToEntity(entity)));
    }
    
}
