package org.netvogue.server.webmvc.converters;

import org.netvogue.server.aws.core.ImageType;
import org.netvogue.server.aws.core.Size;
import org.netvogue.server.aws.core.UploadManager;
import org.netvogue.server.webmvc.domain.Gallery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class GalleryConverter implements Converter<org.netvogue.server.neo4japi.domain.Gallery, Gallery> {

	@Autowired
	private UploadManager uploadManager;
	
	//This is used while returning data from database. So source is database domain and destination is web domain
	public Gallery convert(org.netvogue.server.neo4japi.domain.Gallery dbGallery) {
	    Gallery gallery = new Gallery();
	    gallery.setGalleryid(dbGallery.getGalleryid());
	    gallery.setGalleryname(dbGallery.getGalleryname());
	    if(0 == dbGallery.getPhotosAdded().size()) {
			gallery.setGallerypic(dbGallery.getProfilePicLink());
		} else {
			String thumblink = uploadManager.getQueryString(dbGallery.getProfilePicLink(), ImageType.GALLERY, Size.GThumb);
			gallery.setGallerypic(thumblink);
		}
	    gallery.setGallerydesc(dbGallery.getDescription());
		return gallery;
	}

}
