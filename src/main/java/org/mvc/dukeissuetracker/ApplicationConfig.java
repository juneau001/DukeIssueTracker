/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mvc.dukeissuetracker;

import java.util.HashMap;
import java.util.Map;
import javax.mvc.security.Csrf;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author Juneau
 */
@ApplicationPath("tracker")
public class ApplicationConfig extends Application {

    public Map<String, Object> getProperties() {
        final Map<String, Object> map = new HashMap<>();
        //map.put(Csrf.CSRF_PROTECTION, Csrf.CsrfOptions.EXPLICIT);
        return map;
    }

   
}
