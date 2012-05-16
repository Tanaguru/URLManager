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
package org.opens.urlmanager.entity.service.webpage;

import java.util.List;
import java.util.Set;
import org.opens.tanaguru.sdk.entity.service.AbstractGenericDataService;
import org.opens.urlmanager.entity.dao.webpage.WebpageDAO;
import org.opens.urlmanager.entity.locale.Locale;
import org.opens.urlmanager.entity.request.Request;
import org.opens.urlmanager.entity.tag.Tag;
import org.opens.urlmanager.entity.webpage.Webpage;

/**
 *
 * @author bcareil
 */
public class WebpageDataServiceImpl extends AbstractGenericDataService<Webpage, Long> 
    implements WebpageDataService {

    public static class Comparator implements java.util.Comparator<Webpage> {

        public int compare(Webpage o1, Webpage o2) {
            return o1.getURL().compareTo(o2.getURL());
        }

    }

    public Webpage getWebpageFromURL(String url) {
        return ((WebpageDAO)entityDao).findWebpageFromURL(url);
    }

    public Set<Webpage> getRootWebpageList() {
        return ((WebpageDAO)entityDao).findRootWebpageList();
    }

    public List<Webpage> getWebpageListFromRequest(Request request) {
        return ((WebpageDAO)entityDao).findWebpageListFromRequest(request);
    }

    public List<Webpage> getWebpageListFromRequestParameters(List<Locale> locales, List<Tag> tags) {
        return ((WebpageDAO)entityDao).findWebpageListFromRequestParameters(locales, tags);
    }

    public List<Webpage> getWebpagesWithoutTag() {
        return ((WebpageDAO)entityDao).findWebpagesWithoutTag();
    }

    public List<Webpage> getWebpagesWithoutLocale() {
        return ((WebpageDAO)entityDao).findWebpagesWithoutLocale();
    }

    public List<Webpage> getWebpagesWithoutRelations() {
        return ((WebpageDAO)entityDao).findWebpagesWithoutRelations();
    }
    
}
