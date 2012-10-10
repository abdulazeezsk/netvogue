package org.netvogue.server.webmvc.domain;

public class StatusUpdate {
	String profileid;
	String status;
	String statusid;
	String posteddate; 
	String left_url;
	public String getProfileid() {
		return profileid;
	}
	public void setProfileid(String profileid) {
		this.profileid = profileid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatusid() {
		return statusid;
	}
	public void setStatusid(String statusid) {
		this.statusid = statusid;
	}
	public String getPosteddate() {
		return posteddate;
	}
	public void setPosteddate(String posteddate) {
		this.posteddate = posteddate;
	}
	public String getLeft_url() {
		return left_url;
	}
	public void setLeft_url(String left_url) {
		this.left_url = left_url;
	}
	
}
