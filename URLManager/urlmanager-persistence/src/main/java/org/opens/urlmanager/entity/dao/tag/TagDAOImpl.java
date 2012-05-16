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
package org.opens.urlmanager.entity.dao.tag;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.opens.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;
import org.opens.urlmanager.entity.tag.Tag;
import org.opens.urlmanager.entity.tag.TagImpl;

/**
 *
 * @author bcareil
 */
public class TagDAOImpl extends AbstractJPADAO<Tag, Long> implements TagDAO {

    public TagDAOImpl() {
        super();
    }
    
    @Override
    protected Class<? extends Tag> getEntityClass() {
        return TagImpl.class;
    }

    public Tag findTagFromLabel(String label) {
        Query query;
        
        query = entityManager.createQuery("SELECT t FROM " +
                getEntityClass().getName() + " t " +
                "WHERE t.label = :label");
        query.setParameter("label", label);
        try {
            return (Tag)query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
