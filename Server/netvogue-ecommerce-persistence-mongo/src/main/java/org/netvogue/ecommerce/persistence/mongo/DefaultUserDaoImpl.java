package org.netvogue.ecommerce.persistence.mongo;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import org.netvogue.ecommerce.domain.model.User;
import org.netvogue.ecommerce.persistence.UserDao;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class DefaultUserDaoImpl implements UserDao {

  public static String USER_COLLECTION_NAME = "users";

  private MongoTemplate mongoTemplate;

  public DefaultUserDaoImpl(final MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  public void addUser(final User user) {
    mongoTemplate.insert(user, USER_COLLECTION_NAME);
  }

  public void updateUser(final User user) {
    List<User> usersFromDB = mongoTemplate.find(new Query(where("userName").is(user.getUsername())), User.class, USER_COLLECTION_NAME);
    checkUserExistance(usersFromDB, user.getUsername());
    User userFromDB = usersFromDB.get(0);
    applyChanges(userFromDB, user);
    mongoTemplate.save(userFromDB);
  }

  public void deactivateUser(final User user) {
    List<User> usersFromDB = mongoTemplate.find(new Query(where("userName").is(user.getUsername())), User.class, USER_COLLECTION_NAME);
    checkUserExistance(usersFromDB, user.getUsername());
    usersFromDB.get(0).setActive(false);
  }

  public void reActivateUser(final User user) {
    List<User> usersFromDB = mongoTemplate.find(new Query(where("userName").is(user.getUsername())), User.class, USER_COLLECTION_NAME);
    checkUserExistance(usersFromDB, user.getUsername());
    usersFromDB.get(0).setActive(true);
  }

  public void deleteUser(final User user) {
    List<User> usersFromDB = mongoTemplate.find(new Query(where("userName").is(user.getUsername())), User.class, USER_COLLECTION_NAME);
    checkUserExistance(usersFromDB, user.getUsername());
    mongoTemplate.remove(usersFromDB.get(0), USER_COLLECTION_NAME);
  }

  public boolean authenticateUser(final String userName, final String password) {
    List<User> usersFromDB = mongoTemplate.find(new Query(where("userName").is(userName)), User.class, USER_COLLECTION_NAME);
    if (usersFromDB.get(0).getPassword().equals(password)) {
      return true;
    }

    return false;
  }

  public User getUser(final String userName) {
    List<User> usersFromDB = mongoTemplate.find(new Query(where("userName").is(userName)), User.class, USER_COLLECTION_NAME);
    checkUserExistance(usersFromDB, userName);
    return usersFromDB.get(0);
  }

  public User getActiveUser(final String userName) {
    List<User> users = mongoTemplate.find(new Query(where("userName").is(userName)
        .andOperator(where("active").is(true))), User.class, USER_COLLECTION_NAME);

    if (users.size() == 0) {
      throw new RuntimeException("there is no active user with user name:" + userName);

    }
    if (users.size() > 1) {
      throw new RuntimeException("there are more than one user with user name:" + userName);
    }
    return users.get(0);
  }

  private void checkUserExistance(final List<User> users, final String userName) {
    if (users.size() == 0) {
      throw new RuntimeException("there is no user with user name:" + userName);

    }
    if (users.size() > 1) {
      throw new RuntimeException("there are more than one user with user name:" + userName);
    }
  }

  private void applyChanges(final User originalUser, final User newUser) {
    originalUser.setAboutUs(newUser.getAboutUs());
    originalUser.setActive(newUser.isActive());
    originalUser.setAddress(newUser.getAddress());
    originalUser.setCity(newUser.getCity());
    originalUser.setCountry(newUser.getCountry());
    originalUser.setEmail(newUser.getEmail());
    originalUser.setFirstName(newUser.getFirstName());
    originalUser.setLastName(newUser.getLastName());
    originalUser.setMobileNo(newUser.getMobileNo());
    originalUser.setPassword(newUser.getPassword());
    originalUser.setPrimarycontact(newUser.getPrimarycontact());
    originalUser.setProfilePicLink(newUser.getProfilePicLink());
    originalUser.setState(newUser.getState());
    originalUser.setTelephoneNo1(newUser.getTelephoneNo1());
    originalUser.setTelephoneNo2(newUser.getTelephoneNo2());
    originalUser.setUserType(newUser.getUserType());
    originalUser.setZipCode(newUser.getZipCode());
  }


}
