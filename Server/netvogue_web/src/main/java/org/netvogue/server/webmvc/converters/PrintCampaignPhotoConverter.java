package org.netvogue.server.webmvc.converters;

import org.netvogue.server.aws.core.ImageType;
import org.netvogue.server.aws.core.Size;
import org.netvogue.server.aws.core.UploadManager;
import org.netvogue.server.neo4japi.domain.PrintCampaignPhoto;
import org.netvogue.server.webmvc.domain.PhotoWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class PrintCampaignPhotoConverter implements Converter<PrintCampaignPhoto, PhotoWeb> {

	@Autowired
	private UploadManager uploadManager;
	
	public PhotoWeb convert(PrintCampaignPhoto source) {
		PhotoWeb newPhoto = new PhotoWeb();
		newPhoto.setLabel(source.getPrintcampaignphotoname());
		newPhoto.setSeasonname(source.getDescription());
		newPhoto.setUniqueid(source.getPrintcampaignphotouniqueid());
		
		String addlink   = uploadManager.getQueryString(source.getPrintcampaignphotouniqueid(), ImageType.PRINT_CAMPAIGN, Size.PCAdd);
		String thumblink = uploadManager.getQueryString(source.getPrintcampaignphotouniqueid(), ImageType.PRINT_CAMPAIGN, Size.PCThumb);
		String mainlink = uploadManager.getQueryString(source.getPrintcampaignphotouniqueid(), ImageType.PRINT_CAMPAIGN);
		String leftlink = uploadManager.getQueryString(source.getPrintcampaignphotouniqueid(), ImageType.PRINT_CAMPAIGN, Size.PCLeft);
		newPhoto.setAdd_url(addlink);
		newPhoto.setThumbnail_url(thumblink);
		newPhoto.setPiclink(mainlink);
		newPhoto.setLeft_url(leftlink);
		return newPhoto;
	}

}
