package org.netvogue.server.webmvc.domain;

public class PhotoWeb {
	String uniqueid;
	String label;
	String seasonname;
	String piclink;
    String thumbnail_url;
    String left_url;
    
	public String getUniqueid() {
		return uniqueid;
	}
	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getSeasonname() {
		return seasonname;
	}
	public void setSeasonname(String seasonname) {
		this.seasonname = seasonname;
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
