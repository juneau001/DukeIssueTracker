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
@Table(name = "DUKE_USER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DukeUser.findAll", query = "SELECT d FROM DukeUser d"),
    @NamedQuery(name = "DukeUser.findById", query = "SELECT d FROM DukeUser d WHERE d.id = :id"),
    @NamedQuery(name = "DukeUser.findByEmail", query = "SELECT d FROM DukeUser d WHERE d.email = :email"),
    @NamedQuery(name = "DukeUser.findByFirstName", query = "SELECT d FROM DukeUser d WHERE d.firstName = :firstName"),
    @NamedQuery(name = "DukeUser.findByLastName", query = "SELECT d FROM DukeUser d WHERE d.lastName = :lastName")})
public class DukeUser implements Serializable {

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
    @JoinTable(name = "DUKE_USER_DUKE_ISSUES", joinColumns = {
        @JoinColumn(name = "DUKEUSER_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "DUKEISSUES_ID", referencedColumnName = "ID")})
    @ManyToMany
    private Collection<DukeIssues> dukeIssuesCollection;
    @OneToMany(mappedBy = "requestor", cascade={CascadeType.PERSIST})
    private Collection<DukeIssues> dukeIssuesCollection1;

    public DukeUser() {
    }

    public DukeUser(Long id) {
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
        if (!(object instanceof DukeUser)) {
            return false;
        }
        DukeUser other = (DukeUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.mvc.dukeissuetracker.rest.DukeUser[ id=" + id + " ]";
    }
    
}
