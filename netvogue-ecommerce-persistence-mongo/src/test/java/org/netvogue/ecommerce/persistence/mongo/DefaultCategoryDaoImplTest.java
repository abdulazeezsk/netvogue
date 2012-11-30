package org.netvogue.ecommerce.persistence.mongo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.netvogue.ecommerce.domain.model.Category;
import org.netvogue.ecommerce.domain.model.ProductLine;
import org.netvogue.ecommerce.persistence.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 *
 * @author suman : To run this test you need to run mongodb in u r local on default port.
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class DefaultCategoryDaoImplTest {

  @Autowired
  private CategoryDao categoryDao;

  @Autowired
  private MongoTemplate template;


  @Test
  public void test() {
    template.dropCollection(DefaultCategoryDaoImpl.CATEGORY_COLLECTION_NAME);

    template.createCollection(DefaultCategoryDaoImpl.CATEGORY_COLLECTION_NAME);

    Category c = new Category();
    c.setCategoryType("abc");
    for (int i = 0; i < 3; i++) {
      ProductLine line = new ProductLine();
      line.setDescription("desc-" + i);
      line.setName("prdlinename-" + i);
      c.addProductLine(line);
    }

    Category c1 = new Category();
    c1.setCategoryType("abc");
    for (int i = 0; i < 2; i++) {
      ProductLine line = new ProductLine();
      line.setDescription("desc-" + i);
      line.setName("prdlinename-" + i);
      c1.addProductLine(line);
    }

    categoryDao.addCategory(c);
    categoryDao.addCategory(c1);

    List<Category> categories = categoryDao.findAllCategories();
    assertEquals("categories:", 2, categories.size());
    System.out.println("category id:" + categories.get(0).getId());
    categoryDao.deleteCategory(categories.get(0).getId());
    assertEquals("categories:", 1, categoryDao.findAllCategories().size());

    Category catg = categoryDao.getCategory(categories.get(1).getId());
    assertNotNull("category:", catg);

    assertEquals("categories prod lines:", 2, catg.getProductlines().size());

    ProductLine line = new ProductLine();
    line.setDescription("description");
    line.setName("prod-line-name");

    categoryDao.addProductLine(categories.get(1).getId(), line);

    List<ProductLine> prodLines = categoryDao.findAllProductLinesByCategory(categories.get(1).getId());
    assertEquals("prodlines size:", 3, prodLines.size());

    categoryDao.deleteProductLine(categories.get(1).getId(), "prdlinename-1");

    prodLines = categoryDao.findAllProductLinesByCategory(categories.get(1).getId());
    assertEquals("prodlines size:", 2, prodLines.size());

    template.dropCollection(DefaultCategoryDaoImpl.CATEGORY_COLLECTION_NAME);
  }


}
