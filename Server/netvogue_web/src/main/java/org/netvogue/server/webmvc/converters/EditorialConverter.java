package org.netvogue.server.webmvc.converters;

import org.netvogue.server.webmvc.domain.Editorial;
import org.springframework.core.convert.converter.Converter;

public class EditorialConverter implements Converter<org.netvogue.server.neo4japi.domain.Editorial, Editorial> {

	public Editorial convert(org.netvogue.server.neo4japi.domain.Editorial source) {
		Editorial newEditorial = new Editorial();
		newEditorial.setGalleryid(source.getEditorialid());
		newEditorial.setGalleryname(source.getEditorialname());
		newEditorial.setGallerypic(source.getProfilePicLink());
		newEditorial.setGallerydesc(source.getDescription());
		return newEditorial;
	}

}
