package org.netvogue.server.webmvc.domain;

//Base class for Gallery, collections, printcampaigns and news letters
public class GenericGallery {
	String galleryid;
	String galleryname;
	String gallerypic;
	String gallerydesc;
	String gallerydate;
	
	public String getGalleryid() {
		return galleryid;
	}
	public void setGalleryid(String galleryid) {
		this.galleryid = galleryid;
	}
	public String getGalleryname() {
		return galleryname;
	}
	public void setGalleryname(String galleryname) {
		this.galleryname = galleryname;
	}
	public String getGallerypic() {
		return gallerypic;
	}
	public void setGallerypic(String gallerypic) {
		this.gallerypic = gallerypic;
	}
	public String getGallerydesc() {
		return gallerydesc;
	}
	public void setGallerydesc(String gallerydesc) {
		this.gallerydesc = gallerydesc;
	}
	public String getGallerydate() {
		return gallerydate;
	}
	public void setGallerydate(String gallerydate) {
		this.gallerydate = gallerydate;
	}
	
}
