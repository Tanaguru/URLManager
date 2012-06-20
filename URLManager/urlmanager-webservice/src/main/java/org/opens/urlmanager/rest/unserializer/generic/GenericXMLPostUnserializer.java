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
package org.opens.urlmanager.rest.unserializer.generic;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;
import org.opens.urlmanager.rest.api.IRestUnserializer;
import org.opens.urlmanager.rest.exception.BadClientRequestException;
import org.opens.urlmanager.rest.exception.InternalErrorException;

/**
 *
 * @author bcareil
 */
public class GenericXMLPostUnserializer <T> implements IRestUnserializer<T> {

    private Class<T> targetClass;
        
    @Override
    public Boolean canUnserializeRequest(HttpServletRequest request) {
        return (request.getMethod().equals("POST") &&
                request.getContentType().equals("application/xml"));
    }

    @Override
    public T unserialize(HttpServletRequest request) {
        T tag;

        try {
            JAXBContext jaxb = JAXBContext.newInstance(targetClass);
            Unmarshaller unmarshaller = jaxb.createUnmarshaller();

            tag = (T) unmarshaller.unmarshal(request.getReader());
        } catch (UnmarshalException e) {
            throw new BadClientRequestException("Invalid object", e);
        } catch (JAXBException e) {
            throw new InternalErrorException("JAXB exception", e);
        } catch (IOException e) {
            throw new BadClientRequestException("IO exception", e);
        } catch (ClassCastException e) {
            throw new InternalErrorException("Class cast exception", e);
        }
        return tag;
    }

    public Class<T> getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class<T> targetClass) {
        this.targetClass = targetClass;
    }
}
