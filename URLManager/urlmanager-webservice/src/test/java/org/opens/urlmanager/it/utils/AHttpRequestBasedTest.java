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
package org.opens.urlmanager.it.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map.Entry;
import java.util.*;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.opens.urlmanager.it.ListITCase;

/**
 *
 * @author bcareil
 */
public abstract class AHttpRequestBasedTest extends AIntegrationTest {

    public enum HttpMethod {

        GET("GET"),
        POST("POST");
        
        public static final int COUNT = HttpMethod.values().length;
        
        private final String string;

        private HttpMethod(String string) {
            this.string = string;
        }

        @Override
        public String toString() {
            return this.string;
        }
    }
    private static final String BASE_URL = "http://localhost:8080/urlmanager";

    public AHttpRequestBasedTest(String testName) {
        super(testName);
    }
    

    protected String getInputStreamContent(InputStream is)
            throws Exception {
        try {
            return new Scanner(is, "UTF-8").useDelimiter("\\A").next();
        } catch (NoSuchElementException e) {
            return "";
        }
    }

    protected String getFileContent(String filename)
            throws Exception {
        return getInputStreamContent(new FileInputStream(filename));
    }

    protected void doTest(
            String url,
            Map<String, String> parameters,
            HttpMethod httpMethod,
            final String contentType,
            String expectedOutput,
            int expectedStatusCode
            ) throws Exception {
        LogFactory.getLog(ListITCase.class).debug(
                "doTest(\"" + url + "\", " +
                httpMethod.toString() + ", " +
                "\"" + parameters + "\", " +
                "\"" + contentType + "\", " +
                (expectedOutput == null ? "null" :
                    "\"" + expectedOutput.substring(0, 16) +
                    (expectedOutput.length() > 16 ? "[...]" : "") + "\"") +
                ", " +
                String.valueOf(expectedStatusCode) + ")"
                );
        // execute the request on the given url, with the
        // given method, accepting the given contentType
        // and specifying the entity id.
        HttpResponse response = doRequest(
                url,
                httpMethod,
                new HashMap<String, String>() {
                    {
                        put("Accept", contentType);
                    }
                },
                parameters
                );
        // check status code
        assertEquals(expectedStatusCode, response.getStatusLine().getStatusCode());
        // if we expect an output
        if (expectedOutput != null) {
            Header[] responseContentType;

            // check its content type
            responseContentType = response.getHeaders("Content-Type");
            assertNotNull(responseContentType);
            assertEquals(1, responseContentType.length);
            assertTrue(responseContentType[0].getValue().indexOf(contentType) != -1);
            // then the output itself
            assertEquals(
                    expectedOutput,
                    getInputStreamContent(response.getEntity().getContent())
                    );
        }
    }
    
    protected HttpResponse doRequest(
            String subURL,
            HttpMethod method,
            Map<String, String> header,
            Map<String, String> parameters
            ) throws Exception {
        HttpClient httpClient = new DefaultHttpClient();
        try {
            Iterator<Entry<String, String>> it;
            HttpUriRequest httpRequest;
            // create URL
            String url = BASE_URL + subURL;

            if (method == HttpMethod.GET) {
                // append parameters in case of a GET request
                if (parameters != null) {
                    url = appendParametersToURL(url, parameters);
                }
                httpRequest = (HttpUriRequest) new HttpGet(url);
            } else {
                // in case of a post request, fill request body with the parameters
                HttpPost httpPost = new HttpPost(url);
                
                if (parameters != null) {
                    List<NameValuePair> nvps = new ArrayList<NameValuePair>(parameters.size());

                    // fill up entity (body)
                    it = parameters.entrySet().iterator();
                    while (it.hasNext()) {
                        Entry<String, String> entry = it.next();

                        assert(entry.getKey() != null);
                        assert(entry.getValue() != null);
                        nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                    }
                    httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
                }
                httpRequest = httpPost;
            }

            // fill up header
            it = header.entrySet().iterator();
            while (it.hasNext()) {
                Entry<String, String> entry = it.next();

                httpRequest.addHeader(entry.getKey(), entry.getValue());
            }

            // execute query
            HttpResponse response = httpClient.execute(httpRequest);
            return response;
        } finally {
            // FIXME: should be done :3
            //httpClient.getConnectionManager().shutdown();
        }
    }

    private String appendParametersToURL(
            String url,
            Map<String, String> parameters
            ) throws Exception {
        StringBuilder sb = new StringBuilder();

        sb.append(url).append("?").append(encodeParameters(parameters));
        return sb.toString();
    }

    private String encodeParameters(
            Map<String, String> parameters
            ) throws Exception {
        boolean isFirstParameter = true;
        StringBuilder sb = new StringBuilder();
        Iterator<Entry<String, String>> it;

        it = parameters.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, String> entry = it.next();

            if (isFirstParameter) {
                isFirstParameter = false;
            } else {
                encodeAndAppendToStringBuilder(sb, "&");
            }
            encodeAndAppendToStringBuilder(sb, entry.getKey());
            sb.append("=");
            encodeAndAppendToStringBuilder(sb, entry.getValue());
        }
        return sb.toString();
    }

    private void encodeAndAppendToStringBuilder(
            StringBuilder sb, String string) throws UnsupportedEncodingException {
        sb.append(URLEncoder.encode(string, "UTF-8"));
    }
}
