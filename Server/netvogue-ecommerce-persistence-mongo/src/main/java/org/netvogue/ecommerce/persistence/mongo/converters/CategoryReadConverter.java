package org.netvogue.ecommerce.persistence.mongo.converters;

import org.netvogue.ecommerce.domain.model.Category;
import org.netvogue.ecommerce.domain.model.ProductLine;
import org.springframework.core.convert.converter.Converter;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class CategoryReadConverter implements Converter<DBObject, Category> {

  public Category convert(final DBObject object) {

    Category category = new Category();
    category.setId((String) object.get("_id"));
    category.setCategoryType((String) object.get("type"));

    BasicDBList productLinesList = (BasicDBList) object.get("productLines");

    for (Object obj : productLinesList) {
      BasicDBObject dbObj = (BasicDBObject) obj;
      ProductLine line = new ProductLine();
      line.setId((String) dbObj.get("_id"));
      line.setName((String) dbObj.get("name"));
      line.setDescription((String) dbObj.get("description"));
      line.setSize((String) dbObj.get("size"));
      category.addProductLine(line);
    }

    return category;
  }

}
