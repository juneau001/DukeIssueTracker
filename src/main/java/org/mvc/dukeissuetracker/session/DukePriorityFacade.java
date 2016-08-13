/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mvc.dukeissuetracker.session;

import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.mvc.dukeissuetracker.rest.DukePriority;

/**
 *
 * @author Juneau
 */
@Stateless
public class DukePriorityFacade extends AbstractFacade<DukePriority> {

    @PersistenceContext(unitName = "org.mvc_DukeIssueTracker_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DukePriorityFacade() {
        super(DukePriority.class);
    }
    
    /**
     * Returns a <code>DukePriority</code> object, given a specified priority.
     * @param priority
     * @return 
     */
    public DukePriority findByPriority(int priority){
        return (DukePriority) em.createQuery("select o from DukePriority o " +
                "where o.priority = :priority")
                .setParameter("priority", priority)
                .getSingleResult();
    }
    
    /**
     * Returns a <code>DukePriority</code> object, given a specified priority.
     * @param priority
     * @return 
     */
    public DukePriority findById(Long id){
        return (DukePriority) em.createQuery("select o from DukePriority o " +
                "where o.id = :id")
                .setParameter("id", id)
                .getSingleResult();
    }
    
   
    
}
