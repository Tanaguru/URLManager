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

import junit.framework.Test;
import junit.framework.TestSuite;
import org.dbunit.operation.DatabaseOperation;
import org.opens.urlmanager.it.utils.AExpectedOutputBasedTest;

/**
 *
 * @author bcareil
 */
public class ReadITCase extends AExpectedOutputBasedTest {

    private boolean isFirstSetUp = true;
    
    public ReadITCase(String testName) {
        super(testName);
    }
    
    public static Test suite() {
        return new TestSuite(ReadITCase.class);
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
    
    public void testReadLocale() throws Exception  {
        runTestsWithExpectedOutputs("locale", "read", true);
    }
    
    public void testReadRequest() throws Exception  {
        runTestsWithExpectedOutputs("request", "read", true);        
    }
    
    public void testReadTag() throws Exception  {
        runTestsWithExpectedOutputs("tag", "read", true);        
    }
    
    public void testReadWebpage() throws Exception  {
        runTestsWithExpectedOutputs("webpage", "read", true);        
    }
}
