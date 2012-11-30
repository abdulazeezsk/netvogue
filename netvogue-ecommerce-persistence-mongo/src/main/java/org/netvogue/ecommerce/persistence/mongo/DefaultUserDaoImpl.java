package org.netvogue.ecommerce.persistence.mongo;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import org.netvogue.ecommerce.domain.model.User;
import org.netvogue.ecommerce.persistence.UserDao;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

public class DefaultUserDaoImpl implements UserDao {

  public static String USER_COLLECTION_NAME = "users";

  private MongoTemplate mongoTemplate;

  public DefaultUserDaoImpl() {
  }

  public void addUser(final User user) {
    mongoTemplate.insert(user, USER_COLLECTION_NAME);
  }

  public void updateUser(final User user) {
    List<User> usersFromDB = mongoTemplate.find(new Query(where("userName").is(user.getUsername())), User.class,
        USER_COLLECTION_NAME);
    checkUserExistance(usersFromDB, user.getUsername());
    User userFromDB = usersFromDB.get(0);
    applyChanges(userFromDB, user);
    mongoTemplate.save(userFromDB, USER_COLLECTION_NAME);
  }

  public void deactivateUser(final String userName) {
    mongoTemplate.findAndModify(new Query(where("userName").is(userName)), Update.update("active", false), User.class,
        USER_COLLECTION_NAME);
  }

  public void reActivateUser(final String userName) {
    mongoTemplate.findAndModify(new Query(where("userName").is(userName)), Update.update("active", true), User.class,
        USER_COLLECTION_NAME);
  }

  public void deleteUser(final String userName) {
    mongoTemplate.remove(new Query(where("userName").is(userName)), USER_COLLECTION_NAME);
  }

  public boolean authenticateUser(final String userName, final String password) {
    List<User> usersFromDB = mongoTemplate.find(new Query(where("userName").is(userName)), User.class,
        USER_COLLECTION_NAME);
    if (usersFromDB.get(0).getEncodedPassword().equals(password)) {
      return true;
    }

    return false;
  }

  public User getUser(final String userName) {
    List<User> usersFromDB = mongoTemplate.find(new Query(where("userName").is(userName)), User.class,
        USER_COLLECTION_NAME);
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

  private void applyChanges(final User originalUserDeatails, final User newUserDetails) {
    originalUserDeatails.setAboutUs(newUserDetails.getAboutUs());
    originalUserDeatails.setActive(newUserDetails.isActive());
    originalUserDeatails.setAddress(newUserDetails.getAddress());
    originalUserDeatails.setCity(newUserDetails.getCity());
    originalUserDeatails.setCountry(newUserDetails.getCountry());
    originalUserDeatails.setEmail(newUserDetails.getEmail());
    originalUserDeatails.setFirstName(newUserDetails.getFirstName());
    originalUserDeatails.setLastName(newUserDetails.getLastName());
    originalUserDeatails.setMobileNo(newUserDetails.getMobileNo());
    originalUserDeatails.setEncodedPassword(newUserDetails.getEncodedPassword());
    originalUserDeatails.setPrimarycontact(newUserDetails.getPrimarycontact());
    originalUserDeatails.setProfilePicLink(newUserDetails.getProfilePicLink());
    originalUserDeatails.setState(newUserDetails.getState());
    originalUserDeatails.setTelephoneNo1(newUserDetails.getTelephoneNo1());
    originalUserDeatails.setTelephoneNo2(newUserDetails.getTelephoneNo2());
    originalUserDeatails.setUserType(newUserDetails.getUserType());
    originalUserDeatails.setZipCode(newUserDetails.getZipCode());
  }

  public MongoTemplate getMongoTemplate() {
    return mongoTemplate;
  }

  public void setMongoTemplate(final MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }
}
