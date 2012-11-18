package org.netvogue.ecommerce.persistence.mongo;

import org.netvogue.ecommerce.domain.model.Linesheet;
import org.netvogue.ecommerce.domain.model.Style;
import org.netvogue.ecommerce.persistence.LinesheetDao;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

public class DefaultLinesheetDaoImpl implements LinesheetDao {

  private MongoTemplate mongoTemplate;

  public static String LINESHEETS_COLLECTION_NAME = "linesheets";

  public DefaultLinesheetDaoImpl(final MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  public void addLinesheet(final Linesheet sheet) {
    // TODO Auto-generated method stub

  }

  public void deleteLinesheet(final String linesheetId) {
    // TODO Auto-generated method stub

  }

  public void addStyleToLinesheet(final String linesheetId, final Style style) {
    // TODO Auto-generated method stub

  }

  public void deleteStyleFromLinesheet(final String linesheetId, final String styleId) {
    // TODO Auto-generated method stub

  }

  public List<Linesheet> findAllLinesheets() {
    // TODO Auto-generated method stub
    return null;
  }

  public List<Linesheet> findLinesheetsByuser(final String userName) {
    // TODO Auto-generated method stub
    return null;
  }

  public Linesheet getLinesheetById(final String linesheetId) {
    // TODO Auto-generated method stub
    return null;
  }

}
