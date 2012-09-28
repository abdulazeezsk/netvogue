package org.netvogue.server.webmvc.converters;

import org.netvogue.server.webmvc.domain.PrintCampaign;
import org.springframework.core.convert.converter.Converter;

public class PrintCampaignConverter implements Converter<org.netvogue.server.neo4japi.domain.PrintCampaign, PrintCampaign> {

	public PrintCampaign convert(org.netvogue.server.neo4japi.domain.PrintCampaign source) {
		PrintCampaign newCampaign = new PrintCampaign();
		newCampaign.setGalleryid(source.getPrintcampaignid());
		newCampaign.setGalleryname(source.getPrintcampaignname());
		newCampaign.setGallerypic(source.getProfilePicLink());
		newCampaign.setGallerydesc(source.getDescription());
		return newCampaign;
	}

}
