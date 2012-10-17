package org.netvogue.server.webmvc.domain;

import java.util.Set;

import org.netvogue.server.webmvc.domain.PhotoWeb;

public class Photos extends CommonResponse {
	
	String galleryname;
	String brandname;
	Set<PhotoWeb> photos;
	
	public Set<PhotoWeb> getPhotos() {
		return photos;
	}
	public void setPhotos(Set<PhotoWeb> photos) {
		this.photos = photos;
	}
	public String getBrandname() {
		return brandname;
	}
	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}
	public String getGalleryname() {
		return galleryname;
	}
	public void setGalleryname(String galleryname) {
		this.galleryname = galleryname;
	}
	
}
