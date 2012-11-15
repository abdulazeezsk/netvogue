package org.netvogue.ecommerce.persistence.mongo;

import org.netvogue.ecommerce.domain.model.Style;
import org.netvogue.ecommerce.persistence.StyleDao;
import org.springframework.data.mongodb.core.MongoTemplate;

public class DefaultStyleDaoImpl implements StyleDao {

  private MongoTemplate mongoTemplate;

  public void addStyle(final Style style) {
    // TODO Auto-generated method stub

  }

  public void deleteStyle(final String styleId) {
    // TODO Auto-generated method stub

  }

  public void activateStyle(final String styleId) {
    // TODO Auto-generated method stub

  }

  public void deactivateStyle(final String styleId) {
    // TODO Auto-generated method stub

  }

  public void setMongoTemplate(final MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

}
