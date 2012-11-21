package org.netvogue.ecommerce.persistence.mongo.converters;

import org.bson.types.ObjectId;
import org.netvogue.ecommerce.domain.model.Privacy;
import org.netvogue.ecommerce.domain.model.Style;
import org.netvogue.ecommerce.domain.model.StyleSize;
import org.springframework.core.convert.converter.Converter;

import com.mongodb.BasicDBList;
import com.mongodb.DBObject;

import java.util.Date;

public class StyleReadConverter implements Converter<DBObject, Style> {

  public Style convert(final DBObject source) {

    String styleName = (String) source.get("styleName");
    String styleNo = (String) source.get("styleNo");
    long price = (Long) source.get("price");

    Style style = new Style(styleName, styleNo, price);
    style.setId(((ObjectId) source.get("_id")).toString());
    style.setLookbookId((String) source.get("lookbookId"));
    style.setLinesheetId((String) source.get("linesheetId"));
    style.setDescription((String) source.get("description"));
    style.setFabrication((String) source.get("fabrication"));
    style.setBrand((String) source.get("brand"));
    style.setCreatedDate((Date) source.get("createdDate"));
    style.setPrivacy(Privacy.valueOf((String)source.get("privacy")));
    style.setCategory((String) source.get("category"));
    style.setProductLine((String) source.get("productLineName"));


    BasicDBList availableSizes = (BasicDBList) source.get("availableSizes");

    for (Object obj : availableSizes) {
      style.addSize(StyleSize.valueOf((String) obj));
    }

    BasicDBList availableImages = (BasicDBList) source.get("availableImages");

    for (Object obj : availableImages) {
      style.addImage((String) obj);
    }

    BasicDBList availableColors = (BasicDBList) source.get("availableColors");

    for (Object obj : availableColors) {
      style.addColor((String) obj);
    }

    return style;
  }

}
