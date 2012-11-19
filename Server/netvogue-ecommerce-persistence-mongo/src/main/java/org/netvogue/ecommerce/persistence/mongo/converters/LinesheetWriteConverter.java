package org.netvogue.ecommerce.persistence.mongo.converters;

import org.netvogue.ecommerce.domain.model.Linesheet;
import org.netvogue.ecommerce.persistence.util.Util;
import org.springframework.core.convert.converter.Converter;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class LinesheetWriteConverter implements Converter<Linesheet, DBObject>{

  public DBObject convert(final Linesheet source) {

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
    dbOject.put("profileLink", source.getProfileLink());
    dbOject.put("privacy", source.getPrivacy().toString());
    dbOject.put("_class", Linesheet.class.getName());

    return dbOject;
  }

}
