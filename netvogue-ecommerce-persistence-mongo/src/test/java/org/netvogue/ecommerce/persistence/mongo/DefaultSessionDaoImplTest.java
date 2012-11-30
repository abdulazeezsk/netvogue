package org.netvogue.ecommerce.persistence.mongo;

import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.netvogue.ecommerce.persistence.UserSessionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class DefaultSessionDaoImplTest {

  @Autowired
   private UserSessionDao userSessionDao;


   @Test
   public void test() {
      boolean res = userSessionDao.isSessionValid("77751us4vzl43qmotoz57smt0j18n");
     assertFalse(res);

   }
}
