package org.netvogue.server.webmvc.converters;

import org.netvogue.server.aws.core.ImageType;
import org.netvogue.server.aws.core.Size;
import org.netvogue.server.aws.core.UploadManager;
import org.netvogue.server.webmvc.common.Constants;
import org.netvogue.server.webmvc.domain.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CollectionConverter /*implements Converter<org.netvogue.server.neo4japi.domain.Collection, Collection> */{

	@Autowired
	private UploadManager uploadManager;
	
	public Collection convert(org.netvogue.server.neo4japi.domain.Collection source, String username) {
		Collection newCollection = new Collection();
		newCollection.setGalleryid(source.getCollectionid());
		newCollection.setGalleryname(source.getCollectionseasonname());
		
		String profilepic = source.getProfilePicLink();
		if(null == profilepic || profilepic.isEmpty()) {
			newCollection.setGallerypic(Constants.COLLECTION_DefaultPic);
		} else {
			String thumblink = uploadManager.getQueryString(profilepic, 
					ImageType.COLLECTION, Size.CThumb, username);
			newCollection.setGallerypic(thumblink);
			String leftlink = uploadManager.getQueryString(profilepic, 
					ImageType.COLLECTION, Size.CLeft, username);
			newCollection.setLeftpic(leftlink);
		}
		newCollection.setGallerydesc(source.getDescription());
		newCollection.setBrandname(source.getCreatedBy().getName());
		newCollection.setCategory(source.getProductcategory().getProductLine().getDesc());
		return newCollection;
	}

}
