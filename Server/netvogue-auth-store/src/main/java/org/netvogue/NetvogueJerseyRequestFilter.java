package org.netvogue;

import org.netvogue.ecommerce.persistence.UserSessionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import com.sun.jersey.spi.container.ContainerResponseFilter;
import com.sun.jersey.spi.container.ResourceFilter;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

@Component
@Provider
public class NetvogueJerseyRequestFilter implements ResourceFilter, ContainerRequestFilter {

  @Autowired
  private UserSessionDao userSessionDao;

  public ContainerRequest filter(final ContainerRequest request) {
    String sessionId = request.getHeaderValue("sessionId");

    boolean isValidSession = userSessionDao.isSessionValid(sessionId);

    if (!isValidSession) {
      throw new WebApplicationException(Status.UNAUTHORIZED);
    }

    return request;
  }

  public ContainerRequestFilter getRequestFilter() {
    return this;
  }

  public ContainerResponseFilter getResponseFilter() {
    return null;
  }

}
