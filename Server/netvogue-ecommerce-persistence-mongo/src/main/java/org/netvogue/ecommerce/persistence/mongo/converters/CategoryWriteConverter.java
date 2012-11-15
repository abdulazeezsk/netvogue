package org.netvogue.ecommerce.persistence.mongo.converters;

import org.netvogue.ecommerce.domain.model.Category;
import org.netvogue.ecommerce.domain.model.ProductLine;
import org.springframework.core.convert.converter.Converter;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class CategoryWriteConverter implements Converter<Category, DBObject> {

  public DBObject convert(final Category category) {
    BasicDBObject dbOject = new BasicDBObject();
    dbOject.put("_id", category.getId());
    dbOject.put("type", category.getCategoryType());
    dbOject.put("_class", Category.class.getName());

    BasicDBList list = new BasicDBList();
    for (ProductLine pl : category.getProductlines()) {
      BasicDBObject plObj = new BasicDBObject();
      plObj.put("_id", pl.getId());
      plObj.put("name", pl.getName());
      plObj.put("description", pl.getDescription());
      plObj.put("size", pl.getSize());
      list.add(plObj);
    }

    dbOject.put("productLines", list);
    return dbOject;
  }

}
