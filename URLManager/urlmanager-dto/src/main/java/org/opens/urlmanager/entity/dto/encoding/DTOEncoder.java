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
package org.opens.urlmanager.entity.dto.encoding;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author bcareil
 */
public class DTOEncoder {

    private DTOEncoder() {}
    
    public static String urlEncode(String string) {
        try {
            return URLEncoder.encode(string, "utf-8");
        } catch (UnsupportedEncodingException ex) {
            LogFactory.getLog(DTOEncoder.class).fatal("urlEncode", ex);
            return null;
        }
    }

    public static String escapeHtml(String string) {
        return StringEscapeUtils.escapeHtml(string);
    }
}
