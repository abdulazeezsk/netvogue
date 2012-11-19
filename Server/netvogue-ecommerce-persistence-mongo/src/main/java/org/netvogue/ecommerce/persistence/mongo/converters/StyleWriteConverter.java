package org.netvogue.ecommerce.persistence.mongo.converters;

import org.netvogue.ecommerce.domain.model.Linesheet;
import org.netvogue.ecommerce.domain.model.Style;
import org.netvogue.ecommerce.domain.model.StyleSize;
import org.netvogue.ecommerce.persistence.util.Util;
import org.springframework.core.convert.converter.Converter;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class StyleWriteConverter implements Converter<Style, DBObject> {

  public DBObject convert(final Style source) {

    BasicDBObject dbOject = new BasicDBObject();

    if(source.getId() != null) {
      dbOject.put("_id", Util.massageAsObjectId(source.getId()));
     }

    dbOject.put("styleName", source.getStyleName());
    dbOject.put("styleNo", source.getStyleNo());
    dbOject.put("description", source.getDescription());
    dbOject.put("fabrication", source.getFabrication());
    dbOject.put("price", source.getPrice());

    BasicDBList sizes = new BasicDBList();

    for (StyleSize size : source.getAvailableSizes()) {
      sizes.add(size.toString());
    }

    dbOject.put("availableSizes", sizes);

    BasicDBList images = new BasicDBList();

    for (String image : source.getAvailableImages()) {
      images.add(image);
    }

    dbOject.put("availableImages", images);


    BasicDBList colors = new BasicDBList();

    for (String color : source.getAvailableColors()) {
      colors.add(color);
    }

    dbOject.put("availableColors", colors);


    dbOject.put("createdDate", Linesheet.class.getName());
    dbOject.put("brand", source.getBrand().getUsername());
    dbOject.put("productLine", source.getProductLine().getName());
    dbOject.put("categoryId", source.getCategory().getId());
    dbOject.put("lookbookId", source.getLookbook().getId());
    dbOject.put("linesheetId", source.getLinesheet().getId());
    dbOject.put("privacy", source.getPrivacy().toString());

    return dbOject;
  }

}
