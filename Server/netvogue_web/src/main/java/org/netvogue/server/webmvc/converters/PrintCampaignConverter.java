package org.netvogue.server.webmvc.converters;

import org.netvogue.server.aws.core.ImageType;
import org.netvogue.server.aws.core.Size;
import org.netvogue.server.aws.core.UploadManager;
import org.netvogue.server.webmvc.domain.PrintCampaign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class PrintCampaignConverter implements Converter<org.netvogue.server.neo4japi.domain.PrintCampaign, PrintCampaign> {

	@Autowired
	private UploadManager uploadManager;
	
	public PrintCampaign convert(org.netvogue.server.neo4japi.domain.PrintCampaign source) {
		PrintCampaign newCampaign = new PrintCampaign();
		newCampaign.setGalleryid(source.getPrintcampaignid());
		newCampaign.setGalleryname(source.getPrintcampaignname());
		String thumblink = uploadManager.getQueryString(source.getProfilePicLink(), ImageType.GALLERY, Size.GThumb);
		newCampaign.setGallerypic(thumblink);
		newCampaign.setGallerydesc(source.getDescription());
		return newCampaign;
	}

}
