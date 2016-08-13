/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mvc.dukeissuetracker.rest;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "DUKE_ASSIGNEE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DukeAssignee.findAll", query = "SELECT d FROM DukeAssignee d"),
    @NamedQuery(name = "DukeAssignee.findById", query = "SELECT d FROM DukeAssignee d WHERE d.id = :id"),
    @NamedQuery(name = "DukeAssignee.findByEmail", query = "SELECT d FROM DukeAssignee d WHERE d.email = :email"),
    @NamedQuery(name = "DukeAssignee.findByFirstName", query = "SELECT d FROM DukeAssignee d WHERE d.firstName = :firstName"),
    @NamedQuery(name = "DukeAssignee.findByLastName", query = "SELECT d FROM DukeAssignee d WHERE d.lastName = :lastName")})
public class DukeAssignee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 255)
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Size(max = 255)
    @Column(name = "LAST_NAME")
    private String lastName;
    @ManyToMany(mappedBy = "dukeAssigneeCollection")
    private Collection<DukeIssues> dukeIssuesCollection;
    @OneToMany(mappedBy = "assignee")
    private Collection<DukeIssues> dukeIssuesCollection1;

    public DukeAssignee() {
    }

    public DukeAssignee(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @XmlTransient
    public Collection<DukeIssues> getDukeIssuesCollection() {
        return dukeIssuesCollection;
    }

    public void setDukeIssuesCollection(Collection<DukeIssues> dukeIssuesCollection) {
        this.dukeIssuesCollection = dukeIssuesCollection;
    }

    @XmlTransient
    public Collection<DukeIssues> getDukeIssuesCollection1() {
        return dukeIssuesCollection1;
    }

    public void setDukeIssuesCollection1(Collection<DukeIssues> dukeIssuesCollection1) {
        this.dukeIssuesCollection1 = dukeIssuesCollection1;
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
        if (!(object instanceof DukeAssignee)) {
            return false;
        }
        DukeAssignee other = (DukeAssignee) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.mvc.dukeissuetracker.rest.DukeAssignee[ id=" + id + " ]";
    }
    
}
