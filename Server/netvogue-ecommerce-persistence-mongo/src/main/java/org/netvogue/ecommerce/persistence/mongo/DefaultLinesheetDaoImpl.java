package org.netvogue.ecommerce.persistence.mongo;

import static org.netvogue.ecommerce.persistence.util.Util.massageAsObjectId;
import static org.springframework.data.mongodb.core.query.Criteria.where;

import org.netvogue.ecommerce.domain.model.Linesheet;
import org.netvogue.ecommerce.persistence.LinesheetDao;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.List;

public class DefaultLinesheetDaoImpl implements LinesheetDao {

  private MongoTemplate mongoTemplate;

  public static String LINESHEETS_COLLECTION_NAME = "linesheets";

  public DefaultLinesheetDaoImpl(final MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  public void addLinesheet(final Linesheet sheet) {
    mongoTemplate.insert(sheet, LINESHEETS_COLLECTION_NAME);
  }

  public void deleteLinesheet(final String linesheetId) {
    mongoTemplate.remove(new Query(where("_id").is(massageAsObjectId(linesheetId))), LINESHEETS_COLLECTION_NAME);
  }

  public List<Linesheet> findAllLinesheets() {
    return mongoTemplate.findAll(Linesheet.class, LINESHEETS_COLLECTION_NAME);
  }

  public Linesheet getLinesheetById(final String linesheetId) {
    Linesheet linesheet = mongoTemplate.findOne(new Query(where("_id").is(massageAsObjectId(linesheetId))),
        Linesheet.class, LINESHEETS_COLLECTION_NAME);
    return linesheet;
  }

  public List<Linesheet> findLinesheetsByUser(final String userName) {
    List<Linesheet> linesheets = mongoTemplate.find(new Query(where("createdBy").is(userName)), Linesheet.class,
        LINESHEETS_COLLECTION_NAME);

    if (linesheets == null) {
      linesheets = new ArrayList<Linesheet>();
    }

    return linesheets;
  }

  public List<Linesheet> findLinesheetsByProductLine(final String productLineName) {
    List<Linesheet> linesheets = mongoTemplate.find(new Query(where("productLineName").is(productLineName)),
        Linesheet.class, LINESHEETS_COLLECTION_NAME);

    if (linesheets == null) {
      linesheets = new ArrayList<Linesheet>();
    }

    return linesheets;
  }

  public List<Linesheet> findLinesheetsByCategory(final String categoryId) {
    List<Linesheet> linesheets = mongoTemplate.find(new Query(where("categoryId").is(categoryId)), Linesheet.class,
        LINESHEETS_COLLECTION_NAME);

    if (linesheets == null) {
      linesheets = new ArrayList<Linesheet>();
    }

    return linesheets;
  }

}
