/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanvork.guildhub.model;

import com.zanvork.guildhub.model.dao.HibernateMySQLDAO;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Jamie
 */
@Entity
@Table(name = "realms")
public class Realm {
    public  enum    Region{EU, US;};
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int     realm_id;
    private String  locale;
    private String  slug;
    private String  realm_name;
    @Column(columnDefinition = "enum('EU','US')")
    @Enumerated(EnumType.STRING)
    private Region  region;

    public int getRealm_id() {
        return realm_id;
    }

    public String getLocale() {
        return locale;
    }

    public String getSlug() {
        return slug;
    }

    public String getRealm_name() {
        return realm_name;
    }

    public Region getRegion() {
        return region;
    }
    
    public static Realm getRealm(int realm_id){
        Realm realm   =   null;
        List<Realm> list;

        SessionFactory sessionFactory = HibernateMySQLDAO.getSessionFactory("guild_hub");
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        list = session.createCriteria(Realm.class)
                .add(Restrictions.eq("realm_id", realm_id)).list();
        session.getTransaction().commit();
        session.close();
        if (!list.isEmpty()){
            realm    =   list.get(0);
        }
        return realm;
    }
}