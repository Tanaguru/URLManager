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
import java.util.Iterator;
import java.util.Map.Entry;
import junit.framework.Test;
import junit.framework.TestSuite;
import org.dbunit.operation.DatabaseOperation;
import org.opens.urlmanager.it.utils.AHttpRequestBasedTest;

/**
 *
 * @author bcareil
 */
public class ListITCase extends AHttpRequestBasedTest {

    private boolean isFirstSetUp = true;
    
    public ListITCase(String testName) {
        super(testName);
    }
    
    public static Test suite() {
        return new TestSuite(ListITCase.class);
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
    
    public void testListLocale() throws Exception  {
        doListTestOnEntity("locale");
    }
    
    public void testListRequest() throws Exception  {
        doListTestOnEntity("request");        
    }
    
    public void testListTag() throws Exception  {
        doListTestOnEntity("tag");        
    }
    
    public void testListWebpage() throws Exception  {
        doListTestOnEntity("webpage");        
    }
    
    private void doListTestOnEntity(
            String entity
            ) throws Exception {
        final String url = "/rest/" + entity + "/list";

        // for each mime type
        Iterator<Entry<String, String>> mimeTypesIterators;
        mimeTypesIterators = EXT_MIME_TYPES.entrySet().iterator();
        while (mimeTypesIterators.hasNext()) {
            File expectedOutputFile;
            String expectedOutput = null;
            Entry<String, String> extMimeType = mimeTypesIterators.next();

            // set the expected output if we find it
            expectedOutputFile = new File(
                    getExpectedOutputFilename(
                        entity + "/list/list." + extMimeType.getKey()
                    ));
            if (expectedOutputFile.canRead()) {
                expectedOutput = getInputStreamContent(
                        new FileInputStream(expectedOutputFile)
                        );
            }
            
            doTest(url, null, HttpMethod.GET, extMimeType.getValue(), expectedOutput, 200);
        }
    }
        
}
