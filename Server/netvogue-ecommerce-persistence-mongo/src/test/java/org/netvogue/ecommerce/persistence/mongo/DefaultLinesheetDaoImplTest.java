package org.netvogue.ecommerce.persistence.mongo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.netvogue.ecommerce.domain.model.Linesheet;
import org.netvogue.ecommerce.domain.model.Privacy;
import org.netvogue.ecommerce.domain.model.Season;
import org.netvogue.ecommerce.persistence.LinesheetDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class DefaultLinesheetDaoImplTest {


  @Autowired
  private LinesheetDao linesheetDao;

  @Autowired
  private MongoTemplate template;

  @Test
  public void test() {

    template.dropCollection(DefaultLinesheetDaoImpl.LINESHEETS_COLLECTION_NAME);

    template.createCollection(DefaultLinesheetDaoImpl.LINESHEETS_COLLECTION_NAME);


     Linesheet lt = new Linesheet();
     lt.setCategory("womensrtw");
     lt.setProductLine("prdline-1");
     lt.setCreatedDate(new Date());
     lt.setCreatedBy("psuman");
     lt.setPrivacy(Privacy.PRIVATE);
     lt.setProfileLink("http://s3link");
     lt.setSeason(Season.FALL_WINTER);
     lt.setYear(2012);
     linesheetDao.addLinesheet(lt);

     Linesheet lt1 = new Linesheet();
     lt1.setCategory("mensrtw");
     lt1.setProductLine("prdline-2");
     lt1.setCreatedDate(new Date());
     lt1.setCreatedBy("azeez");
     lt1.setPrivacy(Privacy.PRIVATE);
     lt1.setProfileLink("http://s3link");
     lt1.setSeason(Season.FALL_WINTER);
     lt1.setYear(2012);
     linesheetDao.addLinesheet(lt1);

     List<Linesheet> linesheets = linesheetDao.findAllLinesheets();
     assertEquals("linesheet size:", 2, linesheets.size());
     Linesheet ltFormDb = linesheetDao.getLinesheetById(linesheets.get(0).getId());
     assertNotNull(ltFormDb);
     List<Linesheet> linesheetsByUser = linesheetDao.findLinesheetsByUser("psuman");
     assertEquals("linesheet size:", 1, linesheetsByUser.size());

     List<Linesheet> linesheetsByCat = linesheetDao.findLinesheetsByCategory("womensrtw");
     assertEquals("linesheet size:", 1, linesheetsByCat.size());

     List<Linesheet> linesheetsByProdLine = linesheetDao.findLinesheetsByProductLine("prdline-1");
     assertEquals("linesheet size:", 1, linesheetsByProdLine.size());

     linesheetDao.deleteLinesheet(linesheets.get(0).getId());
     List<Linesheet> allLinesheets = linesheetDao.findAllLinesheets();

     assertEquals("linesheet size:", 1, allLinesheets.size());

  }
}
