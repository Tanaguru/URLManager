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
package org.opens.urlmanager.entity.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import junit.framework.TestCase;
import org.opens.urlmanager.entity.dto.locale.LocaleDTO;
import org.opens.urlmanager.entity.dto.request.RequestDTO;
import org.opens.urlmanager.entity.dto.tag.TagDTO;
import org.opens.urlmanager.entity.dto.webpage.WebpageDTO;
import org.opens.urlmanager.entity.locale.Locale;
import org.opens.urlmanager.entity.locale.LocaleImpl;
import org.opens.urlmanager.entity.request.RequestImpl;
import org.opens.urlmanager.entity.tag.TagImpl;
import org.opens.urlmanager.entity.webpage.Webpage;
import org.opens.urlmanager.entity.webpage.WebpageImpl;

/**
 *
 * @author bcareil
 */
public class EntityDTOWrapperTest extends TestCase {
    
    public EntityDTOWrapperTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    private EntityDTOWrapper getInstance() {
        EntityDTOWrapper ofthejedi;
        Map<Class, Class> dtoForEntity = new HashMap();
        Map<Class, Class> entityForDto = new HashMap();

        // locale
        dtoForEntity.put(LocaleImpl.class, LocaleDTO.class);
        entityForDto.put(LocaleDTO.class, LocaleImpl.class);
        // request
        dtoForEntity.put(RequestImpl.class, RequestDTO.class);
        entityForDto.put(RequestDTO.class, RequestImpl.class);
        // tag
        dtoForEntity.put(TagImpl.class, TagDTO.class);
        entityForDto.put(TagDTO.class, TagImpl.class);
        // webpage
        dtoForEntity.put(WebpageImpl.class, WebpageDTO.class);
        entityForDto.put(WebpageDTO.class, WebpageImpl.class);
        
        ofthejedi = new EntityDTOWrapper();
        ofthejedi.setDtoClassForEntityClass((Map) dtoForEntity);
        ofthejedi.setEntityClassForDtoClass((Map) entityForDto);
        return ofthejedi;
    }
    
    /**
     * Test of entityToDto method, of class AbstractGenericDataServiceWithDTO.
     */
    public void testEntityToDto() {
        System.out.println("entityToDto");
        /* */
        Long id = 1L;
        String url = "url";
        Boolean isRoot = true;
        Locale locale = new LocaleImpl(1L, "fr", "french", "FR", "France");
        /* */
        Webpage entity = new WebpageImpl(id, url, isRoot);
        
        entity.addLocale(locale);
        locale.addWebpage(entity);
        /* */
        EntityDTOWrapper instance = getInstance();
        /* */
        Webpage result = (Webpage) instance.entityToDto(entity);
        /* */
        assertNotNull(result);
        assertTrue(result instanceof WebpageDTO);
        assertEquals(id, result.getId());
        assertEquals(url, result.getURL());
        assertEquals(isRoot, result.getIsRoot());
        /* */
        LocaleDTO localeDTO;
        
        assertNotNull(result.getLocales());
        assertEquals(1, result.getLocales().size());
        assertTrue(result.getLocales().toArray()[0] instanceof LocaleDTO);
        localeDTO = (LocaleDTO) result.getLocales().toArray()[0];
        assertEquals(Long.valueOf(1L), localeDTO.getId());
        assertEquals("fr_FR", localeDTO.getLabel());
    }

    /**
     * Test of dtoToEntity method, of class AbstractGenericDataServiceWithDTO.
     */
    public void testDtoToEntity() {
        System.out.println("dtoToEntity");
        /* */
        Long id = 1L;
        String url = "url";
        Boolean isRoot = true;
        /* */
        Webpage dto = new WebpageDTO(id, url, isRoot);
        /* */
        EntityDTOWrapper instance = getInstance();
        /* */
        Webpage result = (Webpage) instance.dtoToEntity(dto);
        /* */
        assertNotNull(result);
        assertTrue(result instanceof WebpageImpl);
        assertEquals(id, result.getId());
        assertEquals(url, result.getURL());
        assertEquals(isRoot, result.getIsRoot());
    }

    /**
     * Test of entityCollectionToDtoCollection method, of class AbstractGenericDataServiceWithDTO.
     */
    public void testEntityCollectionToDtoCollection() {
        System.out.println("entityCollectionToDtoCollection");
        /* */
        Long id = 1L;
        String url = "url";
        Boolean isRoot = true;        
        /* */
        Webpage webpage = new WebpageImpl(id, url, isRoot);
        Collection<Webpage> entities = new ArrayList<Webpage>();
        entities.add(webpage);
        /* */
        EntityDTOWrapper instance = getInstance();
        /* */
        Collection<Webpage> result = (Collection) instance.entityCollectionToDtoCollection((Collection) entities);
        /* */
        assertNotNull(result);
        assertTrue(result instanceof ArrayList);
        assertEquals(1, result.size());
        assertTrue(result.toArray()[0] instanceof WebpageDTO);
        /* */
        WebpageDTO result0 = (WebpageDTO) result.toArray()[0];
        assertEquals(id, result0.getId());
        assertEquals(url, result0.getURL());
        assertEquals(isRoot, result0.getIsRoot());
    }

    /**
     * Test of dtoCollectionToEntityCollection method, of class AbstractGenericDataServiceWithDTO.
     */
    public void testDtoCollectionToEntityCollection() {
        System.out.println("dtoCollectionToEntityCollection");
        /* */
        Long id = 1L;
        String url = "url";
        Boolean isRoot = true;        
        /* */
        Webpage webpage = new WebpageDTO(id, url, isRoot);
        Collection<Webpage> entities = new ArrayList<Webpage>();
        entities.add(webpage);
        /* */
        EntityDTOWrapper instance = getInstance();
        /* */
        Collection<Webpage> result = (Collection) instance.dtoCollectionToEntityCollection((Collection) entities);
        /* */
        assertNotNull(result);
        assertTrue(result instanceof ArrayList);
        assertEquals(1, result.size());
        assertTrue(result.toArray()[0] instanceof WebpageImpl);
        /* */
        WebpageImpl result0 = (WebpageImpl) result.toArray()[0];
        assertEquals(id, result0.getId());
        assertEquals(url, result0.getURL());
        assertEquals(isRoot, result0.getIsRoot());
    }

}
