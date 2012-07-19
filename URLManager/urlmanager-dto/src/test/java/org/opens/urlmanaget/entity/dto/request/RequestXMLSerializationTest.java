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
package org.opens.urlmanaget.entity.dto.request;

import java.io.ByteArrayOutputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import junit.framework.TestCase;
import org.opens.urlmanager.entity.dto.locale.LocaleDTO;
import org.opens.urlmanager.entity.dto.locale.LocaleDTOImpl;
import org.opens.urlmanager.entity.dto.request.RequestDTO;
import org.opens.urlmanager.entity.dto.request.RequestDTOImpl;
import org.opens.urlmanager.entity.dto.tag.TagDTO;
import org.opens.urlmanager.entity.dto.tag.TagDTOImpl;
import org.opens.urlmanager.entity.dto.webpage.WebpageDTO;
import org.opens.urlmanager.entity.dto.webpage.WebpageDTOImpl;

/**
 *
 * @author bcareil
 */
public class RequestXMLSerializationTest extends TestCase {

    public RequestXMLSerializationTest(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testRequestSerialization()
            throws Exception {
        System.out.println("testRequestSerialization");
        JAXBContext jc = JAXBContext.newInstance(
                TagDTO.class, LocaleDTO.class,
                WebpageDTO.class, RequestDTO.class
                );
        Marshaller marshaller = jc.createMarshaller();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        RequestDTO request = createRequest();
        String output, expOutput;

        marshaller.marshal(request, os);
        output = os.toString();
        
        expOutput =
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
                "<request>" +
                  "<id>1</id>" +
                  "<label>request</label>" +
                  "<locales>" +
                    "<locale>" +
                      "<id>0</id>" +
                      "<country>FR</country>" +
                      "<language>fr</language>" +
                      "<longCountry>France</longCountry>" +
                      "<longLanguage>french</longLanguage>" +
                    "</locale>"+
                  "</locales>" +
                  "<tags>" +
                    "<tag>" +
                      "<id>1</id>" +
                      "<label>label</label>" +
                    "</tag>" +
                  "</tags>" +
                "</request>"
                ;
        
        assertEquals(expOutput, output);
    }

    private RequestDTO createRequest() {
        TagDTO tag = new TagDTOImpl(1L, "label");
        RequestDTO request = new RequestDTOImpl(1L, "request");
        LocaleDTO locale = new LocaleDTOImpl(0L, "fr", "french", "FR", "France");
        WebpageDTO webpage = new WebpageDTOImpl(1L, "http://lol.com/", Boolean.TRUE);

        request.addLocale(locale);
        request.addTag(tag);
        webpage.addLocale(locale);
        webpage.addTag(tag);
        return request;
    }
}
