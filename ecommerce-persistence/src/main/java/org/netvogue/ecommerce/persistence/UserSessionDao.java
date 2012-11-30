package org.netvogue.ecommerce.persistence;

public interface UserSessionDao {

  boolean isSessionValid(final String sessionId);
}
