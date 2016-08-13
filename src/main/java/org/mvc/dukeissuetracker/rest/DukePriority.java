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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Juneau
 */
@Entity
@Table(name = "DUKE_PRIORITY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DukePriority.findAll", query = "SELECT d FROM DukePriority d"),
    @NamedQuery(name = "DukePriority.findById", query = "SELECT d FROM DukePriority d WHERE d.id = :id"),
    @NamedQuery(name = "DukePriority.findByPriority", query = "SELECT d FROM DukePriority d WHERE d.priority = :priority")})
public class DukePriority implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Column(name = "PRIORITY")
  
    private Integer priority;
    @OneToMany(mappedBy = "priority")
    private Collection<DukeIssues> dukeIssuesCollection;

    public DukePriority() {
    }

    public DukePriority(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
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
        if (!(object instanceof DukePriority)) {
            return false;
        }
        DukePriority other = (DukePriority) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.mvc.dukeissuetracker.rest.DukePriority[ id=" + id + " ]";
    }
    
}
