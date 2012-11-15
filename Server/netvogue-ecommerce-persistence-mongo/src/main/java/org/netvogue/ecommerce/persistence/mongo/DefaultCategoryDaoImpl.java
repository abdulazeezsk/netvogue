package org.netvogue.ecommerce.persistence.mongo;

import org.netvogue.ecommerce.domain.model.Category;
import org.netvogue.ecommerce.domain.model.ProductLine;
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

  public void addProductLine(final String categoryId, final ProductLine productLine) {
    Category category = mongoTemplate.findById(categoryId, Category.class, CATEGORY_COLLECTION_NAME);
    category.addProductLine(productLine);
    mongoTemplate.save(category, CATEGORY_COLLECTION_NAME);
  }

  public void deleteProductLine(final String categoryId, final String productLineId) {
    Category category = mongoTemplate.findById(categoryId, Category.class, CATEGORY_COLLECTION_NAME);
    category.removeProductLine(productLineId);
    mongoTemplate.save(category, CATEGORY_COLLECTION_NAME);
  }

  public List<ProductLine> findAllProductLinesByCategory(final String categoryId) {
    Category category = mongoTemplate.findById(categoryId, Category.class, CATEGORY_COLLECTION_NAME);
    return category.getProductlines();
  }

}
