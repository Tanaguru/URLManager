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

    public WebpageDAOImpl() {
        super();
    }

    @Override
    protected Class<? extends Webpage> getEntityClass() {
        return WebpageImpl.class;
    }

    public Webpage findWebpageFromURL(String url) {
        Query query;
        
        query = entityManager.createQuery("SELECT w FROM " +
                getEntityClass().getName() + " w " +
                "WHERE w.url = :url");
        query.setParameter("url", url);
        try {
            return (Webpage) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Set<Webpage> findRootWebpageList() {
        Query query;
        
        query = entityManager.createQuery("SELECT w FROM " +
                getEntityClass().getName() + " w " +
                " LEFT JOIN FETCH w.tags" +
                " LEFT JOIN FETCH w.locales" +
                " WHERE w.isRoot = 1");
        return new HashSet<Webpage>((List<Webpage>) query.getResultList());
    }

    public List<Webpage> findWebpageListFromRequest(Request request) {
        return findWebpageListFromRequestParameters(
                request.getLocales(),
                request.getTags());
    }

    public List<Webpage> findWebpageListFromRequestParameters(
            Collection<? extends Locale> locales,
            Collection<? extends Tag> tags) {
        String queryString;
        Query query;
        List<Webpage> webpages;
        List<Webpage> result;
        
        queryString = buildRequestQuery(
                locales.isEmpty() == false,
                tags.isEmpty() == false);
        
        query = entityManager.createQuery(queryString);
        if (locales.isEmpty() == false) {
            query.setParameter("locales", (Collection<LocaleImpl>) locales);
        }
        if (tags.isEmpty() == false) {
            query.setParameter("tags", (Collection<TagImpl>) tags);
        }
        webpages = (List<Webpage>) query.getResultList();
        result = new ArrayList<Webpage>();
        for (Webpage webpage : webpages) {
            if (webpage.getTags().containsAll(tags)) {
                result.add(webpage);
            }
        }
        return result;
    }
    
    public List<Webpage> findWebpagesWithoutTag() {
        Query query;
        
        query = entityManager.createQuery("SELECT w FROM " +
                getEntityClass().getName() + " w " +
                "WHERE w.tags IS EMPTY");
        return (List<Webpage>) query.getResultList();
    }

    public List<Webpage> findWebpagesWithoutLocale() {
        Query query;
        
        query = entityManager.createQuery("SELECT w FROM " +
                getEntityClass().getName() + " w " +
                "WHERE w.locales IS EMPTY");
        return (List<Webpage>) query.getResultList();
    }

    public List<Webpage> findWebpagesWithoutRelations() {
        Query query;
        
        query = entityManager.createQuery("SELECT w FROM " +
                getEntityClass().getName() + " w " +
                "WHERE w.tags IS EMPTY AND w.locales IS EMPTY");
        return (List<Webpage>) query.getResultList();
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
        //   "SELECT w FROM WEBPAGE w "
        query.append("SELECT w FROM ");
        query.append(getEntityClass().getName());
        query.append(" w");
        // optionaly followed by the locale restriction
        if (hasLocales) {
            query.append(" INNER JOIN w.locales l");
            whereClause.append(" WHERE l IN (:locales)");
            // ask to add an "AND" to the WHERE clause before adding anything else
            addAnd = true;
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
        }
        // concat the query with the where clause
        query.append(whereClause);
        query.append(" GROUP BY w");
        return query.toString();
    }

}
