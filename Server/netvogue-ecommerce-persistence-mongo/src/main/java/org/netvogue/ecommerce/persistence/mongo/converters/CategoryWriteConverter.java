package org.netvogue.ecommerce.persistence.mongo.converters;

import org.netvogue.ecommerce.domain.model.Category;
import org.netvogue.ecommerce.domain.model.ProductLine;
import org.netvogue.ecommerce.persistence.util.Util;
import org.springframework.core.convert.converter.Converter;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class CategoryWriteConverter implements Converter<Category, DBObject> {

  public DBObject convert(final Category category) {
    BasicDBObject dbOject = new BasicDBObject();

    if(category.getId() != null) {
        dbOject.put("_id", Util.massageAsObjectId(category.getId()));
    }

    dbOject.put("type", category.getCategoryType());
    dbOject.put("_class", Category.class.getName());

    BasicDBList list = new BasicDBList();
    for (ProductLine pl : category.getProductlines()) {
      BasicDBObject plObj = new BasicDBObject();
      plObj.put("name", pl.getName());
      plObj.put("description", pl.getDescription());
      list.add(plObj);
    }

    dbOject.put("productLines", list);
    return dbOject;
  }

}
