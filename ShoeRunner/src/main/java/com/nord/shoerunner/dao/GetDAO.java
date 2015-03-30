/** 
 * Copyright (c) 2014 Nordstrom, Inc. All Rights reserved.
 * This software is the confidential and proprietary
 * information of Nordstrom, Inc. ("Confidential Information").
 * You shall not disclose such Confidential Information
 * and shall use it only in accordance with the terms of the 
 * license agreement you entered into with Nordstrom, Inc.
 */
package com.nord.shoerunner.dao;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author utys
 * @param <E>
 */
public interface GetDAO<E> extends BaseDAO {

    static final int PARAMETER_MODIFIER_INDEX = 1;
    static final int PARAMETER_NAME_INDEX = 0;
    static final String PARAMETER_DELIMETER = ":";

    default Collection<E> get(Map parameters, Class tableName) {

        Session session = getSession();
        Collection<E> result = getWithOpenSession(parameters, tableName, session);
        commitAndClose(session);
        return result;
    }

    default Collection<E> getWithOpenSession(Map parameters, Class tableName, Session session) {

        // if the Sesssion has been committed and/or closed, get a new one
        if (!session.isOpen()) {
            session = getSession();
        }
        
        Criteria query = session.createCriteria(tableName);
        Iterator iterator = parameters.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry parameter = (Map.Entry) iterator.next();
            String[] expandedParameter = parameter.getKey().toString().split(PARAMETER_DELIMETER);
            switch (expandedParameter[PARAMETER_NAME_INDEX]) {
                case "orderBy":
                    if (expandedParameter[PARAMETER_MODIFIER_INDEX].equals("asc")) {
                        query.addOrder(Order.asc((String) parameter.getValue()));
                    } else {
                        query.addOrder(Order.desc((String) parameter.getValue()));
                    }
                    break;
                case "limit":
                    query.setMaxResults((int) parameter.getValue());
                    break;
                case "notEqual":
                    query.add(Restrictions.ne((String) parameter.getValue(), expandedParameter[PARAMETER_MODIFIER_INDEX]));
                    break;
                case "lockMode":
                    query.setLockMode((LockMode)parameter.getValue());
                    break;
                default:
                    query.add(Restrictions.eq((String) parameter.getKey(), parameter.getValue()));
                    break;
            }
        }
        return query.list();
    }

    public Collection<E> get(Map parameters);
}
