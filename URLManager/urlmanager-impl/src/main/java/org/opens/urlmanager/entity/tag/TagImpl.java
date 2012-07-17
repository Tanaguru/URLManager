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
package org.opens.urlmanager.entity.tag;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.opens.urlmanager.entity.request.Request;
import org.opens.urlmanager.entity.request.RequestImpl;
import org.opens.urlmanager.entity.webpage.Webpage;
import org.opens.urlmanager.entity.webpage.WebpageImpl;


/**
 *
 * @author bcareil
 */
@Entity
@XmlRootElement(name = "tag")
@Table(name = "TAG")
public class TagImpl implements Tag, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id_Tag")
    private Long id;
    
    @Column(name = "Label",
            unique = true,
            nullable = false)
    private String label;
    
    @ManyToMany(
            cascade = CascadeType.PERSIST,
            mappedBy = "tags"
            )
    private Set<WebpageImpl> webpages =
            new HashSet<WebpageImpl>();
    
    @ManyToMany(
            cascade = CascadeType.PERSIST,
            mappedBy = "tags"
            )
    private Set<RequestImpl> requests =
            new HashSet<RequestImpl>();

    public TagImpl() {
    }

    public TagImpl(Long id, String label) {
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
        final TagImpl other = (TagImpl) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.label == null) ? (other.label != null) : !this.label.equals(other.label)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 37 * hash + (this.label != null ? this.label.hashCode() : 0);
        return hash;
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
    public String getLabel() {
        return label;
    }

    @Override
    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    @XmlTransient
    @JsonIgnore
    public Collection<Request> getRequests() {
        return (Collection) requests;
    }

    @Override
    public void setRequests(Collection<Request> requests) {
        this.requests.clear();
        this.requests.addAll((Collection) requests);
    }

    @Override
    public void addRequest(Request request) {
        this.requests.add((RequestImpl)request);
    }

    @Override
    @XmlTransient
    @JsonIgnore
    public Collection<Webpage> getWebpages() {
        return (Collection) webpages;
    }

    @Override
    public void setWebpages(Collection<Webpage> webpages) {
        this.webpages.clear();
        this.webpages.addAll((Collection) webpages);
    }

    @Override
    public void addWebpage(Webpage webpage) {
        this.webpages.add((WebpageImpl)webpage);
    }
    
}
