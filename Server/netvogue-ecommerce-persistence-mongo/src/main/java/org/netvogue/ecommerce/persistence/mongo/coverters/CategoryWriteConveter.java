package org.netvogue.ecommerce.persistence.mongo.coverters;

import org.netvogue.ecommerce.domain.model.Category;
import org.netvogue.ecommerce.domain.model.ProductLine;
import org.springframework.core.convert.converter.Converter;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class CategoryWriteConveter implements Converter<Category, DBObject> {

  public DBObject convert(final Category category) {
    BasicDBObject dbOject = new BasicDBObject();
    dbOject.put("type", category.getCategoryType());
    BasicDBList list = new BasicDBList();
    for (ProductLine pl : category.getProductlines()) {
      BasicDBObject plObj = new BasicDBObject();
      plObj.put("name", pl.getName());
      plObj.put("description", pl.getDescription());
      plObj.put("size", pl.getSize());
      list.add(plObj);
    }

    dbOject.put("productLines", list);
    return dbOject;
  }

}
