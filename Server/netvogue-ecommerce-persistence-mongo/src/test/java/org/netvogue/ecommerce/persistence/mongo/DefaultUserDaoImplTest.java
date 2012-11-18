package org.netvogue.ecommerce.persistence.mongo;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.netvogue.ecommerce.domain.model.User;
import org.netvogue.ecommerce.domain.model.UserType;
import org.netvogue.ecommerce.persistence.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class DefaultUserDaoImplTest {

  @Autowired
  private UserDao userDao;

  @Autowired
  private MongoTemplate template;

  @Test
  public void test() {
    template.dropCollection(DefaultUserDaoImpl.USER_COLLECTION_NAME);

    template.createCollection(DefaultUserDaoImpl.USER_COLLECTION_NAME);

    User user = new User();
    user.setUsername("psuman");
    user.setAboutUs("aboutus");
    user.setActive(true);
    user.setAddress("MIG-352");
    user.setCity("HYD");
    user.setCountry("IND");
    user.setEmail("papanaboina.suman@gmail.com");
    user.setFirstName("suman");
    user.setLastName("papanaboina");
    user.setMobileNo(9963430239L);
    user.setTelephoneNo1(9963430239L);
    user.setTelephoneNo2(9963430239L);
    user.setPrimarycontact("suman");
    user.setPassword("abcxyz");
    user.setProfilePicLink("http://mylink.com");
    user.setUserType(UserType.BRAND);
    user.setZipCode(1234);

    userDao.addUser(user);

    User userFromDB = userDao.getActiveUser("psuman");
    assertNotNull(userFromDB);

    userFromDB.setCity("BANG");
    userFromDB.setActive(false);

    userDao.updateUser(userFromDB);

    User userFromDB1;
    try {
      userFromDB1 = userDao.getActiveUser("psuman");
    } catch (final RuntimeException e) {
      userFromDB1 = null;
    }
    assertNull(userFromDB1);


    userDao.reActivateUser("psuman");

    User userFromDB2;
    try {
      userFromDB2 = userDao.getActiveUser("psuman");
    } catch (final RuntimeException e) {
      userFromDB2 = null;
    }
    assertNotNull(userFromDB2);

    userDao.deactivateUser("psuman");

    User userFromDB3;
    try {
      userFromDB3 = userDao.getActiveUser("psuman");
    } catch (final RuntimeException e) {
      userFromDB3 = null;
    }
    assertNull(userFromDB3);


    userDao.deleteUser("psuman");


    User userFromDB4;
    try {
      userFromDB4 = userDao.getUser("psuman");
    } catch (final RuntimeException e) {
      userFromDB4 = null;
    }
    assertNull(userFromDB4);
//      userDao.deleteUser("psuman");


    // template.dropCollection(DefaultUserDaoImpl.USER_COLLECTION_NAME);
  }

}
