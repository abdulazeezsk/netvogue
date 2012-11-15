package org.netvogue.ecommerce.persistence.mongo.converters;

import org.netvogue.ecommerce.domain.model.Style;
import org.springframework.core.convert.converter.Converter;

import com.mongodb.DBObject;

public class StyleReadConverter implements Converter<DBObject, Style> {

  public Style convert(final DBObject source) {
    return null;
  }

}
