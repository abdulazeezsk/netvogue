package org.netvogue.server.webmvc.converters;

import org.netvogue.server.aws.core.ImageType;
import org.netvogue.server.aws.core.Size;
import org.netvogue.server.aws.core.UploadManager;
import org.netvogue.server.neo4japi.domain.Photo;
import org.netvogue.server.webmvc.domain.PhotoWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PhotoConverter /*implements Converter<Photo, PhotoWeb>*/ {

	@Autowired
	private UploadManager uploadManager;
	
	public PhotoWeb convert(Photo source, String username) {
		// TODO Auto-generated method stub
		PhotoWeb newPhoto = new PhotoWeb();
		newPhoto.setLabel(source.getPhotoname());
		newPhoto.setSeasonname(source.getSeasonname());
		newPhoto.setUniqueid(source.getPhotouniqueid());
		
		String thumblink = uploadManager.getQueryString(source.getPhotouniqueid(), ImageType.GALLERY, 
				Size.GThumb, username);
		String addlink	 = uploadManager.getQueryString(source.getPhotouniqueid(), ImageType.GALLERY, 
				Size.GAdd, username);
		String mainlink = uploadManager.getQueryString(source.getPhotouniqueid(), ImageType.GALLERY,
				username);
		String leftlink = uploadManager.getQueryString(source.getPhotouniqueid(), ImageType.GALLERY, 
				Size.GLeft, username);
		newPhoto.setAdd_url(addlink);
		newPhoto.setThumbnail_url(thumblink);
		newPhoto.setPiclink(mainlink);
		newPhoto.setLeft_url(leftlink);
		return newPhoto;
	}

}
