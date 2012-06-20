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
package org.opens.urlmanager.entity.dao.request;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.opens.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;
import org.opens.urlmanager.entity.locale.Locale;
import org.opens.urlmanager.entity.locale.LocaleImpl;
import org.opens.urlmanager.entity.request.Request;
import org.opens.urlmanager.entity.request.RequestImpl;
import org.opens.urlmanager.entity.tag.Tag;
import org.opens.urlmanager.entity.tag.TagImpl;
import org.opens.urlmanager.entity.webpage.Webpage;
import org.opens.urlmanager.entity.webpage.WebpageImpl;

/**
 *
 * @author bcareil
 */
public class RequestDAOImpl extends AbstractJPADAO<Request, Long> implements RequestDAO {

    private final String queryBegining;
    private static final String QUERY_JOIN_AND_FETCH_TAGS =
            "LEFT JOIN FETCH r.tags";
    private static final String QUERY_JOIN_AND_FETCH_LOCALES =
            "LEFT JOIN FETCH r.locales";
    
    public RequestDAOImpl() {
        super();
        
        queryBegining =
                "SELECT DISTINCT r FROM " +
                getEntityClass().getName() + " r "
                ;
    }

    @Override
    protected final Class<? extends Request> getEntityClass() {
        return RequestImpl.class;
    }
    
    private Query selectRequest(String whereStatement) {
        StringBuilder queryBuilder;
        
        queryBuilder = new StringBuilder(queryBegining);
        queryBuilder.append(" ").append(QUERY_JOIN_AND_FETCH_TAGS);
        queryBuilder.append(" ").append(QUERY_JOIN_AND_FETCH_LOCALES);
        queryBuilder.append(" ").append(whereStatement);
        return entityManager.createQuery(queryBuilder.toString());
    }

    @Override
    public List<Request> findAll() {
        Query query = selectRequest("");
        
        return query.getResultList();
    }

    @Override
    public Request read(Long key) {
        Request entity = super.read(key);
        
        if (entity == null)
            return null;
        // fetch locales and tags
        entity.getTags().size();
        entity.getLocales().size();
        // return fully filled entity
        return entity;
    }

    public Request findRequestFromLabel(String label) {
        Query query;
        
        query = selectRequest("WHERE r.label=:label");
        query.setParameter("label", label);
        try {
            return (Request) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    public Collection<Webpage> findMatchingWebpages(Request request) {
        String queryString;
        Query query;
        Collection<? extends Locale> locales = request.getLocales();
        Collection<? extends Tag> tags = request.getTags();
        List<Webpage> webpages;
        List<Webpage> result;
        
        // build query string
        queryString = buildRequestQuery(
                locales.isEmpty() == false,
                tags.isEmpty() == false
                );
        
        // create query and set parameters
        query = entityManager.createQuery(queryString);
        if (locales.isEmpty() == false) {
            query.setParameter("locales", (Collection<LocaleImpl>) locales);
        }
        if (tags.isEmpty() == false) {
            query.setParameter("tags", (Collection<TagImpl>) tags);
        }
        // get query result
        webpages = (List<Webpage>) query.getResultList();
        // finalize tag restriction and fetch locales
        result = new ArrayList<Webpage>();
        for (Webpage webpage : webpages) {
            webpage.getLocales().size();
            if (webpage.getTags().containsAll(tags)) {
                result.add(webpage);
            }
        }
        return result;
    }

    /**
     * Build the JQL query needed to fetch webpages matching optional tags and
     * locales to be set manualy using setParameter on the query.
     * 
     * The parameters to be set are "locales" if the @a hasLocales parameter is
     * set to true and "tags" if the @a hasTags parameter is set to true.
     * 
     * NOTE: The tags are inclusive, meaning, the request will match all
     *       webpages with at least one of the specified tag.
     * NOTE: The locales are inclusive too.
     * NOTE: If tag and locale restriction are enabled, the query will returned
     *       all webpages matching at least a locale AND a tag of the specified
     *       sets.
     * 
     * @param hasLocales Ask to include locale restriction.
     * @param hasTags    Ask to include tag restriction.
     * @return The JQL query needed to fetch the webpages with the specified
     *         restrictions.
     */
    protected String buildRequestQuery(
            boolean hasLocales,
            boolean hasTags) {
        // query: use to concat the queried tables
        StringBuilder query = new StringBuilder();
        // whereClause : use to concat the JQL WHERE statement
        StringBuilder whereClause = new StringBuilder();
        // addAnd : use to control the add of "AND" condition when adding tags
        //          in the whereClause
        boolean addAnd = false;
        
        whereClause.append("");
        // The request begin with
        //   "SELECT DISTINCT w FROM WEBPAGE w "
        query.append("SELECT DISTINCT w FROM ");
        query.append(WebpageImpl.class.getName());
        query.append(" w");
        // optionaly followed by the locale restriction
        if (hasLocales) {
            query.append(" INNER JOIN w.locales l");
            whereClause.append(" WHERE l IN (:locales)");
            // ask to add an "AND" to the WHERE clause before adding anything else
            addAnd = true;
        } else {
            // to fetch locales even if we have not restrictions on them
            query.append(" LEFT JOIN FETCH w.locales");
        }
        // optionaly followed by the tag restriction
        if (hasTags) {
            /*
             * NOTE: The tag restriction is not complete. It will include all
             * webpages having at least one of the specified tag which is
             * not what it is expected. The correct restriction is delegated to
             * the caller.
             * 
             * NOTE: If the tag restriction has to be done, it may look like
             * "SELECT w FROM Webpage AS w WHERE w.tags in :tags"
             * where ":tags" is replaced using setParameter by a list of tag.
             * However, this does not work properly with HSQLDB (last version
             * tested : 2.2.8). Or I don't succeed to do it work.
             */
            query.append(" INNER JOIN w.tags t");
            if (addAnd) {
                whereClause.append(" AND");
            } else {
                // If we do not have to add AND, that means that we are adding
                // the first restriction so we have to add WHERE first.
                whereClause.append(" WHERE");
            }
            whereClause.append(" t IN (:tags)");
        } else {
            // to fetch tag even if we have not restrictions on them
            query.append(" LEFT JOIN FETCH w.tags");
        }
        // concat the query with the where clause
        query.append(whereClause);
        return query.toString();
    }
    
}
