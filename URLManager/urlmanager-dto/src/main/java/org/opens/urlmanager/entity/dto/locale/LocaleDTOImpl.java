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
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.opens.urlmanager.entity.dto.encoding.DTOEncoder;
import org.opens.urlmanager.entity.request.Request;
import org.opens.urlmanager.entity.webpage.Webpage;

/**
 *
 * @author bcareil
 */
@XmlRootElement(name = "locale")
@XmlType(propOrder={"id", "country", "language", "longCountry", "longLanguage"})
public class LocaleDTOImpl implements LocaleDTO {

    private Long id;
    private String language;
    private String longLanguage;
    private String country;
    private String longCountry;
    private Set<Webpage> webpages = new HashSet<Webpage>();
    private Set<Request> requests = new HashSet<Request>();

    public LocaleDTOImpl() {
    }

    public LocaleDTOImpl(Long id, String language, String longLanguage, String country, String longCountry) {
        this.id = id;
        this.language = language;
        this.longLanguage = longLanguage;
        this.country = country;
        this.longCountry = longCountry;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }
    
    @XmlTransient
    @JsonIgnore
    public String getHtmlEncodedCountry() {
        return country == null ? null : DTOEncoder.escapeHtml(country);
    }
    
    @XmlTransient
    @JsonIgnore
    public String getUrlEncodedCountry() {
        return country == null ? null : DTOEncoder.urlEncode(country);
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    @XmlTransient
    @JsonIgnore
    public String getHtmlEncodedLanguage() {
        return language == null ? null : DTOEncoder.escapeHtml(language);
    }
    
    @XmlTransient
    @JsonIgnore
    public String getUrlEncodedLanguage() {
        return language == null ? null : DTOEncoder.urlEncode(language);
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLongCountry() {
        return longCountry;
    }

    @XmlTransient
    @JsonIgnore
    public String getHtmlEncodedLongCountry() {
        return longCountry == null ? null : DTOEncoder.escapeHtml(longCountry);
    }
    
    @XmlTransient
    @JsonIgnore
    public String getUrlEncodedLongCountry() {
        return longCountry == null ? null : DTOEncoder.urlEncode(longCountry);
    }

    public void setLongCountry(String longCountry) {
        this.longCountry = longCountry;
    }

    public String getLongLanguage() {
        return longLanguage;
    }

    @XmlTransient
    @JsonIgnore
    public String getHtmlEncodedLongLanguage() {
        return longLanguage == null ? null : DTOEncoder.escapeHtml(longLanguage);
    }
    
    @XmlTransient
    @JsonIgnore
    public String getUrlEncodedLongLanguage()   {
        return longLanguage == null ? null : DTOEncoder.urlEncode(longLanguage);
    }

    public void setLongLanguage(String longLanguage) {
        this.longLanguage = longLanguage;
    }

    @XmlTransient
    @JsonIgnore
    public String getLabel() {        
        StringBuilder sb = new StringBuilder();
        
        if (language != null) {
            sb.append(language);
            if (country != null && country.isEmpty() == false) {
                sb.append("_").append(country);
            }
        }
        return sb.toString();
    }

    @XmlTransient
    @JsonIgnore
    public String getHtmlEncodedLabel() {
        return getLabel() == null ? null : DTOEncoder.escapeHtml(getLabel());
    }
    
    @XmlTransient
    @JsonIgnore
    public String getUrlEncodedLabel() {
        return getLabel() == null ? null : DTOEncoder.urlEncode(getLabel());
    }

    public void setLabel(String label) {
        String[] tokens = label.split("_");
        
        if (tokens.length == 1) {
            language = tokens[0];
            country = "";
        } else if (tokens.length == 2) {
            language = tokens[0];
            country = tokens[1];            
        } else {
            throw new IllegalArgumentException(
                    "Invalid locale label \"" + label + "\""
                    );
        }
    }
    
    @XmlTransient
    @JsonIgnore
    public Collection<Webpage> getWebpages() {
        return (Collection) this.webpages;
    }

    public void setWebpages(Collection<Webpage> webpages) {
        this.webpages.clear();
        if (webpages != null) {
            this.webpages.addAll((Collection) webpages);
        }
    }

    public void addWebpage(Webpage webpage) {
        this.webpages.add(webpage);
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Request> getRequests() {
        return (Collection) this.requests;
    }

    public void setRequests(Collection<Request> requests) {
        this.requests.clear();
        if (requests != null) {
            this.requests.addAll((Collection) requests);
        }
    }

    public void addRequest(Request request) {
        this.requests.add(request);
    }

}
