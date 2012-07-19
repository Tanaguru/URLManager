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
package org.opens.urlmanager.entity.dto.tag;

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
@XmlRootElement(name = "tag")
@XmlType(propOrder={"id", "label"})
public class TagDTOImpl implements TagDTO {

    private Long id;
    private String label;

    public TagDTOImpl() {
    }

    public TagDTOImpl(Long id, String label) {
        this.id = id;
        this.label = label;
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

    @Override
    public void setLabel(String label) {
        this.label = label;
    }

}
