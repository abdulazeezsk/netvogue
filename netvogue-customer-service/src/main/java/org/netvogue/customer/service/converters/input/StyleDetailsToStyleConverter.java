package org.netvogue.customer.service.converters.input;

import org.netvogue.customer.service.converter.Converter;
import org.netvogue.customer.service.representations.StyleRepresentation;
import org.netvogue.ecommerce.domain.model.Style;

import java.util.Date;

public class StyleDetailsToStyleConverter implements Converter<StyleRepresentation, Style> {

  @Override
  public Style convert(final StyleRepresentation source) {
    Style style = new Style(source.getStyleName(), source.getStyleNo(), source.getPrice());
    style.setBrand(source.getBrand());
    style.setCategory(source.getCategory());
    style.setCreatedDate(new Date());
    style.setDescription(source.getDescription());
    style.setFabrication(source.getFabrication());
    style.setLinesheetId(source.getLinesheetId());
    style.setId(source.getId());
    style.setAvailableColors(source.getAvailableColors());
    style.setAvailableImages(source.getAvailableImages());
    style.setAvailableSizes(source.getAvailableSizes());
    style.setPrice(source.getPrice());
    style.setProductLine(source.getPrivacy());

    return style;
  }

}
