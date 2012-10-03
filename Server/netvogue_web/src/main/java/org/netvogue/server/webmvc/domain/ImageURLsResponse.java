package org.netvogue.server.webmvc.domain;

public class ImageURLsResponse {
	String photoid;
	String piclink;
    String thumbnail_url;
    String left_url;
    public ImageURLsResponse() {
    	
    }
    public ImageURLsResponse(String photoid, String pic, String thumb, String left) {
    	this.photoid = photoid;
    	piclink = pic;
    	thumbnail_url = thumb;
    	left_url = left;
    }
	public String getPiclink() {
		return piclink;
	}
	public void setPiclink(String piclink) {
		this.piclink = piclink;
	}
	public String getThumbnail_url() {
		return thumbnail_url;
	}
	public void setThumbnail_url(String thumbnail_url) {
		this.thumbnail_url = thumbnail_url;
	}
	public String getLeft_url() {
		return left_url;
	}
	public void setLeft_url(String left_url) {
		this.left_url = left_url;
	}
}
