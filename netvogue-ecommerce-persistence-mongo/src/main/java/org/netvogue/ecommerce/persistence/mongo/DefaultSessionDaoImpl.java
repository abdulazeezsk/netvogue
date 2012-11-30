package org.netvogue.ecommerce.persistence.mongo;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import org.netvogue.ecommerce.persistence.UserSessionDao;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.DocumentCallbackHandler;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.DBObject;
import com.mongodb.MongoException;

import java.util.HashMap;
import java.util.Map;

public class DefaultSessionDaoImpl implements UserSessionDao {

  private MongoTemplate mongoTemplate;

  public static String SESSIONS_COLLECTION_NAME = "sessions";

  public DefaultSessionDaoImpl() {
  }


  public boolean isSessionValid(final String sessionId) {
    final  Map<String, String> res = new HashMap<String, String>();
    res.put("exists", "false");

    DocumentCallbackHandler handler = new DocumentCallbackHandler() {

      public void processDocument(final DBObject dbObject) throws MongoException, DataAccessException {
        if(((String)dbObject.get("id")).equals(sessionId)) {
          res.put("exists", "true");
      } else {
        res.put("exists", "false");
      }
      }
    };

    mongoTemplate.executeQuery(new Query(where("id").is(sessionId).andOperator(where("valid").is(true))), SESSIONS_COLLECTION_NAME, handler);
    return res.get("exists").equals("true");
  }


  public void setMongoTemplate(final MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }


}
