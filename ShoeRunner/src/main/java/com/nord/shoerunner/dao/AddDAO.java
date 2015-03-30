/* 
 * Copyright (c) 2014 Nordstrom, Inc. All Rights reserved.
 * This software is the confidential and proprietary
 * information of Nordstrom, Inc. ("Confidential Information").
 * You shall not disclose such Confidential Information
 * and shall use it only in accordance with the terms of the 
 * license agreement you entered into with Nordstrom, Inc.
 */
package com.nord.shoerunner.dao;
import org.hibernate.Session;

/**
 *
 * @author utys
 * @param <E>
 */
public interface AddDAO<E> extends BaseDAO {

    public default E add(E dataObject, Class tableName) {
        Session session = getSession();
        session.save(dataObject);
        flushCommitAndClose(session);
        return dataObject;
    }

    public E add(E dataObject);
}
