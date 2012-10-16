package org.netvogue.server.webmvc.converters;

import org.netvogue.server.aws.core.ImageType;
import org.netvogue.server.aws.core.UploadManager;
import org.netvogue.server.neo4japi.domain.User;
import org.netvogue.server.webmvc.domain.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class SearchConverter implements Converter<User, SearchResponse> {

	@Autowired
	private UploadManager uploadManager;
	
	public SearchResponse convert(User source) {
		SearchResponse response = new SearchResponse();
		response.setName(source.getName());
		response.setUsername(source.getUsername());
		response.setCity(source.getCity());
		response.setCountry(source.getCountry());
		if(null != source.getProfilePicLink()) {
			String smallpic = uploadManager.getQueryString(source.getProfilePicLink(), ImageType.PROFILE_PIC); 
			response.setSmallpic(smallpic);
			
			String thumbpic = uploadManager.getQueryString(source.getProfilePicLink(), ImageType.PROFILE_PIC);
			response.setThumbpic(thumbpic);
		}
		return response;
	}

}
