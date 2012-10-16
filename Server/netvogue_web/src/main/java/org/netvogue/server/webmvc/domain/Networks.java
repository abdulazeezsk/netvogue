package org.netvogue.server.webmvc.domain;

import java.util.Set;

public class Networks extends CommonResponse{
	Set<Network> networks;
	ContactInfo contactinfo ;
	
	public Set<Network> getNetworks() {
		return networks;
	}
	public void setNetworks(Set<Network> networks) {
		this.networks = networks;
	}
	public ContactInfo getContactinfo() {
		return contactinfo;
	}
	public void setContactinfo(ContactInfo contactinfo) {
		this.contactinfo = contactinfo;
	}
}
