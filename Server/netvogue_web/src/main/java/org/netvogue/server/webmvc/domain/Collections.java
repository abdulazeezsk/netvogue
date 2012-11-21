package org.netvogue.server.webmvc.domain;

import java.util.Set;

public class Collections extends CommonResponse{
	Set<Collection> collections;

	public Set<Collection> getCollections() {
		return collections;
	}
	public void setCollections(Set<Collection> collections) {
		this.collections = collections;
	}
	
}
