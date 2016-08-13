/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mvc.dukeissuetracker.exception;

import javax.inject.Inject;
import javax.mvc.Models;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Juneau
 */
@Provider
public class GenericExceptionMapper implements
        ExceptionMapper<Exception> {
 
  @Inject
  private Models models;
 
  @Override
  public Response toResponse(Exception exception) {
    models.put("message", exception.getMessage());
 
    return Response.status(Response.Status.BAD_REQUEST)
        .entity("/WEB-INF/views/error.xhtml")
        .build();
  }
}
