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
package org.opens.urlmanager.entity.webpage;

import java.io.ByteArrayOutputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import org.custommonkey.xmlunit.*;
import org.opens.urlmanager.entity.locale.LocaleImpl;
import org.opens.urlmanager.entity.request.RequestImpl;
import org.opens.urlmanager.entity.tag.TagImpl;
import org.opens.urlmanager.test.utils.XMLUtils;
import org.w3c.dom.Document;

/**
 *
 * @author bcareil
 */
public class WebpageXMLSerializationTest extends XMLTestCase {

    private static final String EXPECTED_OUTPUT_FILENAME =
            "src/test/resources/webpage/expected-output.xml";
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        XMLUnit.setIgnoreComments(true);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    
    public void testWebpageSerialization() throws Exception {
        System.out.println("testWebpageSerialization");
        JAXBContext jc = JAXBContext.newInstance(
                TagImpl.class, LocaleImpl.class,
                WebpageImpl.class, RequestImpl.class
                );
        Marshaller marshaller = jc.createMarshaller();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        WebpageImpl webpage = createWebpage();
        String output, expOutput;
        Document doc, expDoc;
        
        marshaller.marshal(webpage, os);
        
        output = os.toString();
        expOutput = XMLUtils.getFileContent(EXPECTED_OUTPUT_FILENAME);
        
        doc = XMLUtils.createDocumentFromXMLContent(output);
        expDoc = XMLUtils.createDocumentFromXMLContent(expOutput);
        
        System.out.println("webpage entity marshalled :");
        System.out.println(os.toString());
        
        Diff diff = new Diff(expDoc, doc, null, new ElementNameQualifier());
        assertXMLEqual(diff, diff.similar());
    }

    private WebpageImpl createWebpage() {
        TagImpl tag = new TagImpl(1L, "label");
        RequestImpl request = new RequestImpl(1L, "request");
        LocaleImpl locale_fr_FR = new LocaleImpl(1L, "fr", "french", "FR", "France");
        LocaleImpl locale_en_GB = new LocaleImpl(2L, "en", "english", "GB", "Great Britain");
        WebpageImpl webpage = new WebpageImpl(1L, "http://lol.com/", Boolean.TRUE);
        
        request.addLocale(locale_fr_FR);
        request.addLocale(locale_en_GB);
        request.addTag(tag);
        webpage.addLocale(locale_fr_FR);
        webpage.addLocale(locale_en_GB);
        webpage.addTag(tag);
        locale_fr_FR.addRequest(request);
        locale_fr_FR.addWebpage(webpage);
        locale_en_GB.addRequest(request);
        locale_en_GB.addWebpage(webpage);
        tag.addRequest(request);
        tag.addWebpage(webpage);
        return webpage;
    }
}
