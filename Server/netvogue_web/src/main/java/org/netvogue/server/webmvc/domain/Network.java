package org.netvogue.server.webmvc.domain;

public class Network {

	String networkstatus;
	String city;
	String country;
	String usertype;
	String name;
	String profileid;
	String thumbnail_url;
	public String getNetworkstatus() {
		return networkstatus;
	}
	public void setNetworkstatus(String networkstatus) {
		this.networkstatus = networkstatus;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String isbrand) {
		this.usertype = isbrand;
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
	public String getThumbnail_url() {
		return thumbnail_url;
	}
	public void setThumbnail_url(String thumbnail_url) {
		this.thumbnail_url = thumbnail_url;
	}
}
