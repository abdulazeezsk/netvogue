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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class DefaultCategoryDaoImplTest {

  @Autowired
  private CategoryDao categoryDao;

  @Autowired
  private MongoTemplate template;


  @Test
  public void test() {
    template.dropCollection(CategoryDao.CATEGORY_COLLECTION_NAME);

    template.createCollection(CategoryDao.CATEGORY_COLLECTION_NAME);

    Category c = new Category();
    c.setCategoryType("abc");
    c.setId("11111");
    for (int i = 0; i < 3; i++) {
      ProductLine line = new ProductLine();
      line.setDescription("desc-" + i);
      line.setId("prdline-" + i);
      line.setName("prdlinename-" + i);
      line.setSize("size-" + i);
      c.addProductLine(line);
    }

    Category c1 = new Category();
    c1.setCategoryType("abc");
    c1.setId("22222");
    for (int i = 0; i < 3; i++) {
      ProductLine line = new ProductLine();
      line.setDescription("desc-" + i);
      line.setId("prdline-" + i);
      line.setName("prdlinename-" + i);
      line.setSize("size-" + i);
      c1.addProductLine(line);
    }

    categoryDao.addCategory(c);
    categoryDao.addCategory(c1);

    List<Category> categories = categoryDao.findAllCategories();
    assertEquals("categories:", 2, categories.size());

    categoryDao.deleteCategory(categories.get(0).getId());
    assertEquals("categories:", 1, categoryDao.findAllCategories().size());

    Category catg = categoryDao.getCategory(categories.get(1).getId());
    assertNotNull("category:", catg);

    template.dropCollection(CategoryDao.CATEGORY_COLLECTION_NAME);
  }


}
