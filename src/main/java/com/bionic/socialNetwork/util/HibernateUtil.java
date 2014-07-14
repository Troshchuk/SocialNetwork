package com.bionic.socialNetwork.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Hibernate util. Create session factory
 *
 * @author Dmytro Troshchuk
 * @version 1.00  14.07.14.
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;

    static {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
