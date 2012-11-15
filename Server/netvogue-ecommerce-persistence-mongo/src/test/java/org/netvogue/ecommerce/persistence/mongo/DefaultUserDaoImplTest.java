package org.netvogue.ecommerce.persistence.mongo;

import static org.junit.Assert.assertNotNull;

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

    userFromDB.setActive(false);

    userDao.updateUser(userFromDB);

//    try {
    userFromDB = userDao.getActiveUser("psuman");
//    } catch(final RuntimeException e) {
//      userFromDB = null;
//    }
//    assertNull(userFromDB);

    // template.dropCollection(DefaultUserDaoImpl.USER_COLLECTION_NAME);
  }

}
