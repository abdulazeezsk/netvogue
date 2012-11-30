package org.netvogue.ecommerce.persistence.mongo.converters;

import org.netvogue.ecommerce.domain.model.Linesheet;
import org.netvogue.ecommerce.domain.model.Privacy;
import org.netvogue.ecommerce.domain.model.Season;
import org.springframework.core.convert.converter.Converter;

import com.mongodb.DBObject;

import java.util.Date;

public class LinesheetReadConverter implements Converter<DBObject, Linesheet> {

  public Linesheet convert(final DBObject source) {
    Linesheet linesheet = new Linesheet();
    linesheet.setId(source.get("_id").toString());
    linesheet.setCreatedDate((Date) source.get("createdDate"));
    linesheet.setProfileLink((String)source.get("profileLink"));
    linesheet.setPrivacy(Privacy.valueOf((String)source.get("privacy")));
    linesheet.setCreatedBy((String) source.get("createdBy"));
    linesheet.setCategory((String) source.get("category"));
    linesheet.setSeason(Season.valueOf((String) source.get("season")));
    linesheet.setYear((Integer) source.get("year"));
    linesheet.setProductLine((String) source.get("productLine"));

    return linesheet;
  }

}
