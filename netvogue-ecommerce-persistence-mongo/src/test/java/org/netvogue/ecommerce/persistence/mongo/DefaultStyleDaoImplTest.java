package org.netvogue.ecommerce.persistence.mongo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.netvogue.ecommerce.domain.model.Linesheet;
import org.netvogue.ecommerce.domain.model.Lookbook;
import org.netvogue.ecommerce.domain.model.Privacy;
import org.netvogue.ecommerce.domain.model.Season;
import org.netvogue.ecommerce.domain.model.Style;
import org.netvogue.ecommerce.domain.model.StyleSize;
import org.netvogue.ecommerce.persistence.LinesheetDao;
import org.netvogue.ecommerce.persistence.LookbookDao;
import org.netvogue.ecommerce.persistence.StyleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class DefaultStyleDaoImplTest {

  @Autowired
  private StyleDao styleDao;

  @Autowired
  private LinesheetDao linesheetDao;

  @Autowired
  private LookbookDao lookbookDao;

  @Autowired
  private MongoTemplate template;


  @Test
  public void test() {

    template.dropCollection(DefaultStyleDaoImpl.STYLES_COLLECTION_NAME);

    template.createCollection(DefaultStyleDaoImpl.STYLES_COLLECTION_NAME);

    Style style = new Style("x", "z1", 100);
    Set<String> colors = new HashSet<String>();
    colors.add("red");
    colors.add("black");
    style.setAvailableColors(colors);
    Set<String> images = new HashSet<String>();
    colors.add("http://image1-s3link");
    colors.add("http://image2-s3link");
    style.setAvailableImages(images);
    Set<StyleSize> styleSizes = new HashSet<StyleSize>();
    styleSizes.add(StyleSize.L);
    styleSizes.add(StyleSize.M);
    style.setAvailableSizes(styleSizes);
    style.setCreatedDate(new Date());
    style.setBrand("psuman");
    style.setCategory("womensrtw");
    style.setDescription("new style");
    style.setFabrication("fabrication");
    style.setLinesheetId("x123");
    style.setPrice(1234);
    style.setPrivacy(Privacy.NETWROK);
    style.setProductLine("subcatg-1");

    Style style1 = new Style("y", "z2", 100);
    Set<String> colors1 = new HashSet<String>();
    colors1.add("red");
    colors1.add("black");
    style1.setAvailableColors(colors1);
    Set<String> images1 = new HashSet<String>();
    images1.add("http://image1-s3link");
    images1.add("http://image2-s3link");
    style1.setAvailableImages(images1);
    Set<StyleSize> styleSizes1 = new HashSet<StyleSize>();
    styleSizes1.add(StyleSize.L);
    styleSizes1.add(StyleSize.M);
    style1.setAvailableSizes(styleSizes1);
    style1.setCreatedDate(new Date());
    style1.setBrand("azeez");
    style1.setCategory("womensrtw-1");
    style1.setDescription("new style 1");
    style1.setFabrication("fabrication 1");
    style1.setPrice(5678);
    style1.setPrivacy(Privacy.NETWROK);
    style1.setProductLine("subcatg");


    Style style2 = new Style("y", "z2", 100);
    Set<String> colors2 = new HashSet<String>();
    colors2.add("red");
    colors2.add("black");
    style2.setAvailableColors(colors2);
    Set<String> images2 = new HashSet<String>();
    images2.add("http://image1-s3link");
    images2.add("http://image2-s3link");
    style2.setAvailableImages(images2);
    Set<StyleSize> styleSizes2 = new HashSet<StyleSize>();
    styleSizes2.add(StyleSize.L);
    styleSizes2.add(StyleSize.M);
    style2.setAvailableSizes(styleSizes2);
    style2.setCreatedDate(new Date());
    style2.setBrand("azeez");
    style2.setCategory("womensrtw-1");
    style2.setDescription("new style 1");
    style2.setFabrication("fabrication 1");
    style2.setPrice(5678);
    style2.setPrivacy(Privacy.NETWROK);
    style2.setProductLine("subcatg-3");

    template.dropCollection(DefaultLinesheetDaoImpl.LINESHEETS_COLLECTION_NAME);

    template.createCollection(DefaultLinesheetDaoImpl.LINESHEETS_COLLECTION_NAME);

    template.dropCollection(DefaultLookbookDaoImpl.LOOKBOOKS_COLLECTION_NAME);

    template.createCollection(DefaultLookbookDaoImpl.LOOKBOOKS_COLLECTION_NAME);

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


     Lookbook lb2 = new Lookbook();
     lb2.setCategory("mensrtw-1");
     lb2.setProductLine("prdline-3");
     lb2.setCreatedDate(new Date());
     lb2.setCreatedBy("azeez");
     lb2.setPrivacy(Privacy.PRIVATE);
     lb2.setProfileLink("http://s3link");
     lb2.setSeason(Season.FALL_WINTER);
     lb2.setYear(2012);
     lookbookDao.addLookbook(lb2);


    styleDao.addStyleToLookbook(lb.getId(), style);
    styleDao.addStyleToLookbook(lb1.getId(), style1);
    styleDao.addStyleToLookbook(lb2.getId(), style2);

    List<Style> styles = styleDao.findStylesByLookbook(lb.getId());
    assertEquals("styles", 1, styles.size());
    assertEquals("style lookbookid", lb.getId(), styles.get(0).getLookbookId());

    styleDao.addStyleToLinesheet(lt.getId(), styles.get(0).getId());

    List<Style> styles11 = styleDao.findStylesByLinesheet(lt.getId());
    assertEquals("styles", 1, styles11.size());
    assertEquals("style linesheetid", lt.getId(), styles11.get(0).getLinesheetId());


    List<Style> styles1 = styleDao.findStylesByLookbook(lb1.getId());
    assertEquals("styles", 1, styles1.size());
    assertEquals("style lookbookid", lb1.getId(), styles1.get(0).getLookbookId());

    styleDao.addStyleToLinesheet(lt1.getId(), styles1.get(0).getId());

    List<Style> styles12 = styleDao.findStylesByLinesheet(lt1.getId());
    assertEquals("styles", 1, styles12.size());
    assertEquals("style linesheetid", lt1.getId(), styles12.get(0).getLinesheetId());

    styleDao.unassignLinesheet(styles1.get(0).getId());

    List<Style> styles3 = styleDao.findStylesByLinesheet(lt1.getId());
    assertEquals("styles", 0, styles3.size());


    styleDao.unassignLookbook(styles1.get(0).getId());


    List<Style> styles2 = styleDao.findStylesByLookbook(lb1.getId());
    assertEquals("styles", 0, styles2.size());

    List<Style> styles4 = styleDao.findStylesByBrand("psuman");
    assertEquals("styles", 1, styles4.size());
    assertEquals("styles", "psuman", styles4.get(0).getBrand());

    List<Style> styles5 = styleDao.findStylesByCategory("womensrtw");
    assertEquals("styles", 1, styles5.size());
    assertEquals("styles", "womensrtw", styles5.get(0).getCategory());

    List<Style> styles6 = styleDao.findStylesByProductLine("subcatg-1");
    assertEquals("styles", 1, styles6.size());
    assertEquals("styles", "subcatg-1", styles6.get(0).getProductLine());

    List<Style> styles7 = styleDao.findStylesByPriceRange(1000, 5000);
    assertEquals("styles", 1, styles7.size());
    assertEquals("styles", 1234, styles7.get(0).getPrice());

    List<Style> styles8 = styleDao.findStylesByPriceRange(5000, 10000);
    assertEquals("styles", 1, styles8.size());
    assertEquals("styles", 5678, styles8.get(0).getPrice());

  }
}
