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
package org.opens.urlmanager.entity.service.generic;

import java.util.Collection;
import java.util.Set;
import org.opens.tanaguru.sdk.entity.Entity;
import org.opens.tanaguru.sdk.entity.dao.GenericDAO;
import org.opens.tanaguru.sdk.entity.factory.GenericFactory;

/**
 *
 * @author bcareil
 */
public interface GenericDataServiceWithDTO <D, E extends Entity> {

    D create();

    void create(D dto);

    void delete(D dto);

    void delete(Long key);

    void delete(Set<D> dtoSet);

    Collection<D> findAll();

    D read(Long key);

    D saveOrUpdate(D dto);

    Set<D> saveOrUpdate(Set<D> dtoSet);

    D update(D dto);    

    void setEntityDao(GenericDAO<E, Long> dao);

    void setEntityFactory(GenericFactory<E> factory);

}
