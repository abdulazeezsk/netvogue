package org.netvogue.server.webmvc.converters;

import org.netvogue.server.aws.core.ImageType;
import org.netvogue.server.aws.core.Size;
import org.netvogue.server.aws.core.UploadManager;
import org.netvogue.server.neo4japi.domain.Photo;
import org.netvogue.server.webmvc.domain.PhotoWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class PhotoConverter implements Converter<Photo, PhotoWeb> {

	@Autowired
	private UploadManager uploadManager;
	
	public PhotoWeb convert(Photo source) {
		// TODO Auto-generated method stub
		PhotoWeb newPhoto = new PhotoWeb();
		newPhoto.setLabel(source.getName());
		newPhoto.setSeasonname(source.getSeasonname());
		newPhoto.setUniqueid(source.getPhotouniqueid());
		
		String thumblink = uploadManager.getQueryString(source.getPhotouniqueid(), ImageType.GALLERY, Size.GThumb);
		String mainlink = uploadManager.getQueryString(source.getPhotouniqueid(), ImageType.GALLERY);
		String leftlink = uploadManager.getQueryString(source.getPhotouniqueid(), ImageType.GALLERY, Size.GLeft);
		newPhoto.setThumbnail_url(thumblink);
		newPhoto.setPiclink(mainlink);
		newPhoto.setLeft_url(leftlink);
		return newPhoto;
	}

}
