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
package org.opens.urlmanager.it;

import java.io.File;
import java.io.FileInputStream;
import junit.framework.Test;
import junit.framework.TestSuite;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.dbunit.operation.DatabaseOperation;
import org.opens.urlmanager.entity.webpage.Webpage;
import org.opens.urlmanager.entity.webpage.WebpageImpl;
import org.opens.urlmanager.it.utils.AHttpRequestBasedTest;
import org.opens.urlmanager.it.utils.EntityXMLUnserializer;

/**
 *
 * @author bcareil
 */
public class CreateITCase extends AHttpRequestBasedTest {

    private boolean isFirstSetUp = true;
    
    public CreateITCase(String testName) {
        super(testName);
    }
    
    public static Test suite() {
        return new TestSuite(CreateITCase.class);
    }

    @Override
    protected DatabaseOperation getSetUpOperation() throws Exception {
        if (isFirstSetUp) {
            return DatabaseOperation.CLEAN_INSERT;
        }
        return DatabaseOperation.NONE;
    }
    
    @Override
    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.NONE;
    }
    
    /**
     * List all files in INPUT_FILES_PATH/entity/operation, excluding files
     * begining with a dot or without dot in the name, and then, use this files
     * as input files for the request (here, create). The format of the filename
     * should be [err-&lt;code&gt;-]create-&lt;number&gt;.&lt;ext&gt; where
     * <list>
     * <li> code is the expected HTTP Status code (if unspecified, 200)</li>
     * <li> numer is a random and ignored number (for now)</li>
     * <li> ext is the file extension and must be chosen among xml, json and urle,
     *      respectively corresponding to the mime type application/xml,
     *      application/json and application/x-www-urlencoded</li>
     * </list>
     */
    public void testCreateWebpage() throws Exception {
        final String url = "rest/" + "webpage" + "/" + "create";
        final String INPUT_FILES_PATH = "src/test/resources/functional-tests/inputs/";
        File directory = new File(INPUT_FILES_PATH + "webpage" + "/" + "create");
        
        for (File file : directory.listFiles()) {
            String filename;
            
            filename = file.getName();
            // skip files begining with a dot or without dot in the name
            if (filename.indexOf(".") > 0) {
                HttpResponse response;
                String extension;
                String[] splitedFilename;
                int expectedStatusCode = 200;
                
                extension = filename.substring(filename.indexOf('.') + 1);
                if (EXT_MIME_TYPES.containsKey(extension) == false) {
                    System.out.println(
                            "No mime type for extension " + extension + ". " +
                            "Skipping file " + filename + ".");
                    continue;
                }
                splitedFilename = filename.split("-");
                if (splitedFilename[0].equals("err")) {
                    expectedStatusCode = Integer.valueOf(splitedFilename[1]);
                }
                
                LogFactory.getLog(CreateITCase.class).debug(
                        "(url=\"" + url + "\", " +
                        "filename=\"" +filename + "\", " +
                        "extension=\"" + extension + "\", " +
                        "expectedStatusCode=" + String.valueOf(expectedStatusCode) + ")");
                response = doRequest(url, file, EXT_MIME_TYPES.get(extension));
                
                assertEquals(expectedStatusCode, response.getStatusLine().getStatusCode());
                if (expectedStatusCode == 200) {
                    Webpage expected = EntityXMLUnserializer.unserialize(
                            new FileInputStream(file),
                            WebpageImpl.class
                            );
                    Webpage created = EntityXMLUnserializer.unserialize(
                            response.getEntity().getContent(),
                            WebpageImpl.class
                            );
                    
                    expected.setId(created.getId());
                    assertNotNull(expected.getId());
                    assertEquals(expected, created);
                }
            }
        }
    }

    private HttpResponse doRequest(String url, File file, String mime)
            throws Exception {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://localhost:8080/urlmanager/" + url);
        
        httpPost.addHeader("Accept", mime);
        httpPost.setEntity(new InputStreamEntity(new FileInputStream(file), file.length(), ContentType.create(mime)));
        return httpClient.execute(httpPost);
    }
}
