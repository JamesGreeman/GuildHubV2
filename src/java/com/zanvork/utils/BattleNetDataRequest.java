/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanvork.utils;

import com.zanvork.guildhub.model.Realm;

/**
 *
 * @author Jamie
 */
public class BattleNetDataRequest extends HTTPDataRequest {
    public static final String EU_BASE_URL  =   "http://eu.battle.net/api/wow/";
    public static final String US_BASE_URL  =   "http://eu.battle.net/api/wow/";
    
    public static String loadCharacter(String name, String realmName, String region){
        String response =   "";
        
        Realm   realm;
        realm   =   Realm.getRealm(realmName, region);
        if (realm != null){
            StringBuilder   url =   new StringBuilder()
                    .append(BattleNetDataRequest.getBaseURL(region))
                    .append("character/")
                    .append(realm.getSlug())
                    .append("/")
                    .append(name);
  
            response    =   BattleNetDataRequest.makeRequest(url.toString());
        }
        
       
        return response;
    }
    
    private static String getBaseURL(String region){
        if (region.equals("EU")){
            return EU_BASE_URL;
        } else if (region.equals("US")){
            return US_BASE_URL;
        } else {
            return "";
        }
    }
    
}
