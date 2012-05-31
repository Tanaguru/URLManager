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
package org.opens.urlmanager.entity.request;

import org.opens.urlmanager.entity.tag.*;
import java.io.ByteArrayOutputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.ElementNameAndTextQualifier;
import org.custommonkey.xmlunit.XMLTestCase;
import org.custommonkey.xmlunit.XMLUnit;
import org.opens.urlmanager.entity.locale.LocaleImpl;
import org.opens.urlmanager.entity.request.RequestImpl;
import org.opens.urlmanager.entity.webpage.WebpageImpl;
import org.opens.urlmanager.test.utils.XMLUtils;
import org.w3c.dom.Document;

/**
 *
 * @author bcareil
 */
public class RequestXMLSerializationTest extends XMLTestCase {

    private static final String EXPECTED_OUTPUT_FILENAME =
            "src/test/resources/request/expected-output.xml";

    public RequestXMLSerializationTest(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        XMLUnit.setIgnoreComments(true);
        XMLUnit.setIgnoreAttributeOrder(true);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testRequestSerialization()
            throws Exception {
        System.out.println("testRequestSerialization");
        JAXBContext jc = JAXBContext.newInstance(
                TagImpl.class, LocaleImpl.class,
                WebpageImpl.class, RequestImpl.class);
        Marshaller marshaller = jc.createMarshaller();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        RequestImpl request = createRequest();
        String output, expOutput;
        Document genDoc, expDoc;


        marshaller.marshal(request, os);
        output = os.toString();
        expOutput = XMLUtils.getFileContent(EXPECTED_OUTPUT_FILENAME);

        System.out.println("-> request entity marshalled :");
        System.out.println(output);

        expDoc = XMLUtils.createDocumentFromXMLContent(expOutput);
        genDoc = XMLUtils.createDocumentFromXMLContent(output);
        
        Diff diff = new Diff(expDoc, genDoc);
        
        assertXMLEqual(diff, diff.identical());
    }

    private RequestImpl createRequest() {
        TagImpl tag = new TagImpl(1L, "label");
        RequestImpl request = new RequestImpl(1L, "request");
        LocaleImpl locale = new LocaleImpl(1L, "fr", "french", "FR", "France");
        WebpageImpl webpage = new WebpageImpl(1L, "http://lol.com/", Boolean.TRUE);

        request.addLocale(locale);
        request.addTag(tag);
        webpage.addLocale(locale);
        webpage.addTag(tag);
        locale.addRequest(request);
        locale.addWebpage(webpage);
        tag.addRequest(request);
        tag.addWebpage(webpage);
        return request;
    }
}
