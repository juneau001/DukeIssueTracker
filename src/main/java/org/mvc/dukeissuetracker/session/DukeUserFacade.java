/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mvc.dukeissuetracker.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.mvc.dukeissuetracker.rest.DukeUser;

/**
 *
 * @author Juneau
 */
@Stateless
public class DukeUserFacade extends AbstractFacade<DukeUser> {

    @PersistenceContext(unitName = "org.mvc_DukeIssueTracker_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DukeUserFacade() {
        super(DukeUser.class);
    }
    
}
