/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanvork.guildhub.controller;
import com.google.gson.Gson;
import com.zanvork.guildhub.model.Guild;
import com.zanvork.guildhub.model.Character;
import com.zanvork.guildhub.model.RaidTeam;
import com.zanvork.utils.RequestMap;
import java.util.List;
/**
 *
 * @author Jamie
 */
public class GuildRequest extends Request{
    public static final String PARAM_GUILD_ID           =   "guildID";

    public static final String  REQUEST_OBJECT_GUILD    =   "guild";
    public static final String  REQUEST_OBJECT_MEMBERS  =   "members";
    
    private Guild   guild;
    private Gson    gson;
    public GuildRequest(RequestMap request){
        super(request);
        if (request.paramExists(PARAM_GUILD_ID)){
            guild   =   Guild.getGuild(request.getParamAsInt(PARAM_GUILD_ID));           
        }

        gson    =   new Gson();
    }
    
    @Override
    public String processCreateReuest() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String processReadRequest() {
        String response =   "";
         if  (this.objectRequested(REQUEST_OBJECT_GUILD)){                      //request for a guild object
            if (this.objectRequested(REQUEST_OBJECT_MEMBERS)){                  //also want members
                guild.loadMembers();
            }
            response    =   gson.toJson(guild);

        } else if (this.objectRequested(REQUEST_OBJECT_MEMBERS)){               //request for the guild's members
            response    =   gson.toJson(guild.getMembers());
        } else {
            response    =   this.defaultResposne("no object to load specified");
        }
         return response;
    }

    @Override
    public String processUpdateRequest() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String processDeleteRequest() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}