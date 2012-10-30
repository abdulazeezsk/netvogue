package org.netvogue.server.webmvc.converters;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.netvogue.server.aws.core.ImageType;
import org.netvogue.server.aws.core.Size;
import org.netvogue.server.aws.core.UploadManager;
import org.netvogue.server.neo4japi.domain.User;
import org.netvogue.server.neo4japi.service.StatusUpdateData;
import org.netvogue.server.webmvc.domain.StatusUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class StatusUpdateDataConverter implements Converter<StatusUpdateData, StatusUpdate> {

	@Autowired
	private UploadManager uploadManager;
	
	public StatusUpdate convert(StatusUpdateData source) {
		StatusUpdate response = new StatusUpdate();
		
		User user = source.getUser();
		response.setName(user.getUsername());
		response.setProfileid(user.getUsername());
		String profilepic = user.getProfilePicLink();
		if(null != profilepic) {
			String topurl = uploadManager.getQueryString(profilepic, ImageType.PROFILE_PIC, 
					Size.PTop, user.getUsername());
			response.setLeft_url(topurl);
		}
		org.netvogue.server.neo4japi.domain.StatusUpdate update = source.getUpdate(); 
		response.setStatusid(update.getStatusid());
		response.setStatus(update.getStatusUpdate());
		Date postedDate = update.getPostedDate();
		if(null != postedDate) {
			Format formatter = new SimpleDateFormat("MM/dd/yyyy");
			String postdate = formatter.format(postedDate);
			response.setPosteddate(postdate);
		}
		return response;
	}
}
