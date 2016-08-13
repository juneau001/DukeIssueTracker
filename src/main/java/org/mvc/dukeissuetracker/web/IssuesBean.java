/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mvc.dukeissuetracker.web;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.mvc.dukeissuetracker.rest.DukeIssues;

/**
 *
 * @author Juneau
 */
@Named
@RequestScoped
public class IssuesBean {

    private Issue issue;
    
    private String message;

    /**
     * @return the issueList
     */
    public List<DukeIssues> getIssueList() {
        return issueList;
    }

    /**
     * @param issueList the issueList to set
     */
    public void setIssueList(
            List<DukeIssues> issueList) {
        this.issueList = issueList;
    }
    
    private List<DukeIssues> issueList;

    /**
     * @return the issue
     */
    public Issue getIssue() {
        if(issue == null){
            issue = new Issue();
        }
        return issue;
    }

    /**
     * @param issue the issue to set
     */
    public void setIssue(Issue issue) {
        this.issue = issue;
    }
    
}
