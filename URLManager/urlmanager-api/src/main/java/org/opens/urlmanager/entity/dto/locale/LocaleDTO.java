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
package org.opens.urlmanager.entity.dto.locale;

import java.io.Serializable;

/**
 *
 * @author bcareil
 */
public interface LocaleDTO extends Serializable {

    Long getId();
    void setId(Long id);

    String getCountry();
    String getHtmlEncodedCountry();
    String getUrlEncodedCountry();
    void setCountry(String country);

    String getLanguage();
    String getHtmlEncodedLanguage();
    String getUrlEncodedLanguage();
    void setLanguage(String language);

    String getLongCountry();
    String getHtmlEncodedLongCountry();
    String getUrlEncodedLongCountry();
    void setLongCountry(String longCountry);

    String getLongLanguage();
    String getHtmlEncodedLongLanguage();
    String getUrlEncodedLongLanguage();
    void setLongLanguage(String longLanguage);

    String getLabel();
    String getHtmlEncodedLabel();
    String getUrlEncodedLabel();
    void setLabel(String label);
    
}
