package org.netvogue.ecommerce.persistence;

import org.netvogue.ecommerce.domain.model.User;

public interface UserDao {

  void addUser(final User user);

  void updateUser(final User user);

  void deactivateUser(final User user);

  void reActivateUser(final User user);

  void deleteUser(final User user);

  User getUser(final String userName);

  User getActiveUser(final String userName);

  boolean authenticateUser(final String userId, final String password);
}
