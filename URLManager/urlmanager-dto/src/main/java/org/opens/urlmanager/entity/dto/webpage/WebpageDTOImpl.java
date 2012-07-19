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

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.xml.bind.annotation.*;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.opens.urlmanager.entity.dto.encoding.DTOEncoder;
import org.opens.urlmanager.entity.dto.locale.LocaleDTO;
import org.opens.urlmanager.entity.dto.tag.TagDTO;
import org.opens.urlmanager.entity.locale.Locale;
import org.opens.urlmanager.entity.tag.Tag;

/**
 *
 * @author bcareil
 */
@XmlRootElement(name = "webpage")
@XmlType(propOrder={"id", "isRoot", "URL", "locales", "tags"})
public class WebpageDTOImpl implements WebpageDTO {

    Long id;
    String url;
    Boolean isRoot;
    Set<LocaleDTO> locales = new HashSet<LocaleDTO>();
    Set<TagDTO> tags = new HashSet<TagDTO>();

    public WebpageDTOImpl() {
    }

    public WebpageDTOImpl(Long id, String url, Boolean isRoot) {
        this.id = id;
        this.url = url;
        this.isRoot = isRoot;
    }
    
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Boolean getIsRoot() {
        return isRoot;
    }

    @Override
    public void setIsRoot(Boolean isRoot) {
        this.isRoot = isRoot;
    }

    @Override
    public void addLocale(Locale locale) {
        locales.add((LocaleDTO) locale);
    }
    
    @Override
    @XmlElementWrapper(name = "locales")
    @XmlElement(name = "locale", type = LocaleDTO.class)
    public Collection<Locale> getLocales() {
        return (Collection) locales;
    }

    @Override
    public void setLocales(Collection<Locale> locales) {
        this.locales.clear();
        if (locales != null) {
            this.locales.addAll((Collection) locales);
        }
    }

    @Override
    public void addTag(Tag tag) {
        tags.add((TagDTO) tag);
    }
    
    @Override
    @XmlElementWrapper(name = "tags")
    @XmlElement(name = "tag", type = TagDTO.class)
    public Collection<Tag> getTags() {
        return (Collection) tags;
    }

    @Override
    public void setTags(Collection<Tag> tags) {
        this.tags.clear();
        if (tags != null) {
            this.tags.addAll((Collection) tags);
        }
    }

    @Override
    public String getURL() {
        return url;
    }

    @XmlTransient
    @JsonIgnore
    public String getHtmlEncodedUrl() {
        return url == null ? null : DTOEncoder.escapeHtml(url);
    }

    @XmlTransient
    @JsonIgnore
    public String getUrlEncodedUrl() {
        return url == null ? null : DTOEncoder.urlEncode(url);
    }

    @Override
    public void setURL(String url) {
        this.url = url;
    }    
  
}
