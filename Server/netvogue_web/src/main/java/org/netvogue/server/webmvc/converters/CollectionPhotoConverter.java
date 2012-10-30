package org.netvogue.server.webmvc.converters;

import org.netvogue.server.aws.core.ImageType;
import org.netvogue.server.aws.core.Size;
import org.netvogue.server.aws.core.UploadManager;
import org.netvogue.server.neo4japi.domain.CollectionPhoto;
import org.netvogue.server.webmvc.domain.PhotoWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CollectionPhotoConverter /*implements Converter<CollectionPhoto, PhotoWeb>*/ {

	@Autowired
	private UploadManager uploadManager;
	
	public PhotoWeb convert(CollectionPhoto source, String username) {
		PhotoWeb newPhoto = new PhotoWeb();
		newPhoto.setLabel(source.getCollectionphotoname());
		newPhoto.setSeasonname(source.getDescription());
		newPhoto.setUniqueid(source.getCollectionphotouniqueid());
		
		String addlink = uploadManager.getQueryString(source.getCollectionphotouniqueid(), ImageType.COLLECTION, 
				Size.CAdd, username);
		String thumblink = uploadManager.getQueryString(source.getCollectionphotouniqueid(), ImageType.COLLECTION, 
				Size.CThumb, username);
		String mainlink = uploadManager.getQueryString(source.getCollectionphotouniqueid(), ImageType.COLLECTION, username);
		String leftlink = uploadManager.getQueryString(source.getCollectionphotouniqueid(), ImageType.COLLECTION, 
				Size.CLeft, username);
		newPhoto.setAdd_url(addlink);
		newPhoto.setThumbnail_url(thumblink);
		newPhoto.setPiclink(mainlink);
		newPhoto.setLeft_url(leftlink);
		return newPhoto;
	}

}
