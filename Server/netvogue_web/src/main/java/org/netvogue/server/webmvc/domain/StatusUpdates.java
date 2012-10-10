package org.netvogue.server.webmvc.domain;

import java.util.Set;

public class StatusUpdates extends CommonResponse {
	ContactInfo contactinfo ;
	Set<StatusUpdate> updates;

	public Set<StatusUpdate> getUpdates() {
		return updates;
	}

	public void setUpdates(Set<StatusUpdate> updates) {
		this.updates = updates;
	}

	public ContactInfo getContactinfo() {
		return contactinfo;
	}

	public void setContactinfo(ContactInfo contactinfo) {
		this.contactinfo = contactinfo;
	}
	
}
