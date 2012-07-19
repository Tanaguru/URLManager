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
package org.opens.urlmanager.rest.utils.jaxb.entity.webpage;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.opens.urlmanager.entity.dto.webpage.WebpageDTO;
import org.opens.urlmanager.rest.utils.jaxb.JaxbList;


/**
 *
 * @author bcareil
 */
@XmlRootElement(name = "webpages")
public class WebpageList extends JaxbList<WebpageDTO> {

    public WebpageList() {
        super();
    }

    public WebpageList(List<WebpageDTO> list) {
        super(list);
    }

    @Override
    @XmlElement(name = "webpage")
    public List<WebpageDTO> getList() {
        return this.list;
    }
    
}
