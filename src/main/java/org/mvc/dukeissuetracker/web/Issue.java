/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mvc.dukeissuetracker.web;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;

/**
 *
 * @author Juneau
 */
public class Issue {
    
    @FormParam(value="id")
    private BigDecimal id;
   
    @FormParam(value="status")
    private Long status;

    @FormParam(value="priority")
    private Long priority;
  
    @NotNull
    @FormParam(value="requestorFirstName")
    private String requestorFirstName;
  
    @NotNull
    @FormParam(value="requestorLastName")
    private String requestorLastName;
    
    @NotNull
    @FormParam(value="requestorEmail")
    private String requestorEmail;
    
    @Size(max = 150)
    @FormParam(value="subject")
    private String subject;
    
    @Size(max = 2000)
    @FormParam(value="description")
    private String description;

    /**
     * @return the id
     */
    public BigDecimal getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(BigDecimal id) {
        this.id = id;
    }

    /**
     * @return the status
     */
    public Long getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Long status) {
        this.status = status;
    }

    /**
     * @return the priority
     */
    public Long getPriority() {
        return priority;
    }

    /**
     * @param priority the priority to set
     */
    public void setPriority(Long priority) {
        this.priority = priority;
    }

    /**
     * @return the requestorFirstName
     */
    public String getRequestorFirstName() {
        return requestorFirstName;
    }

    /**
     * @param requestorFirstName the requestorFirstName to set
     */
    public void setRequestorFirstName(
            String requestorFirstName) {
        this.requestorFirstName =
                requestorFirstName;
    }

    /**
     * @return the requestorLastName
     */
    public String getRequestorLastName() {
        return requestorLastName;
    }

    /**
     * @param requestorLastName the requestorLastName to set
     */
    public void setRequestorLastName(
            String requestorLastName) {
        this.requestorLastName =
                requestorLastName;
    }

    /**
     * @return the requestorEmail
     */
    public String getRequestorEmail() {
        return requestorEmail;
    }

    /**
     * @param requestorEmail the requestorEmail to set
     */
    public void setRequestorEmail(
            String requestorEmail) {
        this.requestorEmail =
                requestorEmail;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(
            String description) {
        this.description = description;
    }
    

    
}
