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
import org.opens.urlmanager.entity.request.Request;
import org.opens.urlmanager.entity.request.RequestImpl;
import org.opens.urlmanager.entity.webpage.Webpage;
import org.opens.urlmanager.entity.webpage.WebpageImpl;

/**
 *
 * @author bcareil
 */
@Entity
@Table(name = "TAG")
public class TagImpl implements Tag, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id_Tag")
    private Long id;
    
    @Column(name = "Label")
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
        if (this.label == null) {
            return (other.label == null);
        }
        return this.label.equals(other.label);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + (this.label != null ? this.label.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "TagImpl{" + "id=" + id + ", label=" + label + ", webpages=" + webpages + ", requests=" + requests + '}';
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
    public Collection<? extends Request> getRequests() {
        return requests;
    }

    @Override
    public void setRequests(Collection<? extends Request> requests) {
        this.requests.addAll((Collection<RequestImpl>)requests);
    }

    @Override
    public void addRequest(Request request) {
        this.requests.add((RequestImpl)request);
    }

    @Override
    public Collection<? extends Webpage> getWebpages() {
        return webpages;
    }

    @Override
    public void setWebpages(Collection<? extends Webpage> webpages) {
        this.webpages.addAll((Collection<WebpageImpl>)webpages);
    }

    @Override
    public void addWebpage(Webpage webpage) {
        this.webpages.add((WebpageImpl)webpage);
    }
    
}
