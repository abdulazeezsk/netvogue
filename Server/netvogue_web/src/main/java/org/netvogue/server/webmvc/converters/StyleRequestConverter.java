package org.netvogue.server.webmvc.converters;

import java.util.HashSet;
import java.util.Set;

import org.netvogue.server.neo4japi.common.ProductLineSizes;
import org.netvogue.server.neo4japi.domain.Style;
import org.netvogue.server.webmvc.domain.StyleRequest;
import org.springframework.core.convert.converter.Converter;

public class StyleRequestConverter implements Converter<StyleRequest, Style> {

	public Style convert(StyleRequest source) {
		Style newStyle = new Style(source.getStylename(), source.getStyleno());
		newStyle.setAvailableColors(source.getAvailableColors());
		newStyle.setAvailableImages(source.getAvailableImages());
		Set<String>	availableSizes	= source.getAvailableSizes();
		Set<ProductLineSizes>	sizes = new HashSet<ProductLineSizes>();
		for(String size: availableSizes) {
			ProductLineSizes productLineSize = ProductLineSizes.valueOf(size);
			sizes.add(productLineSize);
		}
		newStyle.setAvailableSizes(sizes);
		newStyle.setDescription(source.getDescription());
		newStyle.setFabrication(source.getFabrication());
		newStyle.setPrice(source.getPrice());
		return newStyle;
	}

}
