/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mvc.dukeissuetracker.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.mvc.dukeissuetracker.rest.DukeStatus;

/**
 *
 * @author Juneau
 */
@Stateless
public class DukeStatusFacade extends AbstractFacade<DukeStatus> {

    @PersistenceContext(unitName = "org.mvc_DukeIssueTracker_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DukeStatusFacade() {
        super(DukeStatus.class);
    }
    
    /**
     * Returns a <code>DukeStatus</code> object, given a specified status.
     * @param status
     * @return 
     */
    public DukeStatus findByStatus(String status){
        return (DukeStatus) em.createQuery("select o from DukeStatus o " +
                "where o.status = :status")
                .setParameter("status", status)
                .getSingleResult();
    }
    
}
