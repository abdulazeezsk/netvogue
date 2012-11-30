package org.netvogue.ecommerce.persistence.mongo;

import static org.netvogue.ecommerce.persistence.util.Util.massageAsObjectId;
import static org.springframework.data.mongodb.core.query.Criteria.where;

import org.netvogue.ecommerce.domain.model.Category;
import org.netvogue.ecommerce.domain.model.ProductLine;
import org.netvogue.ecommerce.persistence.CategoryDao;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class DefaultCategoryDaoImpl implements CategoryDao {

  private MongoTemplate mongoTemplate;

  public static String CATEGORY_COLLECTION_NAME = "categories";

  public DefaultCategoryDaoImpl() {
  }

  public void addCategory(final Category category) {
    mongoTemplate.insert(category, CATEGORY_COLLECTION_NAME);
  }

  public void deleteCategory(final String categoryId) {
    mongoTemplate.remove(new Query(where("_id").is(massageAsObjectId(categoryId))), CATEGORY_COLLECTION_NAME);
  }

  public Category getCategory(final String categoryId) {
    Category category = mongoTemplate.findById(massageAsObjectId(categoryId), Category.class, CATEGORY_COLLECTION_NAME);
    return category;
  }

  public List<Category> findAllCategories() {
    List<Category> categories = mongoTemplate.findAll(Category.class, CATEGORY_COLLECTION_NAME);
    return categories;
  }

  public void addProductLine(final String categoryId, final ProductLine productLine) {
    Category category = mongoTemplate.findById(massageAsObjectId(categoryId), Category.class, CATEGORY_COLLECTION_NAME);
    category.addProductLine(productLine);
    mongoTemplate.save(category, CATEGORY_COLLECTION_NAME);
  }

  public void deleteProductLine(final String categoryId, final String productLineId) {
    Category category = mongoTemplate.findById(massageAsObjectId(categoryId), Category.class, CATEGORY_COLLECTION_NAME);
    category.removeProductLine(productLineId);
    mongoTemplate.save(category, CATEGORY_COLLECTION_NAME);
  }

  public List<ProductLine> findAllProductLinesByCategory(final String categoryId) {
    Category category = mongoTemplate.findById(massageAsObjectId(categoryId), Category.class, CATEGORY_COLLECTION_NAME);
    return category.getProductlines();
  }

  public ProductLine getProductLine(final String categoryId, final String productLineName) {
    Category category = mongoTemplate.findById(massageAsObjectId(categoryId), Category.class, CATEGORY_COLLECTION_NAME);

    for (ProductLine prdLine : category.getProductlines()) {
      if (prdLine.getName().equals(productLineName)) {
        return prdLine;
      }
    }

    throw new RuntimeException("there is no product line exists with id :" + productLineName);
  }

  public MongoTemplate getMongoTemplate() {
    return mongoTemplate;
  }

  public void setMongoTemplate(final MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }
}
