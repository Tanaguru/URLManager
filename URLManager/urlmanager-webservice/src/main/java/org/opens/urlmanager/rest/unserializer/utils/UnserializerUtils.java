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
package org.opens.urlmanager.rest.unserializer.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.StringTokenizer;
import org.apache.commons.beanutils.BeanUtils;
import org.opens.urlmanager.rest.exception.BadClientRequestException;

/**
 *
 * @author bcareil
 */
public class UnserializerUtils {

    public static String preProcessListValue(String value) {
        if (value.length() < 2) {
            throw new BadClientRequestException("Invalid list");
        }
        return value.substring(1, value.length() - 1);
    }
    
    public static void unserializeListParameter(
            Object bean,
            Method beanSetter,
            String[] parameters,
            Class beanPropertyClass,
            String beanPropertyProperty
            ) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        if (parameters != null) {
            for (String parameter : parameters) {
                StringTokenizer tokenizer;

                if (parameter.length() == 0) {
                    continue ;
                } else if (parameter.charAt(0) == '[') {
                    parameter = preProcessListValue(parameter);
                }
                tokenizer = new StringTokenizer(parameter, ",");
                while (tokenizer.hasMoreTokens()) {
                    String token = tokenizer.nextToken().trim();
                    Object propertyInstance = beanPropertyClass.newInstance();

                    BeanUtils.setProperty(propertyInstance, beanPropertyProperty, token);
                    beanSetter.invoke(bean, propertyInstance);
                }
            }
        }        
    }
}
