package org.netvogue.server.webmvc.domain;

public class Notification {
	String notificationid;
	String text;
	String networkstatus;
	String name;
	String profileid;
	String top_url;
	String thumbnail_url;
	boolean isread;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getNetworkstatus() {
		return networkstatus;
	}
	public void setNetworkstatus(String networkstatus) {
		this.networkstatus = networkstatus;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProfileid() {
		return profileid;
	}
	public void setProfileid(String profileid) {
		this.profileid = profileid;
	}
	public String getTop_url() {
		return top_url;
	}
	public void setTop_url(String top_url) {
		this.top_url = top_url;
	}
	public String getThumbnail_url() {
		return thumbnail_url;
	}
	public void setThumbnail_url(String thumbnail_url) {
		this.thumbnail_url = thumbnail_url;
	}
	public boolean isIsread() {
		return isread;
	}
	public void setIsread(boolean isread) {
		this.isread = isread;
	}
	public String getNotificationid() {
		return notificationid;
	}
	public void setNotificationid(String notificationid) {
		this.notificationid = notificationid;
	}
}
