package org.netvogue.server.webmvc.domain;

import java.util.Set;

public class Editorials {

	String name;
	Set<Editorial> editorials;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Editorial> getGalleries() {
		return editorials;
	}
	public void setGalleries(Set<Editorial> editorials) {
		this.editorials = editorials;
	}
	
}
