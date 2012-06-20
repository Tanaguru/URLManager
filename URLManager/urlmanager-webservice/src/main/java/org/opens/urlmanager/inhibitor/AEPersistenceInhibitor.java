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
package org.opens.urlmanager.inhibitor;

import javax.persistence.PersistenceException;
import org.apache.commons.logging.LogFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.SQLGrammarException;
import org.opens.urlmanager.entity.service.exception.CannotIdentifyEntityException;
import org.opens.urlmanager.entity.service.exception.EntityNotFoundException;
import org.opens.urlmanager.rest.exception.BadClientRequestException;
import org.opens.urlmanager.rest.exception.InternalErrorException;
import org.opens.urlmanager.rest.exception.ResourceNotFoundException;

/**
 *
 * @author bcareil
 */
public class AEPersistenceInhibitor {
    
    /**
     * Inhibit data service and persistence exceptions
     * @see beans-aop-config.xml
     * @param e The exception to handle
     */
    public void inhibitException(Exception e) {
        LogFactory.getLog(AEPersistenceInhibitor.class).debug("Inhibiting exception", e);
        if (e instanceof PersistenceException) {
            if (e.getCause() instanceof ConstraintViolationException) {
                // TODO : create the appropriate exception with its appropriate HTTP error code
                throw new InternalErrorException("Constraint violation exception", e);
            } else if (e.getCause() instanceof SQLGrammarException) {
                throw new InternalErrorException("SQL grammar error", e);
            } else {
                throw new InternalErrorException("Persistence error : " + e.getMessage(), e);
            }
        } else  if (e instanceof CannotIdentifyEntityException) {
            throw new BadClientRequestException("One of the supplied entity can not be identified : " + e.getMessage(), e);
        } else if (e instanceof EntityNotFoundException) {
            throw new ResourceNotFoundException("Entity not found : " + e.getMessage(), e);
        } else {
            throw new InternalErrorException("Error : " + e.getMessage(), e);
        }
    }
}
