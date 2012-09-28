package org.netvogue.server.webmvc.domain;

import java.util.Set;

//Holding class for Galleries, print campaigns, editorials and collections
public class Galleries {
	String name;
	Set<Gallery> galleries;
	
	public Galleries() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Gallery> getGalleries() {
		return galleries;
	}

	public void setGalleries(Set<Gallery> galleries) {
		this.galleries = galleries;
	}
	
}
