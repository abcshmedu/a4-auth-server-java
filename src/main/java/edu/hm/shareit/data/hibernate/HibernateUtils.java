package edu.hm.shareit.data.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Singleton-Class to provide access to Hibernate.
 */
class HibernateUtils {

    private static SessionFactory sessionFactory;

    static {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    static void shutdown() {
        getSessionFactory().close();
    }
}
