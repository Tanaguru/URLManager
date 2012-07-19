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
import org.apache.commons.logging.LogFactory;
import org.opens.tanaguru.sdk.entity.Entity;
import org.opens.urlmanager.entity.locale.Locale;
import org.opens.urlmanager.entity.service.exception.CannotIdentifyEntityException;
import org.opens.urlmanager.entity.service.exception.EntityNotFoundException;
import org.opens.urlmanager.entity.service.locale.LocaleDataService;
import org.opens.urlmanager.entity.service.tag.TagDataService;
import org.opens.urlmanager.entity.tag.Tag;

/**
 *
 * @author bcareil
 */
public abstract class LocaleAndTagAssociatedDataService <T extends Entity> extends AbstractGenericDataServiceWithDTO<T> {

    private TagDataService tagDataService;
    private LocaleDataService localeDataService;

    // TODO: merge with the same method in RequestDataService
    protected Collection<Tag> preprocessTags(
            Collection<Tag> tags,
            boolean createTagIfNotExists
            ) {
        Collection<Tag> persistedTags = new ArrayList<Tag>(tags.size());
        
        for (Tag tag : tags) {
            if (tag.getId() != null) {
                Tag othTag = tagDataService.read(tag.getId());
                
                if (othTag == null) {
                    LogFactory.getLog(LocaleAndTagAssociatedDataService.class).debug("Invalid tag id");
                    throw new EntityNotFoundException("tag", "invalid id");
                } else {
                    persistedTags.add(othTag);
                }
            } else {
                if (tag.getLabel() == null) {
                    throw new CannotIdentifyEntityException("tag");
                }
                Tag othTag = tagDataService.getTagFromLabel(tag.getLabel());
                
                if (othTag != null) {
                    persistedTags.add(othTag);
                } else if (createTagIfNotExists) {
                    tagDataService.create(tag);
                    persistedTags.add(tag);
                } else {
                    LogFactory.getLog(LocaleAndTagAssociatedDataService.class).debug("Invalid tag label");
                    throw new EntityNotFoundException("tag", "invalid label");                    
                }
            }
        }
        return persistedTags;
    }
    
    // TODO: merge with the same method in RequestDataService
    protected Collection<Locale> preprocessLocales(Collection<Locale> locales) {
        Collection<Locale> persistedLocales = new ArrayList<Locale>(locales.size());
        
        for (Locale locale : locales) {
            if (locale.getId() != null) {
                Locale othLocale = localeDataService.read(locale.getId());
                
                if (othLocale == null) {
                    LogFactory.getLog(LocaleAndTagAssociatedDataService.class).debug("Invalid locale id");
                    throw new EntityNotFoundException("locale", "invalid id");
                } else {
                    persistedLocales.add(othLocale);
                }
            } else {
                if (locale.getCountry() == null ||
                        locale.getLanguage() == null) {
                    throw new CannotIdentifyEntityException("locale");
                } else {
                    Locale othLocale = localeDataService.getLocaleFromLanguageAndCountry(
                            locale.getLanguage(),
                            locale.getCountry()
                            );

                    if (othLocale == null) {
                        LogFactory.getLog(LocaleAndTagAssociatedDataService.class).debug(
                                "Invalid locale : " + locale.getLanguage() + "_" + locale.getCountry()
                                );
                        throw new EntityNotFoundException("locale", "invalid language or country");
                    } else {
                        persistedLocales.add(othLocale);
                    }
                }
            }
        }
        return persistedLocales;
    }
    
    public TagDataService getTagDataService() {
        return tagDataService;
    }

    public void setTagDataService(TagDataService tagDataService) {
        this.tagDataService = tagDataService;
    }

    public LocaleDataService getLocaleDataService() {
        return localeDataService;
    }

    public void setLocaleDataService(LocaleDataService localeDataService) {
        this.localeDataService = localeDataService;
    }

}
