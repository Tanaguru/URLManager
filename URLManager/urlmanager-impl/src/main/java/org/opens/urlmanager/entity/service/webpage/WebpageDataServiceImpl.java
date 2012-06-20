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
import org.opens.urlmanager.entity.dao.webpage.WebpageDAO;
import org.opens.urlmanager.entity.service.LocaleAndTagAssociatedDataService;
import org.opens.urlmanager.entity.webpage.Webpage;

/**
 *
 * @author bcareil
 */
public class WebpageDataServiceImpl extends LocaleAndTagAssociatedDataService<Webpage>
    implements WebpageDataService {

    public static class Comparator implements java.util.Comparator<Webpage> {

        public int compare(Webpage o1, Webpage o2) {
            return o1.getURL().compareTo(o2.getURL());
        }

    }

    @Override
    public void create(Webpage entity) {
        entity.setTags(preprocessTags(entity.getTags(), true));
        entity.setLocales(preprocessLocales(entity.getLocales()));
        super.create(entity);
    }
    
    @Override
    public Webpage update(Webpage entity) {
        entity.setTags(preprocessTags(entity.getTags(), true));
        entity.setLocales(preprocessLocales(entity.getLocales()));
        return super.update(entity);
    }

    public Webpage getWebpageFromURL(String url) {
        return ((WebpageDAO)entityDao).findWebpageFromURL(url);
    }

    public Set<Webpage> getRootWebpageList() {
        return ((WebpageDAO)entityDao).findRootWebpageList();
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
