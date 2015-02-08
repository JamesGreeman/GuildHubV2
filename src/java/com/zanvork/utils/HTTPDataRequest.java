/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zanvork.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author Jamie
 */
public class HTTPDataRequest {
    public String makeRequest(String urlStr){
        String response         =   "";
        URL url                 =   null;
        HttpURLConnection con   =   null;
        try{
            url = new URL(urlStr);
        } catch (MalformedURLException ex){
            
        }
        if (url != null){
            try{
                con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder   sb  =   new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                        sb.append(inputLine);
                }
                in.close();
                response    =   sb.toString();
                
            } catch (IOException ex){
                
            }
        }
        
        return response;
        
    }

}
