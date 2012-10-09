package org.netvogue.server.webmvc.domain;

import java.util.Set;

public class Networks {
	String name;
	ImageURLsResponse profilepic;
	Set<Network> networks;
	ContactInfo contactinfo ;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ImageURLsResponse getProfilepic() {
		return profilepic;
	}
	public void setProfilepic(ImageURLsResponse profilepic) {
		this.profilepic = profilepic;
	}
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
