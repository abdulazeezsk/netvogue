package org.netvogue.server.webmvc.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class NetvogueSessionAuthenticationStrategy implements SessionAuthenticationStrategy {

  public static String PRINCIPAL_KEY = "principal";
  public static String PRICIPLE_ROLES_KEY = "principal_roles";

  public void onAuthentication(final Authentication authentication, final HttpServletRequest request, final HttpServletResponse response)
      throws SessionAuthenticationException {

    HttpSession session = request.getSession();
    session.setAttribute(PRINCIPAL_KEY, authentication.getPrincipal());
    session.setAttribute(PRICIPLE_ROLES_KEY, authentication.getAuthorities());
  }

}
