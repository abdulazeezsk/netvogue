package org.netvogue.server.webmvc.converters;

import org.netvogue.server.aws.core.ImageType;
import org.netvogue.server.aws.core.Size;
import org.netvogue.server.aws.core.UploadManager;
import org.netvogue.server.neo4japi.domain.CollectionPhoto;
import org.netvogue.server.webmvc.domain.PhotoWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class CollectionPhotoConverter implements Converter<CollectionPhoto, PhotoWeb> {

	@Autowired
	private UploadManager uploadManager;
	
	public PhotoWeb convert(CollectionPhoto source) {
		PhotoWeb newPhoto = new PhotoWeb();
		newPhoto.setLabel(source.getCollectionphotoname());
		newPhoto.setSeasonname(source.getDescription());
		newPhoto.setUniqueid(source.getCollectionphotouniqueid());
		
		String thumblink = uploadManager.getQueryString(source.getCollectionphotouniqueid(), ImageType.COLLECTION, Size.CThumb);
		String mainlink = uploadManager.getQueryString(source.getCollectionphotouniqueid(), ImageType.COLLECTION);
		String leftlink = uploadManager.getQueryString(source.getCollectionphotouniqueid(), ImageType.COLLECTION, Size.CLeft);
		newPhoto.setThumbnail_url(thumblink);
		newPhoto.setPiclink(mainlink);
		newPhoto.setLeft_url(leftlink);
		return newPhoto;
	}

}
