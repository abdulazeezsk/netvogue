package org.netvogue.server.webmvc.domain;

import java.util.Set;

public class Editorials extends CommonResponse {

	Set<Editorial> editorials;
	
	public Set<Editorial> getGalleries() {
		return editorials;
	}
	public void setGalleries(Set<Editorial> editorials) {
		this.editorials = editorials;
	}
	
}
