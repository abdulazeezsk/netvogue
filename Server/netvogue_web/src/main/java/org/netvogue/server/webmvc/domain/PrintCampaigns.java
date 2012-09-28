package org.netvogue.server.webmvc.domain;

import java.util.Set;

public class PrintCampaigns {

	String name;
	Set<PrintCampaign> printcampaigns;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<PrintCampaign> getGalleries() {
		return printcampaigns;
	}
	public void setGalleries(Set<PrintCampaign> printcampaigns) {
		this.printcampaigns = printcampaigns;
	}
	
}
