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
package org.opens.urlmanager.entity.dto.request;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.xml.bind.annotation.*;
import org.apache.commons.lang.StringEscapeUtils;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.opens.urlmanager.entity.dto.encoding.DTOEncoder;
import org.opens.urlmanager.entity.dto.locale.LocaleDTO;
import org.opens.urlmanager.entity.dto.tag.TagDTO;
import org.opens.urlmanager.entity.locale.Locale;
import org.opens.urlmanager.entity.request.Request;
import org.opens.urlmanager.entity.tag.Tag;

/**
 *
 * @author bcareil
 */
@XmlRootElement(name = "request")
@XmlType(propOrder={"id", "label", "locales", "tags"})
public class RequestDTOImpl implements RequestDTO {

    private Long id;
    private String label;
    private Set<Tag> tags = new HashSet<Tag>();
    private Set<Locale> locales = new HashSet<Locale>();
    
    public RequestDTOImpl() {
    }

    public RequestDTOImpl(Long id, String label) {
        this.id = id;
        this.label = label;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }
    
    @XmlTransient
    @JsonIgnore
    public String getHtmlEncodedLabel() {
        return label == null ? null : DTOEncoder.escapeHtml(label);
    }
    
    @XmlTransient
    @JsonIgnore
    public String getUrlEncodedLabel() {
        return label == null ? null : DTOEncoder.urlEncode(label);
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @XmlElementWrapper(name = "locales")
    @XmlElement(name = "locale", type = LocaleDTO.class)
    public Collection<Locale> getLocales() {
        return (Collection) locales;
    }

    public void setLocales(Collection<Locale> locales) {
        this.locales.clear();
        if (locales != null) {
            this.locales.addAll((Collection) locales);
        }
    }

    public void addLocale(Locale locale) {
        this.locales.add(locale);
    }

    @XmlElementWrapper(name = "tags")
    @XmlElement(name = "tag", type = TagDTO.class)
    public Collection<Tag> getTags() {
        return (Collection) tags;
    }

    public void setTags(Collection<Tag> tags) {
        this.tags.clear();
        if (tags != null) {
            this.tags.addAll((Collection) tags);
        }
    }
    
    public void addTag(Tag tag) {
        this.tags.add(tag);
    }
    
}
