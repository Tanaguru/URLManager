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
package org.opens.urlmanager.entity.dto.webpage;

import java.io.Serializable;
import java.util.Collection;
import org.opens.urlmanager.entity.dto.locale.LocaleDTO;
import org.opens.urlmanager.entity.dto.tag.TagDTO;

/**
 *
 * @author bcareil
 */
public interface WebpageDTO extends Serializable {

    public Long getId();
    public void setId(Long id);

    public Boolean getIsRoot();
    public void setIsRoot(Boolean isRoot);

    public String getURL();
    public String getHtmlEncodedUrl();
    public String getUrlEncodedUrl();
    public void setURL(String url);
    
    public void addLocale(LocaleDTO locale);
    public Collection<LocaleDTO> getLocales();
    public void setLocales(Collection<LocaleDTO> locales);

    public void addTag(TagDTO tag);
    public Collection<TagDTO> getTags();
    public void setTags(Collection<TagDTO> tags);

}
