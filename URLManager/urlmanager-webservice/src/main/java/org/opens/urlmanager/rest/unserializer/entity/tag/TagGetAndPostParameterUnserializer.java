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
package org.opens.urlmanager.rest.unserializer.entity.tag;

import java.util.Map;
import java.util.StringTokenizer;
import org.opens.urlmanager.entity.dto.tag.TagDTO;
import org.opens.urlmanager.entity.request.Request;
import org.opens.urlmanager.entity.request.RequestImpl;
import org.opens.urlmanager.entity.tag.Tag;
import org.opens.urlmanager.entity.webpage.Webpage;
import org.opens.urlmanager.entity.webpage.WebpageImpl;
import org.opens.urlmanager.rest.unserializer.generic.GenericGetAndPostParameterUnserializer;
import org.opens.urlmanager.rest.unserializer.utils.UnserializerUtils;

/*
 * FIXME: do something more generic
 */
/**
 *
 * @author bcareil
 */
public class TagGetAndPostParameterUnserializer
        extends GenericGetAndPostParameterUnserializer<TagDTO> {

    public TagGetAndPostParameterUnserializer() {
        super();
        
        this.setTargetClass(TagDTO.class);
    }
    
    @Override
    protected void prePopulate(TagDTO bean, Map<String, String[]> parameters) {
        if (parameters.get("webpages") != null) {
            unserializeWebpagesParameter(bean, parameters.get("webpages")[0]);
            parameters.remove("webpages");
        }
        if (parameters.get("requests") != null) {
            unserializeRequestsParameter(bean, parameters.get("requests")[0]);
            parameters.remove("requests");
        }
        
    }

    private void unserializeWebpagesParameter(Tag tag, String value) {
        StringTokenizer comaTokenizer;
        
        value = UnserializerUtils.preProcessListValue(value);
        comaTokenizer = new StringTokenizer(value, ",");
        while (comaTokenizer.hasMoreTokens()) {
            String token = comaTokenizer.nextToken();
            Webpage webpage = new WebpageImpl(Long.valueOf(token), null, null);
            
            tag.addWebpage(webpage);
        }
    }

    private void unserializeRequestsParameter(Tag tag, String value) {
        StringTokenizer comaTokenizer;
        
        value = UnserializerUtils.preProcessListValue(value);
        comaTokenizer = new StringTokenizer(value, ",");
        while (comaTokenizer.hasMoreTokens()) {
            String token = comaTokenizer.nextToken();
            Request request = new RequestImpl(Long.valueOf(token), null);
            
            tag.addRequest(request);
        }        
    }
    
}
