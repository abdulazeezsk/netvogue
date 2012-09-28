package org.netvogue.server.webmvc.domain;

public class PhotoInfoJsonRequest {
	String photoid;
	String photoname;
	String seasonname;
	public String getPhotoid() {
		return photoid;
	}
	public void setPhotoid(String photoid) {
		this.photoid = photoid;
	}
	public String getPhotoname() {
		return photoname;
	}
	public void setPhotoname(String photoname) {
		this.photoname = photoname;
	}
	public String getSeasonname() {
		return seasonname;
	}
	public void setSeasonname(String seasonname) {
		this.seasonname = seasonname;
	}
	
	@Override
	public String toString() {
		String result = "photoid:" + photoid;
		result += "\nphotoname" + photoname;
		result += "\nseasonname"+ seasonname;
		return result;
		
	}
}
