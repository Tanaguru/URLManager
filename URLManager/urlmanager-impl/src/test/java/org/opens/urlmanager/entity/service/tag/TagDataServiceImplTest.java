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
import org.opens.urlmanager.entity.service.EntityDTOWrapper;
import org.opens.urlmanager.entity.tag.Tag;

/**
 *
 * @author bcareil
 */
public class TagDataServiceImplTest extends TestCase {
    
    TagDAO tagDao;
    EntityDTOWrapper wrapper;
    
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
        
        tagDao = createMock(TagDAO.class);
        wrapper = createMock(EntityDTOWrapper.class);
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
        Tag tagEntity = createMock(Tag.class);
        Tag tagDto = createMock(Tag.class);
        Tag result;

        /*
         * set-up mock
         */
        expect(tagDao.findTagFromLabel(label)).andReturn(tagEntity);
        expect(wrapper.entityToDto(tagEntity)).andReturn(tagDto);
        //
        replay(tagDao);
        replay(wrapper);
        replay(tagEntity);
        replay(tagDto);
        /*
         * set up instance
         */
        instance.setEntityDao(tagDao);
        instance.setWrapper(wrapper);
        /*
         * run test
         */
        result = instance.getTagFromLabel(label);
        /*
         * asserts
         */
        assertEquals(result, tagDto);
        /*
         * check mock state
         */
        verify(tagDao);
        verify(wrapper);
    }
}
