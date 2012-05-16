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
package org.opens.urlmanager.entity.dao.tag;

import junit.framework.Test;
import junit.framework.TestSuite;
import org.opens.urlmanager.entity.tag.Tag;
import org.opens.urlmanager.entity.tag.TagImpl;
import org.opens.urlmanager.persistence.AbstractDaoTestCase;

/**
 *
 * @author bcareil
 */
public class TagDAOImplTest extends AbstractDaoTestCase {
    
    private TagDAO tagDAO;
    
    public TagDAOImplTest(String testName) {
        super(testName, "src/test/resources/dataSets/flatDataSet.xml");
        tagDAO = (TagDAO) springBeanFactory.getBean("tagDAO");
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(TagDAOImplTest.class);
        return suite;
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of getEntityClass method, of class TagDAOImpl.
     */
    public void testGetEntityClass() {
        System.out.println("getEntityClass");
        TagDAOImpl instance = new TagDAOImpl();
        Class expResult = TagImpl.class;
        Class result = instance.getEntityClass();
        assertEquals(expResult, result);
    }

    /**
     * Test of findTagFromLabel method, of class TagDAOImpl.
     */
    public void testFindTagFromLabel() {
        System.out.println("findTagFromLabel");
        Tag result;
        
        System.out.println("-> existing tag");
        result = tagDAO.findTagFromLabel("lol");
        // expected result : the tag of label="lol" and Id_Tag=1
        assertNotNull(result);
        assertEquals(Long.valueOf(1), result.getId());
        
        System.out.println("-> invalid tag");
        result = tagDAO.findTagFromLabel("blah");
        // expected result : no matching tag found : null
        assertNull(result);
    }
}
