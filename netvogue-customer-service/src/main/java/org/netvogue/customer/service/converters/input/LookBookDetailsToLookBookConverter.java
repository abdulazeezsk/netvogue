package org.netvogue.customer.service.converters.input;

import org.netvogue.customer.service.converter.Converter;
import org.netvogue.customer.service.representations.LookbookRepresentation;
import org.netvogue.ecommerce.domain.model.Lookbook;
import org.netvogue.ecommerce.domain.model.Privacy;
import org.netvogue.ecommerce.domain.model.Season;

import java.util.Date;

public class LookBookDetailsToLookBookConverter implements Converter<LookbookRepresentation, Lookbook> {

  @Override
  public Lookbook convert(final LookbookRepresentation source) {

    Lookbook lookbook = new Lookbook();
    lookbook.setCategory(source.getCategory());
    lookbook.setCreatedBy(source.getCreatedBy());
    lookbook.setCreatedDate(new Date());
    lookbook.setPrivacy(Privacy.valueOf(source.getPrivacy()));
    lookbook.setProductLine(source.getProductLine());
    lookbook.setProfileLink(source.getProfileLink());
    lookbook.setSeason(Season.valueOf(source.getSeason()));
    lookbook.setYear(source.getYear());

    return lookbook;
  }

}
