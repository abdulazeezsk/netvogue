package org.netvogue.ecommerce.persistence.mongo;

import org.netvogue.ecommerce.domain.model.Category;
import org.netvogue.ecommerce.persistence.CategoryDao;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

public class DefaultCategoryDaoImpl implements CategoryDao {

  private MongoTemplate mongoTemplate;

  public DefaultCategoryDaoImpl(final MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  public void addCategory(final Category category) {
    mongoTemplate.insert(category, CATEGORY_COLLECTION_NAME);
  }

  public void deleteCategory(final String categoryId) {
    Category category = mongoTemplate.findById(categoryId, Category.class, CATEGORY_COLLECTION_NAME);
    mongoTemplate.remove(category, CATEGORY_COLLECTION_NAME);
  }

  public Category getCategory(final String categoryId) {
    Category category = mongoTemplate.findById(categoryId, Category.class, CATEGORY_COLLECTION_NAME);
    return category;
  }

  public List<Category> findAllCategories() {
    List<Category> categories = mongoTemplate.findAll(Category.class, CATEGORY_COLLECTION_NAME);
    return categories;
  }

}
