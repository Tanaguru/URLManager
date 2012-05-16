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

import java.util.List;
import org.opens.tanaguru.sdk.entity.service.GenericDataService;
import org.opens.urlmanager.entity.locale.Locale;

/**
 *
 * @author bcareil
 */
public interface LocaleDataService extends GenericDataService<Locale, Long> {
    
    /**
     * 
     * @param language conform to iso639-2
     * @return The list of Locale matching with the given language code.
     *         Return an empty list if there is no match.
     */
    List<Locale> getLocaleListFromLanguage(String language);
    
    /**
     * 
     * @param country conform to iso3166
     * @return The list of Locale matching with the given country code.
     *         Return an empty list if there is no match.
     */
    List<Locale> getLocaleListFromCountry(String country);
    
    /**
     * 
     * @param longLanguage The full language name.
     * @return The list of Locale matching the given language.
     *         Return an empty list if there is no match.
     */
    List<Locale> getLocaleListFromLongLanguage(String longLanguage);
    
    /**
     * 
     * @param longCountry The full country name.
     * @return The list of Locale matching the given country name.
     *         Return an empty list if there is no match.
     */
    List<Locale> getLocaleListFromLongCountry(String longCountry);
    
    
    /**
     * 
     * @param language conform to iso639-2
     * @param country  conform to iso3166
     * @return The Locale matching the given language/country couple.
     *         Return null if there is no match.
     */
    Locale getLocaleFromLanguageAndCountry(String language, String country);
    
    /**
     * 
     * @param longLanguage The full language name.
     * @param longCountry  The full country name.
     * @return The Locale matching the given language/country couple.
     *         Return null if there is no match.
     */
    Locale getLocaleFromLongLanguageAndLongCountry(
            String longLanguage,
            String longCountry);
}
