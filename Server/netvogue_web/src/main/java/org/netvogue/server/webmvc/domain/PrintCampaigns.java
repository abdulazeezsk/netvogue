package org.netvogue.server.webmvc.domain;

import java.util.Set;

public class PrintCampaigns extends CommonResponse{

	Set<PrintCampaign> printcampaigns;
	
	public Set<PrintCampaign> getGalleries() {
		return printcampaigns;
	}
	public void setGalleries(Set<PrintCampaign> printcampaigns) {
		this.printcampaigns = printcampaigns;
	}

}
