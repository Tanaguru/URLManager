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
package org.opens.urlmanager.entity.webpage;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import org.opens.urlmanager.entity.locale.Locale;
import org.opens.urlmanager.entity.locale.LocaleImpl;
import org.opens.urlmanager.entity.tag.Tag;
import org.opens.urlmanager.entity.tag.TagImpl;

/**
 *
 * @author bcareil
 */
@Entity
@Table(name = "WEBPAGE")
public class WebpageImpl implements Webpage, Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id_Webpage")
    private Long id;
    
    @Column(name = "URL",
            nullable = false,
            unique = true)
    private String url;
    
    @Basic(optional = false)
    @Column(name = "Is_Root")
    private Boolean isRoot;
    
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "WEBPAGE_TAG",
            joinColumns = @JoinColumn(name = "Id_Webpage"),
            inverseJoinColumns = @JoinColumn(name = "Id_Tag")
            )
    private Set<TagImpl> tags =
            new HashSet<TagImpl>();

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(
            name = "WEBPAGE_LOCALE",
            joinColumns = @JoinColumn(name = "Id_Webpage"),
            inverseJoinColumns = @JoinColumn(name = "Id_Locale")
            )
    private Set<LocaleImpl> locales = 
            new HashSet<LocaleImpl>();
    
    public WebpageImpl() {
        
    }
    
    public WebpageImpl(Long id, String url, Boolean isRoot) {
        this.id = id;
        this.url = url;
        this.isRoot = isRoot;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final WebpageImpl other = (WebpageImpl) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.url == null) ? (other.url != null) : !this.url.equals(other.url)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 41 * hash + (this.url != null ? this.url.hashCode() : 0);
        return hash;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIsRoot() {
        return isRoot;
    }

    public void setIsRoot(Boolean isRoot) {
        this.isRoot = isRoot;
    }

    public Collection<Locale> getLocales() {
        return (Collection)locales;
    }

    public void setLocales(Collection<Locale> locales) {
        this.locales.clear();
        this.locales.addAll((Collection)locales);
    }

    public void addLocale(Locale locale) {
        this.locales.add((LocaleImpl)locale);
    }

    public Collection<Tag> getTags() {
        return (Collection) tags;
    }

    public void setTags(Collection<Tag> tags) {
        this.tags.clear();
        this.tags.addAll((Collection) tags);
    }

    public void addTag(Tag tag) {
        this.tags.add((TagImpl)tag);
    }

    public String getURL() {
        return url;
    }

    public void setURL(String url) {
        this.url = url;
    }

}
