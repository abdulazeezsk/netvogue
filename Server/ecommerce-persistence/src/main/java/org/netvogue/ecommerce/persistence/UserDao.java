package org.netvogue.ecommerce.persistence;

import org.netvogue.ecommerce.domain.model.User;

public interface UserDao {

  void addUser(final User user);

  void deactivateUser(final User user);

  void deleteUser(final User user);
}
