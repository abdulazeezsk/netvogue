package org.netvogue.server.webmvc.converters;

import org.netvogue.server.aws.core.ImageType;
import org.netvogue.server.aws.core.Size;
import org.netvogue.server.aws.core.UploadManager;
import org.netvogue.server.webmvc.domain.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class NotificationConverter implements Converter<org.netvogue.server.neo4japi.domain.Notification, Notification> {

	@Autowired private UploadManager uploadManager;
	
	public Notification convert(
			org.netvogue.server.neo4japi.domain.Notification source) {
		
		Notification response = new Notification();
		response.setIsread(source.isRead());
		response.setName(source.getOtherUser().getName());
		response.setNetworkstatus(source.getStatus().toString());
		response.setText(source.getDesc());
		response.setProfileid(source.getOtherUser().getUsername());
		String profilepic = source.getOtherUser().getProfilePicLink();
		if(null != profilepic) {
			String thumburl = uploadManager.getQueryString(profilepic, ImageType.PROFILE_PIC, Size.PThumb);
			response.setThumbnail_url(thumburl);
			
			String topurl = uploadManager.getQueryString(profilepic, ImageType.PROFILE_PIC, Size.PTop);
			response.setTop_url(topurl);
		}
		response.setNotificationid(source.getNotificationid());
		return response;
	}
}
