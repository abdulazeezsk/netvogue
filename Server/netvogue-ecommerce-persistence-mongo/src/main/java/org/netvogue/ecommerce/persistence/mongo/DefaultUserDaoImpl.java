package org.netvogue.ecommerce.persistence.mongo;

import org.netvogue.ecommerce.domain.model.User;
import org.netvogue.ecommerce.persistence.UserDao;
import org.springframework.data.mongodb.core.MongoTemplate;

public class DefaultUserDaoImpl implements UserDao {

  private MongoTemplate mongoTemplate;

  public void addUser(final User user) {
    // TODO Auto-generated method stub

  }

  public void deactivateUser(final User user) {
    // TODO Auto-generated method stub

  }

  public void deleteUser(final User user) {
    // TODO Auto-generated method stub

  }

  public void authenticateUser(final String userId, final String password) {
    // TODO Auto-generated method stub

  }

  public void setMongoTemplate(final MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

}
