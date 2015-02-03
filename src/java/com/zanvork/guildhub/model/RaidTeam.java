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
@Table(name = "raid_teams")
public class RaidTeam {
    public  enum    Region{EU, US;};
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int     raid_team_id;
    private String  raid_team_name;
    private int     team_owner_fk;
    @Column(columnDefinition = "enum('EU','US')")
    @Enumerated(EnumType.STRING)
    private Region  region;
    private int     guild_fk;

    public int getRaid_team_id() {
        return raid_team_id;
    }

    public void setRaid_team_id(int raid_team_id) {
        this.raid_team_id = raid_team_id;
    }

    public String getRaid_team_name() {
        return raid_team_name;
    }

    public void setRaid_team_name(String raid_team_name) {
        this.raid_team_name = raid_team_name;
    }

    public int getTeam_owner_fk() {
        return team_owner_fk;
    }

    public void setTeam_owner_fk(int team_owner_fk) {
        this.team_owner_fk = team_owner_fk;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public int getGuild_fk() {
        return guild_fk;
    }

    public void setGuild_fk(int guild_fk) {
        this.guild_fk = guild_fk;
    }
    
    public static List<RaidTeam> getAllRaidTeams(){
        List<RaidTeam> list;

        SessionFactory sessionFactory = HibernateMySQLDAO.getSessionFactory("guild_hub");
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        list = session.createCriteria(RaidTeam.class).list();
        session.getTransaction().commit();
        session.close();
        
        return list;
    }
    

    
}
