package org.netvogue.ecommerce.persistence.mongo;

import static org.netvogue.ecommerce.persistence.util.Util.massageAsObjectId;
import static org.springframework.data.mongodb.core.query.Criteria.where;

import org.netvogue.ecommerce.domain.model.Lookbook;
import org.netvogue.ecommerce.persistence.LookbookDao;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.List;

public class DefaultLookbookDaoImpl implements LookbookDao {

  private MongoTemplate mongoTemplate;


  public static String LOOKBOOKS_COLLECTION_NAME = "lookbooks";

  public DefaultLookbookDaoImpl(final MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  public void addLookbook(final Lookbook lookbook) {
    mongoTemplate.insert(lookbook, LOOKBOOKS_COLLECTION_NAME);
  }

  public void deleteLookbook(final String lookbookId) {
    mongoTemplate.remove(new Query(where("_id").is(massageAsObjectId(lookbookId))), LOOKBOOKS_COLLECTION_NAME);

  }

  public List<Lookbook> findAllLookbooks() {
    return mongoTemplate.findAll(Lookbook.class, LOOKBOOKS_COLLECTION_NAME);
  }

  public Lookbook getLookbookById(final String lookBookId) {
    Lookbook lookbook = mongoTemplate.findOne(new Query(where("_id").is(massageAsObjectId(lookBookId))), Lookbook.class,
                                                                                                         LOOKBOOKS_COLLECTION_NAME);
    return lookbook;
  }

  public List<Lookbook> findLookbooksByUser(final String userName) {
    List<Lookbook> lookbooks = mongoTemplate.find(new Query(where("createdBy").is(userName)), Lookbook.class,
                                                                                              LOOKBOOKS_COLLECTION_NAME);

    if(lookbooks == null) {
      lookbooks = new ArrayList<Lookbook>();
    }

    return lookbooks;
  }


  public List<Lookbook> findLookbooksByProductLine(final String productLineName) {
    List<Lookbook> lookbooks = mongoTemplate.find(new Query(where("productLineName").is(productLineName)), Lookbook.class,
        LOOKBOOKS_COLLECTION_NAME);

    if (lookbooks == null) {
      lookbooks = new ArrayList<Lookbook>();
    }

    return lookbooks;
  }

  public List<Lookbook> findLookbooksByCategory(final String categoryId) {
    List<Lookbook> lookbooks = mongoTemplate.find(new Query(where("categoryId").is(massageAsObjectId(categoryId))), Lookbook.class,
        LOOKBOOKS_COLLECTION_NAME);

    if (lookbooks == null) {
      lookbooks = new ArrayList<Lookbook>();
    }

    return lookbooks;
  }

}
