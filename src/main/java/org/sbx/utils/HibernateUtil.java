package org.sbx.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

/**
 * Created by aloginov on 21.10.16.
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    private static final Logger logger = LogManager.getLogger(HibernateUtil.class);
    static {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
