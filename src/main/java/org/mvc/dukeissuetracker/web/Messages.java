/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mvc.dukeissuetracker.web;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * CDI Bean utilized to display feedback messages to the user after each request.
 * 
 * @author Juneau
 */
@Named
@RequestScoped
public class Messages {
    
    private String message;
    
    private String error;

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getError(){
        return error;
    }
    
    public void setError(String error){
        this.error = error;
    }
}
