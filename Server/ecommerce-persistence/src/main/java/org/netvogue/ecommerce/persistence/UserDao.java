package org.netvogue.ecommerce.persistence;

import org.netvogue.ecommerce.domain.model.User;

public interface UserDao {

  void addUser(final User user);

  void updateUser(final User user);

  void deactivateUser(final String userName);

  void reActivateUser(final String userName);

  void deleteUser(final String userName);

  User getUser(final String userName);

  User getActiveUser(final String userName);

  boolean authenticateUser(final String userName, final String password);
}
