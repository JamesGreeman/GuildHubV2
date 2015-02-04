/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanvork.utils;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Jamie
 */
public class RequestMap {
    
    private Map<String, String[]>   requestParams;
    
    public RequestMap(HttpServletRequest request){
        requestParams   =   request.getParameterMap();
    }
    
    public boolean paramExists(String paramName){
        boolean exists  =   false;
        if (requestParams.containsKey(paramName)){
            exists  =   true;
        }
        return exists;
    }
    
    public String getParam(String paramName){
        String  parameter   =   "";
        if (requestParams.containsKey(paramName)){
            String[] params =   requestParams.get(paramName);
            parameter   =   params[0];
        } 
        return  parameter;
    }
    
    public boolean getParamAsBool(String paramName){
        boolean  parameter   =   false;
        if (this.getParam(paramName).equals("true")){
            parameter   =   true;
        } 
        return  parameter;
    }
    
    public int getParamAsInt(String paramName){
        int parameter   =   0;
        try{
            parameter   =   Integer.parseInt(this.getParam(paramName));
        } catch (NumberFormatException ex){
            parameter   =   0;
        }
        return  parameter;
    }
    
    public String getParam(String param, int type){
        if (requestParams.containsKey(param)){
            String[] params =   requestParams.get(param);
            return  params[0];
        }
        return  "";            
    }
    
    
}
