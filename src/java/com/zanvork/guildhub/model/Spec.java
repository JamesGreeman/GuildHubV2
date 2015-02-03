/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanvork.guildhub.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Jamie
 */
@Entity
@Table(name = "specs")
public class Spec {
    public  enum    Role{Tank,Healer,Melee,Ranged;};
    public  enum    MainStat{Agi,Str,Int;};
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int         spec_id;
    private String      spec_name;
    private int         class_fk;
    @Column(columnDefinition = "enum('Tank','Healer','Melee','Ranged')")
    @Enumerated(EnumType.STRING)
    private Role        role;
    @Column(columnDefinition = "enum('Agi','Str','Int')")
    @Enumerated(EnumType.STRING)
    private MainStat    main_stat;

    public int getSpec_id() {
        return spec_id;
    }

    public String getSpec_name() {
        return spec_name;
    }

    public int getClass_fk() {
        return class_fk;
    }

    public Role getRole() {
        return role;
    }

    public MainStat getMain_stat() {
        return main_stat;
    }
    
    
}
