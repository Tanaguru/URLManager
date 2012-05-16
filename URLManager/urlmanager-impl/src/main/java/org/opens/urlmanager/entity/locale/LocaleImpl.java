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
package org.opens.urlmanager.entity.locale;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.opens.urlmanager.entity.request.Request;
import org.opens.urlmanager.entity.request.RequestImpl;
import org.opens.urlmanager.entity.webpage.Webpage;
import org.opens.urlmanager.entity.webpage.WebpageImpl;

/**
 *
 * @author bcareil
 */
@Entity
@Table(name = "LOCALE")
public class LocaleImpl implements Locale, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id_Locale")
    private Long id;
    
    @Column(name = "Language")
    private String language;
    
    @Column(name = "Long_Language")
    private String longLanguage;
    
    @Column(name = "Country")
    private String country;
    
    @Column(name = "Long_Country")
    private String longCountry;

    @ManyToMany(mappedBy="locales")
    private Set<WebpageImpl> webpages = 
            new HashSet<WebpageImpl>();
    
    @ManyToMany(mappedBy="locales")
    private Set<RequestImpl> requests =
            new HashSet<RequestImpl>();

    public LocaleImpl(Long id, String language, String longLanguage, String country, String longCountry) {
        this.id = id;
        this.language = language;
        this.longLanguage = longLanguage;
        this.country = country;
        this.longCountry = longCountry;
    }
    
    public LocaleImpl() {
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LocaleImpl other = (LocaleImpl) obj;
        if ((this.language == null) ? (other.language != null) : !this.language.equals(other.language)) {
            return false;
        }
        if ((this.country == null) ? (other.country != null) : !this.country.equals(other.country)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + (this.language != null ? this.language.hashCode() : 0);
        hash = 79 * hash + (this.country != null ? this.country.hashCode() : 0);
        return hash;
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

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLongCountry() {
        return longCountry;
    }

    public void setLongCountry(String longCountry) {
        this.longCountry = longCountry;
    }

    public String getLongLanguage() {
        return longLanguage;
    }

    public void setLongLanguage(String longLanguage) {
        this.longLanguage = longLanguage;
    }

    public Collection<? extends Webpage> getWebpages() {
        return this.webpages;
    }

    public void setWebpages(Collection<? extends Webpage> webpages) {
        this.webpages.addAll((Collection<WebpageImpl>) webpages);
    }

    public void addWebpage(Webpage webpage) {
        this.webpages.add((WebpageImpl)webpage);
    }

    public Collection<? extends Request> getRequests() {
        return this.requests;
    }

    public void setRequests(Collection<? extends Request> requests) {
        this.requests.addAll((Collection<RequestImpl>) requests);
    }

    public void addRequest(Request request) {
        this.requests.add((RequestImpl)request);
    }

}
