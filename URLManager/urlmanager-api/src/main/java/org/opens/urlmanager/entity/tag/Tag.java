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
package org.opens.urlmanager.entity.tag;

import java.util.Collection;
import org.opens.tanaguru.sdk.entity.Entity;
import org.opens.urlmanager.entity.request.Request;
import org.opens.urlmanager.entity.webpage.Webpage;

/**
 *
 * @author bcareil
 */
public interface Tag extends Entity {
    
    /**
     * 
     * @return 
     */
    String getLabel();
    /**
     * 
     * @param label 
     */
    void setLabel(String label);
    
    /**
     * 
     * @return 
     */
    Collection<Webpage> getWebpages();
    /**
     * 
     * @param webpages 
     */
    void setWebpages(Collection<Webpage> webpages);
    /**
     * 
     * @param webpage 
     */
    void addWebpage(Webpage webpage);
    
    /**
     * 
     * @return 
     */
    Collection<Request> getRequests();
    /**
     * 
     * @param researches 
     */
    void setRequests(Collection<Request> researches);
    /**
     * 
     * @param request 
     */
    void addRequest(Request request);
}
