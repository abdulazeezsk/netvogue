package org.netvogue.ecommerce.persistence.mongo;

import org.netvogue.ecommerce.domain.model.ProductLine;
import org.netvogue.ecommerce.persistence.ProductLineDao;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Set;

public class DefaultProductLineDaoImpl implements ProductLineDao {

  private MongoTemplate mongoTemplate;

  public void addProductLine(final long categoryId, final ProductLine productLine) {
    // TODO Auto-generated method stub

  }

  public void deleteProductLine(final long categoryId, final long productLineId) {
    // TODO Auto-generated method stub

  }

  public Set<ProductLine> findAllProductLinesByCategory(final long categoryId) {
    // TODO Auto-generated method stub
    return null;
  }

  public Set<ProductLine> findAllProductLines() {
    // TODO Auto-generated method stub
    return null;
  }

  public void setMongoTemplate(final MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

}
