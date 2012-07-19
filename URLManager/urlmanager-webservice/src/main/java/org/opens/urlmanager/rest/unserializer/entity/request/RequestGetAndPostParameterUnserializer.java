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
package org.opens.urlmanager.rest.unserializer.entity.request;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.opens.urlmanager.entity.dto.locale.LocaleDTO;
import org.opens.urlmanager.entity.dto.request.RequestDTO;
import org.opens.urlmanager.entity.dto.tag.TagDTO;
import org.opens.urlmanager.entity.locale.Locale;
import org.opens.urlmanager.entity.tag.Tag;
import org.opens.urlmanager.rest.exception.BadClientRequestException;
import org.opens.urlmanager.rest.unserializer.entity.webpage.WebpageGetAndPostParameterUnserializer;
import org.opens.urlmanager.rest.unserializer.generic.GenericGetAndPostParameterUnserializer;
import org.opens.urlmanager.rest.unserializer.utils.UnserializerUtils;

/*
 * FIXME: do something more generic
 */
/**
 *
 * @author bcareil
 */
public class RequestGetAndPostParameterUnserializer
        extends GenericGetAndPostParameterUnserializer<RequestDTO> {

    public RequestGetAndPostParameterUnserializer() {
        super();
        
        this.setTargetClass(RequestDTO.class);
    }

    @Override
    protected void prePopulate(RequestDTO bean, Map<String, String[]> parameters) {
        try {
            UnserializerUtils.unserializeListParameter(
                    bean,
                    bean.getClass().getMethod("addTag", Tag.class),
                    parameters.remove("tags"),
                    TagDTO.class,
                    "id"
                    );
            UnserializerUtils.unserializeListParameter(
                    bean,
                    bean.getClass().getMethod("addLocale", Locale.class),
                    parameters.remove("locales"),
                    LocaleDTO.class,
                    "id"
                    );
            UnserializerUtils.unserializeListParameter(
                    bean,
                    bean.getClass().getMethod("addTag", Tag.class),
                    parameters.remove("tags-label"),
                    TagDTO.class,
                    "label"
                    );
            UnserializerUtils.unserializeListParameter(
                    bean,
                    bean.getClass().getMethod("addLocale", Locale.class),
                    parameters.remove("locales-label"),
                    LocaleDTO.class,
                    "label"
                    );
        } catch (Exception ex) {
            Logger.getLogger(WebpageGetAndPostParameterUnserializer.class.getName()).log(Level.SEVERE, null, ex);
            throw new BadClientRequestException("Invalid request parameters", ex);
        }        
    }
    
}
