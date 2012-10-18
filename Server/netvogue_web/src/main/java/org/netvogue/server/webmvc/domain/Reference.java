package org.netvogue.server.webmvc.domain;

public class Reference {

	String city;
	String country;
	String usertype;
	String name;
	String profileid;
	String thumbnail_url;
	Integer mutualfriends;
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
	public void setUsertype(String usertype) {
		this.usertype = usertype;
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
	public Integer getMutualfriends() {
		return mutualfriends;
	}
	public void setMutualfriends(Integer mutualfriends) {
		this.mutualfriends = mutualfriends;
	}
	
}
