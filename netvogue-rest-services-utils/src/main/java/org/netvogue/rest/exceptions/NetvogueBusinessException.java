package org.netvogue.rest.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class NetvogueBusinessException extends WebApplicationException {

  /**
   *
   */
  private static final long serialVersionUID = -4288550610150154911L;

  public NetvogueBusinessException(final String message, final int status, final Exception e) {
    super(e, Response.status(status).entity(message).build());
  }

  public NetvogueBusinessException(final String message, final Status status) {
    super(Response.status(status).entity(message).build());
  }

}
