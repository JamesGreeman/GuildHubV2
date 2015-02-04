/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanvork.guildhub.controller;
import com.zanvork.utils.RequestMap;
import java.util.Arrays;
import java.util.List;
/**
 *
 * @author Jamie
 */
public class Request {
    public static final String PARAM_REQUEST_TYPE       =   "requestType";
    public static final String PARAM_REQUEST_OBJECTS    =   "requestObjects";
    
    public static final String  REQUEST_TYPE_CREATE     =   "create";
    public static final String  REQUEST_TYPE_READ       =   "read";
    public static final String  REQUEST_TYPE_UPDATE     =   "update";
    public static final String  REQUEST_TYPE_DELETE     =   "delete";
    
    
    RequestMap      request;
    String          requestType;
    List<String>    requestObjects;
    
    public Request(RequestMap request){
        this.request    =   request;
        
        requestType     =   request.getParam(PARAM_REQUEST_TYPE);
        requestObjects  =   Arrays.asList(request.getParam(PARAM_REQUEST_OBJECTS).split(","));
    }
    
    public boolean  objectRequested(String object){
        return requestObjects.contains(object);
    }
    
    public String processRequest(){
        return "";
    }
}
