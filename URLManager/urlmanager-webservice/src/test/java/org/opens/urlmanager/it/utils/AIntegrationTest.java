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
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;

/**
 *
 * @author bcareil
 */
public abstract class AIntegrationTest extends DBTestCase {
    
    private static final String JDBC_URL = "jdbc:hsqldb:hsql://localhost/urlmanager";
    private static final String JDBC_DRIVER_CLASS = "org.hsqldb.jdbcDriver";
    private static final String PASSWORD = "";
    private static final String USER = "sa";
    public  static final String DEFAULT_DATASET_FILENAME =
            "src/test/resources/functional-tests/db/default-dataset.xml";
    public  static final String EXPECTED_OUTPUT_FILES_DIRECTORY =
            "src/test/resources/functional-tests/expected-results/";
    public  static final Map<String, String> EXT_MIME_TYPES =
            Collections.unmodifiableMap(new HashMap<String, String>() {
                {
                    put("xml", "application/xml");
                    put("json", "application/json");
                    put("html", "text/html");
                }
            });

    private String inputDataSetFilename = DEFAULT_DATASET_FILENAME;
    
    public AIntegrationTest(String testName) {
        super(testName);
        
        System.setProperty(
                PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS,
                JDBC_DRIVER_CLASS
                );
        System.setProperty(
                PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,
                JDBC_URL
                );
        System.setProperty(
                PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME,
                USER
                );
        System.setProperty(
                PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,
                PASSWORD
                );
    }
    
    public File getExpectedOutputDirectory(String entity, String operation) {
        StringBuilder path = new StringBuilder();
        
        path.append(EXPECTED_OUTPUT_FILES_DIRECTORY);
        if (entity != null) {
            path.append("/").append(entity);
            if (operation != null)
                path.append("/").append(operation);
        }
        return new File(path.toString());
    }

    public String getExpectedOutputFilename(String filename) {
        return EXPECTED_OUTPUT_FILES_DIRECTORY + filename;
    }
    
    public void setInputDataSetFilename(String inputDataSetFilename) {
        this.inputDataSetFilename = inputDataSetFilename;
    }
    
    public String getInputDataSetFilename() {
        return inputDataSetFilename;
    }

    @Override
    protected DatabaseOperation getSetUpOperation() throws Exception {
        return DatabaseOperation.CLEAN_INSERT;
    }

    @Override
    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.DELETE_ALL;
    }
    
    @Override
    protected IDataSet getDataSet() throws Exception {
        FlatXmlDataSetBuilder flatXmlDataSetBuilder = new FlatXmlDataSetBuilder();
        FlatXmlDataSet flatXmlDataSet = flatXmlDataSetBuilder.build(
                new FileInputStream(new File(this.inputDataSetFilename))
                );
        return flatXmlDataSet;
    }
    
}
