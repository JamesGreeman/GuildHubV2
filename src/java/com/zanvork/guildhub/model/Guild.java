/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanvork.guildhub.model;

import com.zanvork.guildhub.model.dao.HibernateMySQLDAO;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Jamie
 */
@Entity
@Table(name = "guilds")
public class Guild {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int     guild_id;
    private String  guild_name;
    private int     realm_fk;
    private int     guild_level;
    //@Column(columnDefinition = "enum('horde','alliance')")
    //@Enumerated(EnumType.STRING)
    private String  faction;
    private int     leader_fk;
    
    
    //Getters and setters
    public int getGuild_id() {
        return guild_id;
    }

    public void setGuild_id(int guild_id) {
        this.guild_id   =   guild_id;
    }
    
    public String getGuild_name() {
        return guild_name;
    }

    public void setGuild_name(String guild_name) {
        this.guild_name = guild_name;
    }

    public int getRealm_fk() {
        return realm_fk;
    }

    public void setRealm_fk(int realm_fk) {
        this.realm_fk = realm_fk;
    }

    public int getGuild_level() {
        return guild_level;
    }

    public void setGuild_level(int guild_level) {
        this.guild_level = guild_level;
    }

    public String getFaction() {
        return faction;
    }

    public void setFaction(String faction) {
        this.faction = faction;
    }

    public int getLeader_fk() {
        return leader_fk;
    }

    public void setLeader_fk(int leader_fk) {
        this.leader_fk = leader_fk;
    }

    public static List<Guild> getGuild(int guild_id){
        SessionFactory sessionFactory = HibernateMySQLDAO.getSessionFactory("guild");
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Guild> list    =   session.createCriteria(Guild.class).list();
        session.getTransaction().commit();
        session.close();
        return list;
    }
}
