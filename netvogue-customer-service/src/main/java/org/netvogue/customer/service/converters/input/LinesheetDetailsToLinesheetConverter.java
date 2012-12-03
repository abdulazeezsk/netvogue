package org.netvogue.customer.service.converters.input;

import org.netvogue.customer.service.converter.Converter;
import org.netvogue.customer.service.representations.LinesheetRepresentation;
import org.netvogue.ecommerce.domain.model.Linesheet;
import org.netvogue.ecommerce.domain.model.Privacy;
import org.netvogue.ecommerce.domain.model.Season;

import java.util.Date;


public class LinesheetDetailsToLinesheetConverter implements Converter<LinesheetRepresentation, Linesheet> {

  @Override
  public Linesheet convert(final LinesheetRepresentation source) throws Exception {
    Linesheet linesheet = new Linesheet();
    linesheet.setCategory(source.getCategory());
    linesheet.setCreatedBy(source.getCreatedBy());
    linesheet.setCreatedDate(new Date());
    linesheet.setPrivacy(Privacy.valueOf(source.getPrivacy()));
    linesheet.setProductLine(source.getProductLine());
    linesheet.setProfileLink(source.getProfileLink());
    linesheet.setSeason(Season.valueOf(source.getSeason()));
    linesheet.setYear(source.getYear());

    return linesheet;
  }

}
