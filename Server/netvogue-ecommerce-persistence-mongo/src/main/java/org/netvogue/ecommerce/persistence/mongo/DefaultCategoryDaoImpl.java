package org.netvogue.ecommerce.persistence.mongo;

import org.netvogue.ecommerce.domain.model.Category;
import org.netvogue.ecommerce.persistence.CategoryDao;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Set;

public class DefaultCategoryDaoImpl implements CategoryDao {

  private MongoTemplate mongoTemplate;

  public void addCategory(final Category category) {
    // TODO Auto-generated method stub

  }

  public void deleteCategory(final long categoryId) {
    // TODO Auto-generated method stub

  }

  public Category getCategory(final long categoryId) {
    // TODO Auto-generated method stub
    return null;
  }

  public Set<Category> findAllCategories() {
    // TODO Auto-generated method stub
    return null;
  }


  public void setMongoTemplate(final MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }
}
