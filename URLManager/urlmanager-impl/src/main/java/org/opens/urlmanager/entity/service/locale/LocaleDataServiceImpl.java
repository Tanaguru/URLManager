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
package org.opens.urlmanager.entity.service.locale;

import java.util.Collection;
import java.util.List;
import org.opens.urlmanager.entity.dao.locale.LocaleDAO;
import org.opens.urlmanager.entity.locale.Locale;
import org.opens.urlmanager.entity.service.AbstractGenericDataServiceWithDTO;

/**
 *
 * @author bcareil
 */
public class LocaleDataServiceImpl extends AbstractGenericDataServiceWithDTO<Locale>
    implements LocaleDataService {

    public static class Comparator implements java.util.Comparator<Locale> {

        public int compare(Locale a, Locale b) {
            int diffLanguage;
            
            diffLanguage = a.getLanguage().compareTo(b.getLanguage());
            if (diffLanguage == 0) {
                return a.getCountry().compareTo(b.getCountry());
            }
            return diffLanguage;
        }
    }

    public LocaleDataServiceImpl() {
        super();
    }

    public List<Locale> getLocaleListFromLanguage(String language) {
        return (List) wrapper.entityCollectionToDtoCollection((Collection) ((LocaleDAO)entityDao).findLocaleListFromLanguage(language));
    }

    public List<Locale> getLocaleListFromCountry(String country) {
        return (List) wrapper.entityCollectionToDtoCollection((Collection) ((LocaleDAO)entityDao).findLocaleListFromCountry(country));
    }

    public List<Locale> getLocaleListFromLongLanguage(String longLanguage) {
        return (List) wrapper.entityCollectionToDtoCollection((Collection) ((LocaleDAO)entityDao).findLocaleListFromLongLanguage(longLanguage));
    }

    public List<Locale> getLocaleListFromLongCountry(String longCountry) {
        return (List) wrapper.entityCollectionToDtoCollection((Collection) ((LocaleDAO)entityDao).findLocaleListFromLongCountry(longCountry));
    }

    public Locale getLocaleFromLanguageAndCountry(
            String language,
            String country) {
        return (Locale) wrapper.entityToDto(
                ((LocaleDAO)entityDao).findLocaleFromLanguageAndCountry(
                    language,
                    country
                    )
                );
    }

    public Locale getLocaleFromLongLanguageAndLongCountry(
            String longLanguage,
            String longCountry) {
        return (Locale) wrapper.entityToDto(
                ((LocaleDAO)entityDao).findLocaleFromLongLanguageAndLongCountry(
                    longLanguage,
                    longCountry
                    )
                );
    }
    
}
