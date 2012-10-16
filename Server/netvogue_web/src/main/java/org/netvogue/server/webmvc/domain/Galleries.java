package org.netvogue.server.webmvc.domain;

import java.util.Set;

//Holding class for Galleries, print campaigns, editorials and collections
public class Galleries extends CommonResponse{
	Set<Gallery> galleries;
	
	public Galleries() {
		
	}

	public Set<Gallery> getGalleries() {
		return galleries;
	}

	public void setGalleries(Set<Gallery> galleries) {
		this.galleries = galleries;
	}
}
