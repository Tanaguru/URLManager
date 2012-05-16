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

import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.opens.tanaguru.sdk.entity.dao.GenericDAO;
import org.opens.urlmanager.entity.locale.Locale;
import org.opens.urlmanager.entity.request.Request;
import org.opens.urlmanager.entity.tag.Tag;
import org.opens.urlmanager.entity.webpage.Webpage;

/**
 *
 * @author bcareil
 */
public interface WebpageDAO extends GenericDAO<Webpage, Long> {
    
    /**
     * 
     * @param url
     * @return The uniq Webpage associated with the given url or null if
     *         none was found.
     */
    Webpage findWebpageFromURL(String url);
    
    /**
     * 
     * @return The list of Webpages set with the isRoot bit.
     */
    Set<Webpage> findRootWebpageList();
    
    /**
     * 
     * @param request The stored request to get the result
     * @return The list of webpages matching the given request.
     */
    List<Webpage> findWebpageListFromRequest(Request request);
    
    /**
     * 
     * @param locales Restrict the research to the given locales. Use an empty
     *                list to avoid restrictions.
     * @param tags    Restrict the research to the given tags. Use an empty list
     *                to avoid restrictions.
     * @return The list of webpages matching the given locales and tags
     */
    List<Webpage> findWebpageListFromRequestParameters(
            Collection<? extends Locale> locales,
            Collection<? extends Tag> tags);
    
    /**
     * 
     * @return The list of webpages without tag
     */
    List<Webpage> findWebpagesWithoutTag();
    
    /**
     * 
     * @return The list of webpages without locale
     */
    List<Webpage> findWebpagesWithoutLocale();
    
    /**
     * 
     * @return The list of webpages without tag and locale
     */
    List<Webpage> findWebpagesWithoutRelations();
    
}
