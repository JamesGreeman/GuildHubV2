/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanvork.guildhub.model;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.zanvork.guildhub.model.dao.HibernateMySQLDAO;
import com.zanvork.utils.BattleNetDataRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
@Table(name = "characters")
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int     character_id; 
    private String  character_name;
    private int     realm_fk;
    private int     race_fk;    
    @Column(columnDefinition = "enum('Horde','Alliance','Neutral')")
    @Enumerated(EnumType.STRING)
    private Faction faction;
    private int     class_fk;
    private int     spec_fk;
    private int     offspec_fk; 
    private int     level;
    private int     guild_fk;
    private String  thumbnail_url;
    private int     user_fk;
    
    @ManyToOne()
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name="realm_fk", nullable=true, insertable=false, updatable=false)
    private Realm   characterRealm;
    @ManyToOne()
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name="race_fk", nullable=true, insertable=false, updatable=false)
    private Race    characterRace;
    @ManyToOne()
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name="class_fk", nullable=true, insertable=false, updatable=false)
    private Class   characterClass;
    @ManyToOne()
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name="spec_fk", nullable=true, insertable=false, updatable=false)
    private Spec    characterMainSpec;
   @ManyToOne()
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name="offspec_fk", nullable=true, insertable=false, updatable=false)
    private Spec    characterOffSpec;
    
    
    public Character(Map<String, String> characterData){
        if (characterData.containsKey("name") && characterData.containsKey("realm") && characterData.containsKey("region")){
            this.character_name     =   characterData.get("name");
            String  realmName       =   characterData.get("realm");
            String  region          =   characterData.get("region");
            this.characterRealm     =   Realm.getRealm(realmName, region);
            
            String  fields          =   "";
            if (characterData.containsKey("fields"      )){
                fields  =   characterData.get("fields");
            }
            
            BattleNetDataRequest    bnetRequest =   new BattleNetDataRequest();
            String bnetResponse                 =   bnetRequest.loadCharacter(character_name, characterRealm, fields);
            
            JsonParser  parser  =   new JsonParser();
            JsonObject  bnetJson    =   parser.parse(bnetResponse).getAsJsonObject();
            
            this.character_name =   bnetJson.get("name").getAsString();
            this.realm_fk       =   characterRealm.getRealm_id();
            this.race_fk        =   bnetJson.get("race").getAsInt();

            this.characterRace  =   Race.getRace(race_fk);
            this.faction        =   characterRace.getFaction();
            
            this.class_fk       =   bnetJson.get("class").getAsInt();
            this.characterClass =   Class.getClass(class_fk);
            
            //spec stuff
            this.spec_fk        =   -1;
            this.offspec_fk     =   -1;
            if (bnetJson.has("talents")){
                JsonArray   jsonTalents     =   bnetJson.getAsJsonArray("talents");
                JsonObject  jsonMainSpec    =   jsonTalents.get(0).getAsJsonObject();
                String      mainSpecName    =   "";
                JsonObject  jsonOffSpec     =   null;
                String      offSpecName     =   "";
                if (jsonTalents.size() > 1){
                    jsonOffSpec =   jsonTalents.get(1).getAsJsonObject();
                }
                
                if (jsonMainSpec.has("spec") && jsonMainSpec.getAsJsonObject("spec").has("name")){
                    mainSpecName            =   jsonMainSpec.getAsJsonObject("spec").get("name").getAsString();
                    this.characterMainSpec  =   Spec.getSpec(class_fk, mainSpecName);
                    this.spec_fk            =   characterMainSpec.getSpec_id();
                }
                if (jsonOffSpec != null && jsonOffSpec.has("spec") && jsonOffSpec.getAsJsonObject("spec").has("name")){
                    offSpecName             =   jsonOffSpec.getAsJsonObject("spec").get("name").getAsString();
                    this.characterOffSpec   =   Spec.getSpec(class_fk, offSpecName);
                    this.offspec_fk         =   characterOffSpec.getSpec_id();
                }
            }
            //TODO: set guild
            this.level          =   bnetJson.get("level").getAsInt();
            this.thumbnail_url  =   bnetJson.get("thumbnail").getAsString();
            this.user_fk        =   -1;
        }
    }

    public int getCharacter_id() {
        return character_id;
    }

    public void setCharacter_id(int character_id) {
        this.character_id = character_id;
    }

    public String getCharacter_name() {
        return character_name;
    }

    public void setCharacter_name(String character_name) {
        this.character_name = character_name;
    }

    public int getRealm_fk() {
        return realm_fk;
    }

    public void setRealm_fk(int realm_fk) {
        this.realm_fk = realm_fk;
    }

    public int getRace_fk() {
        return race_fk;
    }

    public void setRace_fk(int race_fk) {
        this.race_fk = race_fk;
    }

    public Faction getFaction() {
        return faction;
    }

    public void setFaction(Faction faction) {
        this.faction = faction;
    }

    public int getClass_fk() {
        return class_fk;
    }

    public void setClass_fk(int class_fk) {
        this.class_fk = class_fk;
    }

    public int getSpec_fk() {
        return spec_fk;
    }

    public void setSpec_fk(int spec_fk) {
        this.spec_fk = spec_fk;
    }

    public int getOffspec_fk() {
        return offspec_fk;
    }

    public void setOffspec_fk(int offspec_fk) {
        this.offspec_fk = offspec_fk;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getGuild_fk() {
        return guild_fk;
    }

    public void setGuild_fk(int guild_fk) {
        this.guild_fk = guild_fk;
    }

    public String getThumbnail_url() {
        return thumbnail_url;
    }

    public void setThumbnail_url(String thumbnail_url) {
        this.thumbnail_url = thumbnail_url;
    }

    public int getUser_fk() {
        return user_fk;
    }

    public void setUser_fk(int user_fk) {
        this.user_fk = user_fk;
    }
    
    public static List<Character> getAllCharacters(){
        List<Character> list;

        SessionFactory sessionFactory = HibernateMySQLDAO.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        list = session.createCriteria(Character.class).list();
        session.getTransaction().commit();
        
        return list;
    }
    
    public static List<Character> getAllCharacters(Guild guild){
        List<Character> list;
        int guild_id    =   guild.getGuild_id();
        SessionFactory sessionFactory = HibernateMySQLDAO.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        list = session.createCriteria(Character.class)
                .add(Restrictions.eq("guild_fk", guild_id)).list();
        session.getTransaction().commit();
        
        return list;
    }
}
