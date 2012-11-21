package org.netvogue.ecommerce.persistence.mongo.converters;

import org.netvogue.ecommerce.domain.model.Lookbook;
import org.netvogue.ecommerce.domain.model.Privacy;
import org.netvogue.ecommerce.domain.model.Season;
import org.springframework.core.convert.converter.Converter;

import com.mongodb.DBObject;

import java.util.Date;

public class LookbookReadConverter implements Converter<DBObject, Lookbook> {

  public Lookbook convert(final DBObject source) {
    Lookbook lookbook = new Lookbook();
    lookbook.setId(source.get("_id").toString());
    lookbook.setCreatedDate((Date) source.get("createdDate"));
    lookbook.setCreatedBy((String) source.get("createdBy"));
    lookbook.setProfileLink((String)source.get("profileLink"));
    lookbook.setPrivacy(Privacy.valueOf((String)source.get("privacy")));
    lookbook.setCategory((String) source.get("category"));
    lookbook.setSeason(Season.valueOf((String) source.get("season")));
    lookbook.setYear((Integer) source.get("year"));
    lookbook.setProductLine((String) source.get("productLineName"));
    return lookbook;
  }

}
