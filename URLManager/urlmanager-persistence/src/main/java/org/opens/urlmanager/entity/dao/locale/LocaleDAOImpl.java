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
package org.opens.urlmanager.entity.dao.locale;

import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.opens.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;
import org.opens.urlmanager.entity.locale.Locale;
import org.opens.urlmanager.entity.locale.LocaleImpl;

/**
 *
 * @author bcareil
 */
public class LocaleDAOImpl extends AbstractJPADAO<Locale, Long> implements LocaleDAO {

    public LocaleDAOImpl() {
        super();
    }

    @Override
    protected Class<? extends Locale> getEntityClass() {
        return LocaleImpl.class;
    }

    public List<Locale> findLocaleListFromLanguage(String language) {
        Query query;
        
        query = entityManager.createQuery("SELECT l FROM " +
                getEntityClass().getName() + " l " +
                "WHERE Language=:language");
        query.setParameter("language", language);
        return (List<Locale>) query.getResultList();
    }

    public List<Locale> findLocaleListFromCountry(String country) {
        Query query;
        
        query = entityManager.createQuery("SELECT l FROM " +
                getEntityClass().getName() + " l " +
                "WHERE Country=:country");
        query.setParameter("country", country);
        return (List<Locale>) query.getResultList();
    }

    public List<Locale> findLocaleListFromLongLanguage(String longLanguage) {
        Query query;
        
        query = entityManager.createQuery("SELECT l FROM " +
                getEntityClass().getName() + " l " +
                "WHERE Long_Language=:longLanguage");
        query.setParameter("longLanguage", longLanguage);
        return (List<Locale>) query.getResultList();
    }

    public List<Locale> findLocaleListFromLongCountry(String longCountry) {
        Query query;
        
        query = entityManager.createQuery("SELECT l FROM " +
                getEntityClass().getName() + " l " +
                "WHERE Long_Country=:longCountry");
        query.setParameter("longCountry", longCountry);
        return (List<Locale>) query.getResultList();
    }

    public Locale findLocaleFromLanguageAndCountry(String language, String country) {
        Query query;
        
        query = entityManager.createQuery("SELECT l FROM " +
                getEntityClass().getName() + " l " +
                "WHERE Language=:language AND Country=:country");
        query.setParameter("country", country);
        query.setParameter("language", language);
        try {
            return (Locale) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Locale findLocaleFromLongLanguageAndLongCountry(String longLanguage, String longCountry) {
        Query query;
        
        query = entityManager.createQuery("SELECT l FROM " +
                getEntityClass().getName() + " l " +
                "WHERE Long_Language=:longLanguage AND Long_Country=:longCountry");
        query.setParameter("longCountry", longCountry);
        query.setParameter("longLanguage", longLanguage);
        try {
            return (Locale) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
}
