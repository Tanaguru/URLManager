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
package org.opens.urlmanager.entity.request;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import org.opens.urlmanager.entity.locale.Locale;
import org.opens.urlmanager.entity.locale.LocaleImpl;
import org.opens.urlmanager.entity.tag.Tag;
import org.opens.urlmanager.entity.tag.TagImpl;

/**
 *
 * @author bcareil
 */
@Entity
@XmlRootElement(name = "request")
@Table(name = "REQUEST")
public class RequestImpl implements Request, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id_Request")
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "Label")
    private String label;
    
    @ManyToMany
    @JoinTable(
            name = "REQUEST_TAG",
            joinColumns = @JoinColumn(name = "Id_Request"),
            inverseJoinColumns = @JoinColumn(name = "Id_Tag")
            )
    private Set<TagImpl> tags = 
            new HashSet<TagImpl>();
    
    @ManyToMany
    @JoinTable(
            name = "REQUEST_LOCALE",
            joinColumns = @JoinColumn(name = "Id_Request"),
            inverseJoinColumns = @JoinColumn(name = "Id_Locale")
            )
    private Set<LocaleImpl> locales =
            new HashSet<LocaleImpl>();
    
    
    public RequestImpl() {
    }

    public RequestImpl(Long id, String label) {
        this.id = id;
        this.label = label;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RequestImpl other = (RequestImpl) obj;
        if ((this.label == null) ? (other.label != null) : !this.label.equals(other.label)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.label != null ? this.label.hashCode() : 0);
        return hash;
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

    public void setLabel(String label) {
        this.label = label;
    }

    @XmlElementWrapper(name = "locales")
    @XmlElement(name = "locales", type = LocaleImpl.class)
    public Collection<? extends Locale> getLocales() {
        return locales;
    }

    public void setLocales(Collection<? extends Locale> locales) {
        this.locales.addAll((Collection<LocaleImpl>)locales);
    }

    public void addLocale(Locale locale) {
        this.locales.add((LocaleImpl)locale);
    }

    @XmlElementWrapper(name = "tags")
    @XmlElement(name = "tags", type = TagImpl.class)
    public Collection<? extends Tag> getTags() {
        return tags;
    }

    public void setTags(Collection<? extends Tag> tags) {
        this.tags.addAll((Collection<TagImpl>)tags);
    }
    
    public void addTag(Tag tag) {
        this.tags.add((TagImpl)tag);
    }
    
}
