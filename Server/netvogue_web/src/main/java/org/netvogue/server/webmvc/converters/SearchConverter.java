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
		if(null != source.getUserType())
			response.setUsertype(source.getUserType().toString());
		String profilepic = source.getProfilePicLink();
		if(null != profilepic && !profilepic.isEmpty()) {
			String smallpic = uploadManager.getQueryString(profilepic, ImageType.PROFILE_PIC,
					source.getUsername()); 
			response.setSmallpic(smallpic);
			
			String thumbpic = uploadManager.getQueryString(profilepic, ImageType.PROFILE_PIC,
					source.getUsername());
			response.setThumbpic(thumbpic);
			System.out.println("username:" + source.getUsername() + " : profilepic:" + profilepic);
		}
		return response;
	}

}
