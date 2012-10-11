package org.netvogue.server.webmvc.converters;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.netvogue.server.aws.core.ImageType;
import org.netvogue.server.aws.core.Size;
import org.netvogue.server.aws.core.UploadManager;
import org.netvogue.server.neo4japi.service.StatusUpdateData;
import org.netvogue.server.webmvc.domain.StatusUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class StatusUpdateDataConverter implements Converter<StatusUpdateData, StatusUpdate> {

	@Autowired
	private UploadManager uploadManager;
	
	public StatusUpdate convert(StatusUpdateData source) {
		StatusUpdate response = new StatusUpdate();
		
		response.setName(source.getUser().getUsername());
		response.setProfileid(source.getUser().getUsername());
		String profilepic = source.getUser().getProfilePicLink();
		if(null != profilepic) {
			String topurl = uploadManager.getQueryString(profilepic, ImageType.PROFILE_PIC, Size.PTop);
			response.setLeft_url(topurl);
		}
		response.setStatusid(source.getUpdate().getStatusid());
		response.setStatus(source.getUpdate().getStatusUpdate());
		Date postedDate = source.getUpdate().getPostedDate();
		if(null != postedDate) {
			Format formatter = new SimpleDateFormat("MM/dd/yyyy");
			String postdate = formatter.format(postedDate);
			response.setPosteddate(postdate);
		}
		return response;
	}

}
