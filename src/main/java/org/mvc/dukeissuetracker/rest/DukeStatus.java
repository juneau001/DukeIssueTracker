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
@Table(name = "DUKE_STATUS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DukeStatus.findAll", query = "SELECT d FROM DukeStatus d"),
    @NamedQuery(name = "DukeStatus.findById", query = "SELECT d FROM DukeStatus d WHERE d.id = :id"),
    @NamedQuery(name = "DukeStatus.findByStatus", query = "SELECT d FROM DukeStatus d WHERE d.status = :status")})
public class DukeStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Size(max = 255)
    @Column(name = "STATUS")
    
    private String status;
    @OneToMany(mappedBy = "status" )
    private Collection<DukeIssues> dukeIssuesCollection;

    public DukeStatus() {
    }

    public DukeStatus(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<DukeIssues> getDukeIssuesCollection() {
        return dukeIssuesCollection;
    }

    public void setDukeIssuesCollection(Collection<DukeIssues> dukeIssuesCollection) {
        this.dukeIssuesCollection = dukeIssuesCollection;
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
        if (!(object instanceof DukeStatus)) {
            return false;
        }
        DukeStatus other = (DukeStatus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.mvc.dukeissuetracker.rest.DukeStatus[ id=" + id + " ]";
    }
    
}
