package org.netvogue.server.webmvc.converters;

import org.netvogue.server.webmvc.domain.Gallery;
import org.springframework.core.convert.converter.Converter;

public class GalleryConverter implements Converter<org.netvogue.server.neo4japi.domain.Gallery, Gallery> {

	//This is used while returning data from database. So source is database domain and destination is web domain
	public Gallery convert(org.netvogue.server.neo4japi.domain.Gallery dbGallery) {
	    Gallery gallery = new Gallery();
	    gallery.setGalleryid(dbGallery.getGalleryid());
	    gallery.setGaleryname(dbGallery.getGalleryname());
	    gallery.setGallerypic(dbGallery.getProfilePicLink());
	    gallery.setGallerydesc(dbGallery.getDescription());
		return gallery;
	}

}
