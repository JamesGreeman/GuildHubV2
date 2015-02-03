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
@Table(name = "races")
public class Race {
    public enum    Faction{Horde, Alliance, Neutral;};
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int     race_id; 
    private String  name;
    @Column(columnDefinition = "enum('Horde','Alliance','Neutral')")
    @Enumerated(EnumType.STRING)
    private Faction faction;

    public int getRace_id() {
        return race_id;
    }

    public String getName() {
        return name;
    }
    
    public Faction getFaction() {
        return faction;
    }
    
    public static Race getRace(int race_id){
        Race race   =   null;
        List<Race> list;

        SessionFactory sessionFactory = HibernateMySQLDAO.getSessionFactory("guild_hub");
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        list = session.createCriteria(Race.class)
                .add(Restrictions.eq("race_id", race_id)).list();
        session.getTransaction().commit();
        session.close();
        if (!list.isEmpty()){
            race    =   list.get(0);
        }
        return race;
    }
    
     public static List<Race> getAllRaces(){
        List<Race> list;

        SessionFactory sessionFactory = HibernateMySQLDAO.getSessionFactory("guild_hub");
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        list = session.createCriteria(Race.class).list();
        session.getTransaction().commit();
        session.close();
        
        return list;
    }
}
