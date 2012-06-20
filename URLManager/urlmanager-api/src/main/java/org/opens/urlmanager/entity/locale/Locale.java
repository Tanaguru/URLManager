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
package org.opens.urlmanager.entity.locale;

import java.util.Collection;
import org.opens.tanaguru.sdk.entity.Entity;
import org.opens.urlmanager.entity.request.Request;
import org.opens.urlmanager.entity.webpage.Webpage;

/**
 *
 * @author bcareil
 */
public interface Locale extends Entity {
    /**
     * 
     * @return 
     */
    String getLanguage();
    /**
     * 
     * @param language Code for the representation of a name of a language
     *                 (refer to iso639).
     */
    void setLanguage(String language);
    
    /**
     * 
     * @return 
     */
    String getLongLanguage();
    /**
     * 
     * @param longLanguage Full name of language
     */
    void setLongLanguage(String longLanguage);
    
    /**
     * 
     * @return 
     */
    String getCountry();
    /**
     * 
     * @param country Country short name (refer to iso3166)
     */
    void setCountry(String country);
    
    /**
     * 
     * @return 
     */
    String getLongCountry();
    /**
     * 
     * @param longCountry Country full name
     */
    void setLongCountry(String longCountry);
    
    /**
     * 
     * @return The language concatained with the country and separated
     *         with an underscore. Exemple : fr_FR.
     */
    String getLabel();

    /**
     * 
     * @param label The contatenation of the language and the country
     *              separeted by an underscore. Exemple fr_FR.
     */
    void setLabel(String label);
    
    /**
     * 
     * @return 
     */
    Collection<? extends Webpage> getWebpages();
    /**
     * 
     * @param webpages 
     */
    void setWebpages(Collection<? extends Webpage> webpages);
    /**
     * 
     * @param webpage 
     */
    void addWebpage(Webpage webpage);

    /**
     * 
     * @return 
     */
    Collection<? extends Request> getRequests();
    /**
     * 
     * @param requests 
     */
    void setRequests(Collection<? extends Request> requests);
    /**
     * 
     * @param request 
     */
    void addRequest(Request request);
}
