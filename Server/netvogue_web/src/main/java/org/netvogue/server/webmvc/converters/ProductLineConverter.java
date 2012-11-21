package org.netvogue.server.webmvc.converters;

import org.netvogue.server.common.ProductLines;
import org.springframework.core.convert.converter.Converter;

public class ProductLineConverter implements Converter<String[], ProductLines[]>{

	public ProductLines[] convert(String[] brandsCarried) {
		System.out.println("ProductLineConverter--------------"+brandsCarried.toString());
		ProductLines[] res = new ProductLines[brandsCarried.length];
		int i=0;
		for(String source: brandsCarried) {
			res[i++] = ProductLines.getValueOf(source);
		}
		return res;
	}

}
