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
    dbOject.put("createdBy", source.getCreatedBy().getUsername());
    dbOject.put("categoryId", source.getCategory().getId());
    dbOject.put("productLineName", source.getProductLine().getName());
    dbOject.put("season", source.getSeason().toName());
    dbOject.put("year", source.getYear());
    dbOject.put("_class", Lookbook.class.getName());

    return dbOject;
  }

}