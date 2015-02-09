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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
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
    private int             guild_id;
    private String          guild_name;
    private int             realm_fk;
    private int             guild_level;
    @Column(columnDefinition = "enum('Horde','Alliance','Neutral')")
    @Enumerated(EnumType.STRING)
    private Faction         faction;
    private int             leader_fk;
    @ManyToOne()
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name="realm_fk", nullable=true, insertable=false, updatable=false)
    private Realm           guildRealm;
    @Transient
    private List<Character> members;

    
    public Guild(){
        
    }
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

    public Faction getFaction() {
        return faction;
    }

    public void setFaction(Faction faction) {
        this.faction = faction;
    }
    
    public int getLeader_fk() {
        return leader_fk;
    }

    public void setLeader_fk(int leader_fk) {
        this.leader_fk = leader_fk;
    }
    
    public List<Character>  getMembers(){
        if (members == null || members.isEmpty()){
            this.loadMembers();
        }
        return members;
    }
    
    public List<Character>  getMembers(boolean forceReload){
        if (forceReload || members == null || members.isEmpty()){
            this.loadMembers();
        }
        return members;
    }
    
    public String toJSON(){
        String json =   "";
        return json;
    }
    
    public void loadMembers(){
        members    =   Character.getAllCharacters(this);
    }

    public static Guild getGuild(int guild_id){
        Guild   guild       =   null;
        List<Guild> list;

        SessionFactory sessionFactory = HibernateMySQLDAO.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        list = session.createCriteria(Guild.class)
                .add(Restrictions.eq("guild_id", guild_id)).list();
        session.getTransaction().commit();
        
        if (!list.isEmpty()){
            guild =   list.get(0);
        }
        return guild;
    }
    
    public static List<Guild> getAllGuilds(){
        List<Guild> list;

        SessionFactory sessionFactory = HibernateMySQLDAO.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        list = session.createCriteria(Guild.class).list();
        session.getTransaction().commit();
        
        return list;
    }
    
    
}
