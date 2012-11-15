package org.netvogue.ecommerce.persistence.mongo.converters;

import org.netvogue.ecommerce.domain.model.Style;
import org.springframework.core.convert.converter.Converter;

import com.mongodb.DBObject;

public class StyleWriteConverter implements Converter<Style, DBObject> {

  public DBObject convert(final Style source) {
    return null;
  }

}
