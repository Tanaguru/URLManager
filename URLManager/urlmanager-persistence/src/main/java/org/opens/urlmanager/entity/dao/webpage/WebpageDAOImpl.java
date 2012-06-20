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
package org.opens.urlmanager.entity.dao.webpage;

import java.util.*;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.opens.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;
import org.opens.urlmanager.entity.locale.Locale;
import org.opens.urlmanager.entity.locale.LocaleImpl;
import org.opens.urlmanager.entity.request.Request;
import org.opens.urlmanager.entity.tag.Tag;
import org.opens.urlmanager.entity.tag.TagImpl;
import org.opens.urlmanager.entity.webpage.Webpage;
import org.opens.urlmanager.entity.webpage.WebpageImpl;

/**
 *
 * @author bcareil
 */
public class WebpageDAOImpl extends AbstractJPADAO<Webpage, Long> implements WebpageDAO {

    private final String queryBegining;
    private static final String QUERY_JOIN_AND_FETCH_TAGS =
            "LEFT JOIN FETCH w.tags";
    private static final String QUERY_JOIN_AND_FETCH_LOCALES =
            "LEFT JOIN FETCH w.locales";
    
    public WebpageDAOImpl() {
        super();
        
        queryBegining =
                "SELECT DISTINCT w FROM " +
                getEntityClass().getName() + " w"
                ;
    }

    private Query selectWebpages(String whereStatement) {
        StringBuilder queryBuilder;
        
        queryBuilder = new StringBuilder(queryBegining);
        queryBuilder.append(" ").append(QUERY_JOIN_AND_FETCH_TAGS);
        queryBuilder.append(" ").append(QUERY_JOIN_AND_FETCH_LOCALES);
        queryBuilder.append(" ").append(whereStatement);
        return entityManager.createQuery(queryBuilder.toString());
    }
    
    @Override
    protected final Class<? extends Webpage> getEntityClass() {
        return WebpageImpl.class;
    }

    @Override
    public List<Webpage> findAll() {
        Query query;
        
        query = selectWebpages("");
        return query.getResultList();
    }

    @Override
    public Webpage read(Long key) {
        Webpage entity = super.read(key);
        
        if (entity == null)
            return null;
        // fetch locales and tags
        entity.getLocales().size();
        entity.getTags().size();
        // return entity
        return entity;
    }
    
    

    public Webpage findWebpageFromURL(String url) {
        Query query;
        
        query = selectWebpages("WHERE w.url = :url");
        query.setParameter("url", url);
        try {
            return (Webpage) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Set<Webpage> findRootWebpageList() {
        Query query;
        
        query = selectWebpages("WHERE w.isRoot = 1");
        return new HashSet<Webpage>((List<Webpage>) query.getResultList());
    }
    
    public List<Webpage> findWebpagesWithoutTag() {
        Query query;
        
        query = selectWebpages("WHERE w.tags IS EMPTY");
        return (List<Webpage>) query.getResultList();
    }

    public List<Webpage> findWebpagesWithoutLocale() {
        Query query;
        
        query = selectWebpages("WHERE w.locales IS EMPTY");
        return (List<Webpage>) query.getResultList();
    }

    public List<Webpage> findWebpagesWithoutRelations() {
        Query query;
        
        query = selectWebpages("WHERE w.tags IS EMPTY AND w.locales IS EMPTY");
        return (List<Webpage>) query.getResultList();
    }
    
}
