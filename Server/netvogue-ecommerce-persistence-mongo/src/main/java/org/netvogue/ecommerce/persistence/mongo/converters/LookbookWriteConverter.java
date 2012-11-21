package org.netvogue.ecommerce.persistence.mongo.converters;

import org.netvogue.ecommerce.domain.model.Lookbook;
import org.netvogue.ecommerce.persistence.util.Util;
import org.springframework.core.convert.converter.Converter;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class LookbookWriteConverter implements Converter<Lookbook, DBObject> {

  public DBObject convert(final Lookbook source) {
    BasicDBObject dbOject = new BasicDBObject();

    if(source.getId() != null) {
      dbOject.put("_id", Util.massageAsObjectId(source.getId()));
  }

    dbOject.put("createdDate", source.getCreatedDate());
    dbOject.put("createdBy", source.getCreatedBy());
    dbOject.put("category", source.getCategory());
    dbOject.put("productLine", source.getProductLine());
    dbOject.put("season", source.getSeason().toString());
    dbOject.put("year", source.getYear());
    dbOject.put("profileLink", source.getProfileLink());
    dbOject.put("privacy", source.getPrivacy().toString());
    dbOject.put("_class", Lookbook.class.getName());

    return dbOject;
  }

}
