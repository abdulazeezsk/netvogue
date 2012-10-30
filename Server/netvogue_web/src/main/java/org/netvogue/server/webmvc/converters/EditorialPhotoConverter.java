package org.netvogue.server.webmvc.converters;

import org.netvogue.server.aws.core.ImageType;
import org.netvogue.server.aws.core.Size;
import org.netvogue.server.aws.core.UploadManager;
import org.netvogue.server.neo4japi.domain.EditorialPhoto;
import org.netvogue.server.webmvc.domain.PhotoWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EditorialPhotoConverter /*implements Converter<EditorialPhoto, PhotoWeb>*/ {

	@Autowired
	private UploadManager uploadManager;
	
	public PhotoWeb convert(EditorialPhoto source, String username) {
		PhotoWeb newPhoto = new PhotoWeb();
		newPhoto.setLabel(source.getEditorialphotoname());
		newPhoto.setSeasonname(source.getDescription());
		newPhoto.setUniqueid(source.getEditorialphotouniqueid());
		
		String addlink	 = uploadManager.getQueryString(source.getEditorialphotouniqueid(), ImageType.EDITORIAL, 
				Size.EAdd, username);
		String thumblink = uploadManager.getQueryString(source.getEditorialphotouniqueid(), ImageType.EDITORIAL, 
				Size.EThumb, username);
		String mainlink = uploadManager.getQueryString(source.getEditorialphotouniqueid(), ImageType.EDITORIAL,
				username);
		String leftlink = uploadManager.getQueryString(source.getEditorialphotouniqueid(), ImageType.EDITORIAL, 
				Size.ELeft, username);
		newPhoto.setAdd_url(addlink);
		newPhoto.setThumbnail_url(thumblink);
		newPhoto.setPiclink(mainlink);
		newPhoto.setLeft_url(leftlink);
		return newPhoto;
	}

}
