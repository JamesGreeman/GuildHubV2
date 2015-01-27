/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanvork.guildhub.controller;
import com.zanvork.utils.RequestMap;
/**
 *
 * @author Jamie
 */
public class Request {
    RequestMap request;
    public Request(RequestMap request){
        this.request    =   request;
    }
    
    public String processRequest(){
        return "";
    }
}
