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
@Table(name = "classes")
public class Class {
    public enum    ArmourType{Cloth, Leather, Mail, Plate;};
    public enum    TierToken{Vanquisher, Conqueror, Protector;};
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int         class_id; 
    private String      class_name;
    @Column(columnDefinition = "enum('Cloth','Leather','Mail','Plate')")
    @Enumerated(EnumType.STRING)
    private ArmourType  armour_type; 
    @Column(columnDefinition = "enum('Vanquisher','Conqueror','Protector')")
    @Enumerated(EnumType.STRING)
    private TierToken   tier_token; 

    public int getClass_id() {
        return class_id;
    }

    public String getClass_name() {
        return class_name;
    }

    public ArmourType getArmour_type() {
        return armour_type;
    }

    public TierToken getTier_token() {
        return tier_token;
    }
    
    public static Class getClass(int class_id){
        Class  characterClass  =   null;
        List<Class>    list;

        SessionFactory sessionFactory = HibernateMySQLDAO.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        list = session.createCriteria(Class.class)
                .add(Restrictions.eq("class_id", class_id)).list();
        session.getTransaction().commit();
        
        if (!list.isEmpty()){
            characterClass  =   list.get(0);
        }
        return characterClass;
    }
}
