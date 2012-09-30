package org.netvogue.server.webmvc.converters;

import org.netvogue.server.aws.core.ImageType;
import org.netvogue.server.aws.core.Size;
import org.netvogue.server.aws.core.UploadManager;
import org.netvogue.server.webmvc.domain.Editorial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class EditorialConverter implements Converter<org.netvogue.server.neo4japi.domain.Editorial, Editorial> {

	@Autowired
	private UploadManager uploadManager;
	
	public Editorial convert(org.netvogue.server.neo4japi.domain.Editorial source) {
		Editorial newEditorial = new Editorial();
		newEditorial.setGalleryid(source.getEditorialid());
		newEditorial.setGalleryname(source.getEditorialname());
		String thumblink = uploadManager.getQueryString(source.getProfilePicLink(), ImageType.EDITORIAL, Size.EThumb);
		newEditorial.setGallerypic(thumblink);
		newEditorial.setGallerydesc(source.getDescription());
		return newEditorial;
	}

}
