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
package org.opens.urlmanager.test.utils;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author bcareil
 */
public class XMLUtils {
    static public String getFileContent(String filename) throws IOException {
        File file = new File(filename);
        FileInputStream stream = new FileInputStream(file);
        try {
            FileChannel fc = stream.getChannel();
            MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
            
            return Charset.defaultCharset().decode(mbb).toString();
        } finally {
            stream.close();
        }
    }
    
    static public Document createDocumentFromXMLContent(String docContent)
            throws SAXException, ParserConfigurationException, IOException, TransformerConfigurationException, TransformerException
    {
        // create builder
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();

        // create document
        Document doc = docBuilder.parse(new ByteArrayInputStream(docContent.getBytes()));
        
        // create transformer to remove spaces
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer(new StreamSource("src/test/resources/strip-spaces.xls"));
        
        // load doc
        DOMSource source = new DOMSource(doc);
        OutputStream os = new ByteArrayOutputStream();
        StreamResult result = new StreamResult(os);
        
        // remove spaces from doc
        transformer.transform(source, result);

        // re-create doc
        return docBuilder.parse(new ByteArrayInputStream(os.toString().getBytes()));
    }    
}
