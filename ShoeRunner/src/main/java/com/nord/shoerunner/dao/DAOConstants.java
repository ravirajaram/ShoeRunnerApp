/* 
 * Copyright (c) 2014 Nordstrom, Inc. All Rights reserved.
 * This software is the confidential and proprietary
 * information of Nordstrom, Inc. ("Confidential Information").
 * You shall not disclose such Confidential Information
 * and shall use it only in accordance with the terms of the 
 * license agreement you entered into with Nordstrom, Inc.
 */
package com.nord.shoerunner.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author utys
 */
public final class DAOConstants {
 
    public static final String JNDI_TEST_CLASS = "com.nordstrom.sf.retailengine.test.util.OfflineJNDIContainer";

    public static final int HIBERNATE_TIMEOUT_SECONDS = 10;

    public static final SessionFactory SESSION_FACTORY = buildSessionFactory();

    private DAOConstants() {
    }

    /**
     * Initialize the Hibernate subsystem.
     *
     * @return
     */
    private static SessionFactory buildSessionFactory() {
        try {
            checkForTestJNDIDataSource();
            // Create the SessionFactory from hibernate.cfg.xml
            Configuration c = new Configuration().configure();
            ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(c.getProperties()).build();
            return c.buildSessionFactory(sr);
        } catch (HibernateException ex) {
            throw ex;
        }
    }

    /**
     * In a test environment, we will deploy with the test jars, which will
     * include a class to setup a special JNDI data source used only in test.
     */
    private static void checkForTestJNDIDataSource() {

        try {
            Class.forName(JNDI_TEST_CLASS);
        } catch (ClassNotFoundException e) {
        }
    }

    public static Session getNewSession() {
        return SESSION_FACTORY.openSession();
    }
}
