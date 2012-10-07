package org.netvogue.server.webmvc.converters;

import org.netvogue.server.aws.core.ImageType;
import org.netvogue.server.aws.core.Size;
import org.netvogue.server.aws.core.UploadManager;
import org.netvogue.server.webmvc.domain.ImageURLsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class ImageURLsConverter implements Converter<String, ImageURLsResponse> {

	@Autowired
	private UploadManager uploadManager;
	
	public ImageURLsResponse convert(String source) {
		ImageURLsResponse response = new ImageURLsResponse();
		String thumburl = uploadManager.getQueryString(source, ImageType.PROFILE_PIC, Size.PThumb);
		response.setThumbnail_url(thumburl);
		String lefturl = uploadManager.getQueryString(source, ImageType.PROFILE_PIC, Size.PTop);
		response.setLeft_url(lefturl);
		String piclink = uploadManager.getQueryString(source, ImageType.PROFILE_PIC);
		response.setPiclink(piclink);
		return response;
	}

}
