package org.netvogue.ecommerce.persistence.mongo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.netvogue.ecommerce.domain.model.Lookbook;
import org.netvogue.ecommerce.domain.model.Privacy;
import org.netvogue.ecommerce.domain.model.Season;
import org.netvogue.ecommerce.persistence.LookbookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 *
 * This test is just to prove storing and retrieval is working.
 * This is not the rt way to write test cases. We will revisit once we make basic version
 * up and running.
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class DefaultLookbookDaoImplTest {


  @Autowired
  private LookbookDao lookbookDao;

  @Autowired
  private MongoTemplate template;

  @Test
  public void test() {


      template.dropCollection(DefaultLookbookDaoImpl.LOOKBOOKS_COLLECTION_NAME);

      template.createCollection(DefaultLookbookDaoImpl.LOOKBOOKS_COLLECTION_NAME);


       Lookbook lb = new Lookbook();
       lb.setCategory("womensrtw");
       lb.setProductLine("prdline-1");
       lb.setCreatedDate(new Date());
       lb.setCreatedBy("psuman");
       lb.setPrivacy(Privacy.PRIVATE);
       lb.setProfileLink("http://s3link");
       lb.setSeason(Season.FALL_WINTER);
       lb.setYear(2012);
       lookbookDao.addLookbook(lb);

       Lookbook lb1 = new Lookbook();
       lb1.setCategory("mensrtw");
       lb1.setProductLine("prdline-2");
       lb1.setCreatedDate(new Date());
       lb1.setCreatedBy("azeez");
       lb1.setPrivacy(Privacy.PRIVATE);
       lb1.setProfileLink("http://s3link");
       lb1.setSeason(Season.FALL_WINTER);
       lb1.setYear(2012);
       lookbookDao.addLookbook(lb1);

       List<Lookbook> lookbooks = lookbookDao.findAllLookbooks();
       assertEquals("lookbooks size:", 2, lookbooks.size());
       Lookbook lbFormDb = lookbookDao.getLookbookById(lookbooks.get(0).getId());
       assertNotNull(lbFormDb);
       List<Lookbook> lookbooksByUser = lookbookDao.findLookbooksByUser("psuman");
       assertEquals("lookbook size:", 1, lookbooksByUser.size());

       List<Lookbook> lookbooksByCat = lookbookDao.findLookbooksByCategory("womensrtw");
       assertEquals("lookbook size:", 1, lookbooksByCat.size());

       List<Lookbook> lookbooksByProdLine = lookbookDao.findLookbooksByProductLine("prdline-1");
       assertEquals("lookbooks size:", 1, lookbooksByProdLine.size());

       lookbookDao.deleteLookbook(lookbooksByProdLine.get(0).getId());
       List<Lookbook> allLookbooks = lookbookDao.findAllLookbooks();

       assertEquals("lookbook size:", 1, allLookbooks.size());

  }
}
