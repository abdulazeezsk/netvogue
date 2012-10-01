package org.netvogue.server.webmvc.converters;

import org.netvogue.server.aws.core.ImageType;
import org.netvogue.server.aws.core.Size;
import org.netvogue.server.aws.core.UploadManager;
import org.netvogue.server.neo4japi.domain.EditorialPhoto;
import org.netvogue.server.webmvc.domain.PhotoWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class EditorialPhotoConverter implements Converter<EditorialPhoto, PhotoWeb> {

	@Autowired
	private UploadManager uploadManager;
	
	public PhotoWeb convert(EditorialPhoto source) {
		PhotoWeb newPhoto = new PhotoWeb();
		newPhoto.setLabel(source.getEditorialphotoname());
		newPhoto.setSeasonname(source.getDescription());
		newPhoto.setUniqueid(source.getEditorialphotouniqueid());
		
		String thumblink = uploadManager.getQueryString(source.getEditorialphotouniqueid(), ImageType.EDITORIAL, Size.EThumb);
		String mainlink = uploadManager.getQueryString(source.getEditorialphotouniqueid(), ImageType.EDITORIAL, Size.EMain);
		String leftlink = uploadManager.getQueryString(source.getEditorialphotouniqueid(), ImageType.EDITORIAL, Size.ELeft);
		newPhoto.setThumbnail_url(thumblink);
		newPhoto.setPiclink(mainlink);
		newPhoto.setLeft_url(leftlink);
		return newPhoto;
	}

}
