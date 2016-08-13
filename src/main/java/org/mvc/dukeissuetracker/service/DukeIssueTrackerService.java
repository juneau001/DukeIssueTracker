/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mvc.dukeissuetracker.service;

import java.math.BigDecimal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import org.mvc.dukeissuetracker.rest.DukeIssues;

/**
 *
 * @author Juneau
 */
@ApplicationScoped
public class DukeIssueTrackerService implements
        java.io.Serializable {

    private Client jaxRsClient;
    private List<DukeIssues> issueList;

    private String hostUri =
    "http://localhost:8080/DukeIssueTracker/tracker";

    public DukeIssueTrackerService() {

    }

    @PostConstruct
    public void init() {
        System.out.println("initializing app...");
        // Construct a JAX-RS Client
        setJaxRsClient(ClientBuilder.newClient());
        loadData();
    }

    private void loadData() {
        System.out.println("loading issues...");
        issueList = getJaxRsClient()
         .target(getHostUri() +
         "/org.mvc.dukeissuetracker.rest.dukeissues")
         .request("application/xml")
         .get(new GenericType<List<DukeIssues>>() {
             }
          );
        System.out.println("Issues after load:" +
                issueList.size());
    }
    
    /**
     * Returns a <code>DukeIssues</code> object matching the requested ID.
     * 
     * @param id
     * @return 
     */
    public DukeIssues findById(BigDecimal id){
        return (DukeIssues) getJaxRsClient()
         .target(getHostUri() +
         "/org.mvc.dukeissuetracker.rest.dukeissues/" + id)
         .request("application/xml")
         .get(new GenericType<DukeIssues>(){
             
         });
    }


    /**
     * @return the issueList
     */
    public List<DukeIssues> getIssueList() {
        // Load fresh data
        loadData();
        
        return issueList;
    }

    /**
     * @param issueList the issueList to set
     */
    public void setIssueList(
            List<DukeIssues> issueList) {
        this.issueList = issueList;
    }

    /**
     * @return the jaxRsClient
     */
    public Client getJaxRsClient() {
        return jaxRsClient;
    }

    /**
     * @param jaxRsClient the jaxRsClient to set
     */
    public void setJaxRsClient(Client jaxRsClient) {
        this.jaxRsClient = jaxRsClient;
    }

    /**
     * @return the hostUri
     */
    public String getHostUri() {
        return hostUri;
    }

    /**
     * @param hostUri the hostUri to set
     */
    public void setHostUri(String hostUri) {
        this.hostUri = hostUri;
    }
    
    

}
