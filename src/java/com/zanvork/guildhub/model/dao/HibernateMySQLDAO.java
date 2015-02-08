/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanvork.guildhub.model.dao;

import java.util.HashMap;
import java.util.Map;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author Jamie
 */
public class HibernateMySQLDAO {
    public static final String DEFAULT_SESSION_NAME = "guild_hub";
    private static final Map<String, SessionFactory> sessionFactory = new HashMap();

    public static SessionFactory getSessionFactory() {
        return getSessionFactory(DEFAULT_SESSION_NAME);
    }
    
    public static SessionFactory getSessionFactory(String dbconnect) {

        if (sessionFactory != null && sessionFactory.containsKey(dbconnect)) {
            return sessionFactory.get(dbconnect);
        }

        try {
            synchronized (sessionFactory) {
                Configuration configuration     =   new Configuration().configure();
                       
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
                SessionFactory sf               = configuration.buildSessionFactory(serviceRegistry);
                sessionFactory.put(dbconnect, sf);
            }
        } catch (HibernateException ex) {
            throw new ExceptionInInitializerError(ex);
        }
        return sessionFactory.get(dbconnect);
    }
    
    public static void closeSession(){
        closeSession(DEFAULT_SESSION_NAME);
    }
    
    public static void closeSession(String dbconnect){
        if (sessionFactory != null && sessionFactory.containsKey(dbconnect)) {
            SessionFactory sf   =   sessionFactory.get(dbconnect);
            sf.close();
            sessionFactory.remove(dbconnect);
        
            try {

            }  catch (HibernateException ex) {
               throw new ExceptionInInitializerError(ex);
           }
         }
    }
}
