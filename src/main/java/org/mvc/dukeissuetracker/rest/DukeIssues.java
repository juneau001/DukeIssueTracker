/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mvc.dukeissuetracker.rest;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Juneau
 */
@Entity
@Table(name = "DUKE_ISSUES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DukeIssues.findAll", query = "SELECT d FROM DukeIssues d"),
    @NamedQuery(name = "DukeIssues.findById", query = "SELECT d FROM DukeIssues d WHERE d.id = :id"),
    @NamedQuery(name = "DukeIssues.findByDescription", query = "SELECT d FROM DukeIssues d WHERE d.description = :description"),
    @NamedQuery(name = "DukeIssues.findBySubject", query = "SELECT d FROM DukeIssues d WHERE d.subject = :subject")})
public class DukeIssues implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 255)
    @Column(name = "DESCRIPTION")
   
    private String description;
    @Size(max = 255)
    @Column(name = "SUBJECT")
   
    private String subject;
    @ManyToMany(mappedBy = "dukeIssuesCollection")
    private Collection<DukeUser> dukeUserCollection;
    @JoinTable(name = "DUKE_ASSIGNEE_DUKE_ISSUES", joinColumns = {
        @JoinColumn(name = "DUKEISSUES_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "DUKEASSIGNEE_ID", referencedColumnName = "ID")})
    @ManyToMany
    private Collection<DukeAssignee> dukeAssigneeCollection;
    @JoinColumn(name = "ASSIGNEE", referencedColumnName = "ID")
    @ManyToOne
    private DukeAssignee assignee;
    @JoinColumn(name = "PRIORITY", referencedColumnName = "ID")
    @ManyToOne
    private DukePriority priority;
    @JoinColumn(name = "STATUS", referencedColumnName = "ID")
    @ManyToOne
    private DukeStatus status;
    @JoinColumn(name = "REQUESTOR", referencedColumnName = "ID")
    @ManyToOne(cascade={CascadeType.PERSIST})
    private DukeUser requestor;

    public DukeIssues() {
    }

    public DukeIssues(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @XmlTransient
    public Collection<DukeUser> getDukeUserCollection() {
        return dukeUserCollection;
    }

    public void setDukeUserCollection(Collection<DukeUser> dukeUserCollection) {
        this.dukeUserCollection = dukeUserCollection;
    }

    @XmlTransient
    public Collection<DukeAssignee> getDukeAssigneeCollection() {
        return dukeAssigneeCollection;
    }

    public void setDukeAssigneeCollection(Collection<DukeAssignee> dukeAssigneeCollection) {
        this.dukeAssigneeCollection = dukeAssigneeCollection;
    }

    public DukeAssignee getAssignee() {
        return assignee;
    }

    public void setAssignee(DukeAssignee assignee) {
        this.assignee = assignee;
    }

    public DukePriority getPriority() {
        return priority;
    }

    public void setPriority(DukePriority priority) {
        this.priority = priority;
    }

    public DukeStatus getStatus() {
        return status;
    }

    public void setStatus(DukeStatus status) {
        this.status = status;
    }

    public DukeUser getRequestor() {
        return requestor;
    }

    public void setRequestor(DukeUser requestor) {
        this.requestor = requestor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DukeIssues)) {
            return false;
        }
        DukeIssues other = (DukeIssues) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.mvc.dukeissuetracker.rest.DukeIssues[ id=" + id + " ]";
    }
    
}
