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
    
    public String getParam(String param){
        if (requestParams.containsKey(param)){
            String[] params =   requestParams.get(param);
            return  params[0];
        } 
        return  "";
    }
    
    public String getParam(String param, int offset){
        if (requestParams.containsKey(param)){
            String[] params =   requestParams.get(param);
            if (params.length > offset){
                return params[offset];
            }
        }
        return  "";            
    }
    
    
}
