/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanvork.guildhub.controller;
import com.zanvork.guildhub.model.Guild;
import com.zanvork.guildhub.model.Character;
import com.zanvork.utils.RequestMap;
import java.util.List;
/**
 *
 * @author Jamie
 */
public class GuildRequest extends Request{
    
    public GuildRequest(RequestMap request){
        super(request);
    }
    @Override
    public String processRequest(){
        Guild guild;
        guild   =   Guild.getGuild(1);
        List<Character> members =   guild.getMembers();
        return "";
    }
}
