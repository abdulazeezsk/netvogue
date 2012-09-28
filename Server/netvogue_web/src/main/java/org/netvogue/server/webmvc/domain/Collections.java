package org.netvogue.server.webmvc.domain;

import java.util.Set;

public class Collections {
	String name;
	Set<Collection> collections;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Collection> getCollections() {
		return collections;
	}
	public void setCollections(Set<Collection> collections) {
		this.collections = collections;
	}
}
