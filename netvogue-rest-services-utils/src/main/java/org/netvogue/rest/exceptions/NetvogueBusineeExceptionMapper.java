package org.netvogue.rest.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NetvogueBusineeExceptionMapper implements ExceptionMapper<NetvogueBusinessException>  {

  public Response toResponse(final NetvogueBusinessException ex) {
    // TODO Auto-generated method stub
    return ex.getResponse();
  }

}
