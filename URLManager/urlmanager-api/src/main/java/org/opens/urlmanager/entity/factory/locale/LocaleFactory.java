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
package org.opens.urlmanager.entity.factory.locale;

import org.opens.tanaguru.sdk.entity.factory.GenericFactory;
import org.opens.urlmanager.entity.locale.Locale;

/**
 *
 * @author bcareil
 */
public interface LocaleFactory extends GenericFactory<Locale> {
    
    /**
     * 
     * @param language
     * @param longLanguage
     * @param country
     * @param longCountry
     * @return an initialized Locale instance
     */
    Locale create(
            String language,
            String longLanguage,
            String country,
            String longCountry);
    
}
