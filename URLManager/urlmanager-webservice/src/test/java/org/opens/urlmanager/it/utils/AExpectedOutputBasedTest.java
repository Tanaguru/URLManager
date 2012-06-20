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

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

/**
 *
 * @author bcareil
 */
public class AExpectedOutputBasedTest extends AHttpRequestBasedTest {

    public AExpectedOutputBasedTest(String testName) {
        super(testName);
    }
    
    protected void runTestsWithExpectedOutputs(
            String entity,
            String operation,
            boolean useZeroAsInvalidId
            ) throws Exception {
        File expectedOutputDirectory = getExpectedOutputDirectory(entity, operation);
        Set<String> ids = new HashSet();
        Long invalidId = new Long(0L);

        // list valid id for wich an expected output exist
        for (File file : expectedOutputDirectory.listFiles()) {
            String filename;
            int indexOfDot;
            
            filename = file.getName();
            indexOfDot = filename.indexOf('.');
            // ignore file begining with "." or without "."
            if (indexOfDot > 0) {
                ids.add(filename.substring(0, indexOfDot));
            }
        }
        
        // launch tests on valid ids
        for (String id : ids) {
            doReadTestOnEntity(entity, operation, id, 200);
        }
        
        if (useZeroAsInvalidId == false) {
            // if we can't use zero as invalid id, look for another one
            Long max = 1L;
            
            for (String id : ids) {
                Long idAsLong = Long.valueOf(id);
                
                if (idAsLong > max) {
                    max = idAsLong;
                }
            }
            invalidId = max + 1;
        }
        doReadTestOnEntity(entity, operation, String.valueOf(invalidId), 404);
    }
    
    protected void doReadTestOnEntity(
            String entity,
            String operation, 
            String id,
            int expectedStatusCode
            ) throws Exception {
        final String url = "/rest/" + entity + "/" + operation;
        Map<String, String> parameters = new HashMap();

        parameters.put("id", id);
        // for each mime type
        Iterator<Map.Entry<String, String>> mimeTypesIterators;
        mimeTypesIterators = EXT_MIME_TYPES.entrySet().iterator();
        while (mimeTypesIterators.hasNext()) {
            File expectedOutputFile;
            String expectedOutput = null;
            Map.Entry<String, String> extMimeType = mimeTypesIterators.next();

            // set the expected output if we find it
            expectedOutputFile = new File(
                    getExpectedOutputFilename(
                        entity + "/" + operation + "/" + id + "." + extMimeType.getKey()
                    ));
            if (expectedOutputFile.canRead()) {
                expectedOutput = getInputStreamContent(
                        new FileInputStream(expectedOutputFile)
                        );
            }
            
            // for each http method
            for (int i = 0; i < HttpMethod.values().length; ++i) {
                HttpMethod httpMethod;

                httpMethod = HttpMethod.values()[i];
                // do read test on the specified entity with the specified id
                doTest(url, parameters, httpMethod, extMimeType.getValue(), expectedOutput, expectedStatusCode);
            }
        }
    }    
}
