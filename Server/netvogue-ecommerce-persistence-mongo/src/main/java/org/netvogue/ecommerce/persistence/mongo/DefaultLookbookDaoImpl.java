package org.netvogue.ecommerce.persistence.mongo;

import org.netvogue.ecommerce.domain.model.Lookbook;
import org.netvogue.ecommerce.domain.model.Style;
import org.netvogue.ecommerce.persistence.LookbookDao;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

public class DefaultLookbookDaoImpl implements LookbookDao {

  private MongoTemplate mongoTemplate;

  public static String LOOKBOOKS_COLLECTION_NAME = "lookbooks";

  public DefaultLookbookDaoImpl(final MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  public void addLookbook(final Lookbook lookbook) {
    // TODO Auto-generated method stub

  }

  public void deleteLookbook(final String lookbookId) {
    // TODO Auto-generated method stub

  }

  public void addStyleToLookbook(final String lookbookId, final Style style) {
    // TODO Auto-generated method stub

  }

  public void deleteStyleFromLookbook(final String lookbookId, final String styleId) {
    // TODO Auto-generated method stub

  }

  public List<Lookbook> findAllLookbooks() {
    // TODO Auto-generated method stub
    return null;
  }

  public List<Lookbook> findLookbooksByuser(final String userName) {
    // TODO Auto-generated method stub
    return null;
  }

  public Lookbook getLookbookById(final String lookBookId) {
    // TODO Auto-generated method stub
    return null;
  }

}
