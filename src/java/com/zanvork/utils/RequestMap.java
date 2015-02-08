/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanvork.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Jamie
 */
public class RequestMap {
    
    private Map<String, String>   requestParams;
    
    public RequestMap(HttpServletRequest request){
        requestParams   =   mapParameters(request);
    }
    
    private Map<String, String> mapParameters(HttpServletRequest request){
        HashMap<String, String> mappedParams    =   new HashMap<>();
        Map<String, String[]>   getParams       =   request.getParameterMap();
        for (Entry<String, String[]> entry : getParams.entrySet()){
            mappedParams.put(entry.getKey(), entry.getValue()[0]);
        }
        return mappedParams;
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
            parameter    =   requestParams.get(paramName);
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
        String  parameter = "";
        if (requestParams.containsKey(param)){
            parameter =   requestParams.get(param);
            return  parameter;
        }
        return  parameter;            
    }
    
    
}
