/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mvc.dukeissuetracker.web;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.mvc.Models;
import javax.mvc.annotation.Controller;
import javax.mvc.binding.BindingResult;
import javax.validation.Valid;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;
import javax.ws.rs.BeanParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.mvc.dukeissuetracker.rest.DukeIssues;
import org.mvc.dukeissuetracker.rest.DukePriority;
import org.mvc.dukeissuetracker.rest.DukeStatus;
import org.mvc.dukeissuetracker.rest.DukeUser;
import org.mvc.dukeissuetracker.service.DukeIssueTrackerService;
import org.mvc.dukeissuetracker.session.DukeIssuesFacade;
import org.mvc.dukeissuetracker.session.DukePriorityFacade;
import org.mvc.dukeissuetracker.session.DukeStatusFacade;
import org.mvc.dukeissuetracker.session.DukeUserFacade;

/**
 *
 * @author Juneau
 */
@Controller
@Path("/issues")
public class IssuesController {

    @Inject
    private DukeIssueTrackerService dukeIssueTrackerService;

    @Inject
    private IssuesBean issuesBean;

    @Inject
    private Models models;

    @Inject
    private BindingResult bindingResult;

    @Inject
    private Messages messages;

    @EJB
    private DukeIssuesFacade dukeIssuesFacade;

    @EJB
    private DukeUserFacade dukeUserFacade;

    @EJB
    private DukePriorityFacade dukePriorityFacade;

    @EJB
    private DukeStatusFacade dukeStatusFacade;

    @GET
    public String displayIssues() {
        // To make available via Models, use the following:
        //models.put("dukeIssues", dukeIssueTrackerService.getIssueList());

        // To make available via CDI, use the IssuesBean:
        issuesBean.setIssueList(dukeIssueTrackerService.getIssueList());
        return "issues.xhtml";
    }

    /**
     * Selects an issue from the tracker to manage
     *
     * @param id
     * @return
     */
    @POST
    @Path("/manageIssue")
    public String manageIssue(@FormParam("id") BigDecimal id) {
        Issue issueToManage = issuesBean.getIssue();
        System.out.println("id: " + id);
        // Obtain the requested object
        DukePriority priority = null;
        DukeStatus status = null;
        DukeIssues issue = dukeIssueTrackerService.findById(id);
        
        try {
            priority = dukePriorityFacade.find(issue.getPriority().getId());
            status = dukeStatusFacade.find(issue.getStatus().getId());
            System.out.println("Status: " + status);
        } catch (Exception e) {

        }

        issueToManage.setDescription(issue.getDescription());
        issueToManage.setId(id);
        issueToManage.setPriority(priority.getId());
        issueToManage.setRequestorEmail(issue.getRequestor().getEmail());
        issueToManage.setRequestorFirstName(issue.getRequestor().getFirstName());
        issueToManage.setRequestorLastName(issue.getRequestor().getLastName());
        issueToManage.setStatus(status.getId());
        issueToManage.setSubject(issue.getSubject());
        
        messages.setMessage("Managing Issue:" + issueToManage.getId());
        issuesBean.setIssue(issueToManage);
        return "manage.xhtml";
    }
    
    /**
     * Updates a selected issue.
     * @param form
     * @return 
     */
    @POST
    @Path("/update")
    @ValidateOnExecution(type = ExecutableType.NONE)
    public String updateIssue(@BeanParam @Valid Issue form) {
        // Perform validation here
        System.out.println("Updating id: " + form.getId());
        // Obtain the requested object
        DukePriority priority = null;
        DukeStatus status = null;
        DukeIssues issue = dukeIssueTrackerService.findById(form.getId());
        System.out.println("Issue" + issue.getDescription());
        try {
            priority = dukePriorityFacade.find(form.getPriority());
            System.out.println("Finding Status: " + form.getStatus());
            status = dukeStatusFacade.find(form.getStatus());
            System.out.println("status loaded: " + status);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        
        issue.setDescription(form.getDescription());
        issue.setPriority(priority);
        issue.setStatus(status);
        issue.setSubject(form.getSubject());
        dukeIssuesFacade.edit(issue);
        
        messages.setMessage("Update Successful");
        return displayIssues();

    }

    @POST
    @Path("/create")
    @ValidateOnExecution(type = ExecutableType.NONE)
    public Response createItem(@BeanParam @Valid Issue form) {
        System.out.println("Creating item...");
        DukePriority priority = null;
        DukeStatus status = null;

        if (bindingResult.isFailed()) {

            bindingResult.getAllViolations().stream()
                    .map(violation -> violation.getMessage())
                    .forEach(message -> messages.setMessage(message));

            // Initialize issue list
            dukeIssueTrackerService.setIssueList(null);
            issuesBean.setIssueList(
                    dukeIssueTrackerService.getIssueList());

            return Response.status(javax.ws.rs.core.Response.Status.BAD_REQUEST).entity("issues.xhtml").build();
        }
        try {
            // Find the DukePriority object that matches the selected priority
            priority = dukePriorityFacade.find(form.getPriority());
            status = dukeStatusFacade.findByStatus("OPEN");
            System.out.println("Status: " + status.getStatus());
        } catch (Exception e) {

        }

        // Create new issue
        // Obtain issue list to count records for ID population
        DukeIssues entity = new DukeIssues();
        entity.setId(new Long(
                dukeIssueTrackerService.getIssueList().size() + 1));

        entity.setDescription(form.getDescription());
        entity.setSubject(form.getSubject());
        DukeUser user = new DukeUser();
        user.setId(Long.valueOf(
                dukeUserFacade.count() + 1));
        user.setEmail(form.getRequestorEmail());
        user.setFirstName(form.getRequestorFirstName());
        user.setLastName(form.getRequestorLastName());
        entity.setRequestor(user);

        entity.setPriority(priority);
       
        entity.setStatus(status);
        // Create record
        dukeIssuesFacade.create(entity);
        // Initialize issue list
        dukeIssueTrackerService.setIssueList(null);
        issuesBean.setIssueList(
                dukeIssueTrackerService.getIssueList());

        return Response.status(javax.ws.rs.core.Response.Status.OK).entity("issues.xhtml").build();

    }

    @POST
    @Path("/delete")
    @Controller
    public String delete(@FormParam("id") Long id) {
        DukeIssues selectedIssue = null;
        List<DukeIssues> issueList = dukeIssueTrackerService.getIssueList();
        for (DukeIssues issue : issueList) {
            if (issue.getId().compareTo(id) == 0) {
                selectedIssue = issue;
            }
        }
        if (selectedIssue != null) {
            dukeIssuesFacade.remove(selectedIssue);
        }
        
        messages.setMessage("Successfully Deleted");
        return displayIssues();
    }

    @POST
    @Path("/date/{year}/{month}")
    public String pathParamDate(@PathParam("year") int year,
            @PathParam("month") int month) {
        models.put("specifiedDate", month + "/" + year);
        return "showDate.xhtml";
    }
}
