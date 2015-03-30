package com.nord.shoerunner.dao;


import static com.nord.shoerunner.dao.DAOConstants.SESSION_FACTORY;
import org.hibernate.Session;

/**
 * BaseDAO class allows us to separate the operations to generate and destroy a
 * Hibernate session.
 *
 * An implementing DAO class can override these methods to customize how and
 * when a session is obtained and destroyed.
 *
 * @author z181
 * @see TransactionSequenceDAOImpl
 */
public interface BaseDAO {

    /**
     * Obtain a default Hibernate Session from the factory
     *
     * @return
     */
    default Session getSession() {
        try {
            Session session = SESSION_FACTORY.openSession();
            session.beginTransaction().setTimeout(60);
            return session;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Commit our work and close the session
     *
     * @param session
     */
    default void commitAndClose(Session session) {
        if (session != null && session.isOpen()) {
            session.getTransaction().commit();
            // thread-managed Hib sessions may have closed it for us
            if (session.isOpen()) {
                session.close();
            }
        }
    }

    /**
     * Force Hibernate to flush its entity cache as well, between the commit and
     * the close operations
     *
     * @param session
     */
    default void flushCommitAndClose(Session session) {
        //LOGGER.debug("Flush, Commit, and Close transaction");
        if (session != null && session.isOpen()) {
            session.flush();
            session.getTransaction().commit();
            // thread-managed Hib sessions may have closed it for us
            if (session.isOpen()) {
                session.close();
            }
        }
    }

    /**
     * Rollback the Hibernate transaction, releasing any locks, and close the
     * session
     *
     * @param session
     */
    default void rollbackAndClose(Session session) {
        if (session != null && session.isOpen()) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            session.close();
        }
    }
}
