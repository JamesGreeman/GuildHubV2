/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanvork.guildhub.controller;
import com.google.gson.Gson;
import com.zanvork.guildhub.model.Guild;
import com.zanvork.guildhub.model.Character;
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
    public String processRequest(){
        String json =   "";
        
        if (this.requestType.equals(REQUEST_TYPE_READ)){                         //GetRequest
            
            if  (this.objectRequested(REQUEST_OBJECT_GUILD)){                   //request for a guild object
               
                if (this.objectRequested(REQUEST_OBJECT_MEMBERS)){              //also want members
                    guild.loadMembers();
                }
                json    =   gson.toJson(guild);
    
            } else if (this.objectRequested(REQUEST_OBJECT_MEMBERS)){           //request for the guild's members
                json    =   gson.toJson(guild.getMembers());
            }
        }
        return json;
    }
    
}