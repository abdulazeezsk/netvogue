package org.netvogue.server.webmvc.domain;

import java.util.Set;

import org.netvogue.server.webmvc.domain.PhotoWeb;

public class Photos {
	String name;
	String galleryname;
	Set<PhotoWeb> photos;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<PhotoWeb> getPhotos() {
		return photos;
	}
	public void setPhotos(Set<PhotoWeb> photos) {
		this.photos = photos;
	}
	public String getGalleryname() {
		return galleryname;
	}
	public void setGalleryname(String galleryname) {
		this.galleryname = galleryname;
	}
}
