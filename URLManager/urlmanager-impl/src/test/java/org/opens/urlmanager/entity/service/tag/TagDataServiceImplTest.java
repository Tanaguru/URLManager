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
package org.opens.urlmanager.entity.service.tag;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import static org.easymock.EasyMock.*;
import org.opens.urlmanager.entity.dao.tag.TagDAO;

/**
 *
 * @author bcareil
 */
public class TagDataServiceImplTest extends TestCase {
    
    TagDAO mock;
    
    public TagDataServiceImplTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(TagDataServiceImplTest.class);
        return suite;
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        mock = createMock(TagDAO.class);
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of getTagFromLabel method, of class TagDataServiceImpl.
     */
    public void testGetTagFromLabel() {
        System.out.println("getTagFromLabel");
        String label = "";
        TagDataServiceImpl instance = new TagDataServiceImpl();

        /*
         * set-up mock
         */
        // We dont care of the return value since getTagFromLabel is just a return
        expect(mock.findTagFromLabel(label)).andReturn(null);
        replay(mock);
        /*
         * run test
         */
        instance.setEntityDao(mock);
        instance.getTagFromLabel(label);
        /*
         * check mock state
         */
        verify(mock);
        }
}
