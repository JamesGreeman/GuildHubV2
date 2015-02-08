/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanvork.guildhub.controller;
import com.google.gson.JsonObject;
import com.zanvork.guildhub.model.dao.HibernateMySQLDAO;
import com.zanvork.utils.RequestMap;
import java.util.Arrays;
import java.util.List;
/**
 *
 * @author Jamie
 */
public abstract class Request {
    public static final String PARAM_REQUEST_TYPE       =   "requestType";
    public static final String PARAM_REQUEST_OBJECTS    =   "requestObjects";
    
    public static final String  REQUEST_TYPE_CREATE     =   "create";
    public static final String  REQUEST_TYPE_READ       =   "read";
    public static final String  REQUEST_TYPE_UPDATE     =   "update";
    public static final String  REQUEST_TYPE_DELETE     =   "delete";
    
    
    protected   RequestMap      request;
    protected   String          requestType;
    protected   List<String>    requestObjects;
    
    public Request(RequestMap request){
        this.request    =   request;
        
        requestType     =   request.getParam(PARAM_REQUEST_TYPE);
        requestObjects  =   Arrays.asList(request.getParam(PARAM_REQUEST_OBJECTS).split(","));
    }
    
    public boolean  objectRequested(String object){
        return requestObjects.contains(object);
    }
    
    public String processRequest(){
        String response =   "";
        switch (requestType){
            case REQUEST_TYPE_CREATE:
                response    =   processCreateReuest();
                break;
            case REQUEST_TYPE_READ:
                response    =   processReadRequest();
                break;
            case REQUEST_TYPE_UPDATE:
                response    =   processUpdateRequest();
                break;
            case REQUEST_TYPE_DELETE:
                response    =   processDeleteRequest();
                break;
            default:
                response    =   this.defaultResposne("No request type specified");
                break;
        }
        HibernateMySQLDAO.closeSession();
        return response;
    }
    
    public String defaultResposne(String reason){
        JsonObject response =   new JsonObject();
        response.addProperty("response", "nodata");
        response.addProperty("reason", reason);
        return response.toString();
    }
    
    public abstract String processCreateReuest();
    public abstract String processReadRequest();
    public abstract String processUpdateRequest();
    public abstract String processDeleteRequest();
}
