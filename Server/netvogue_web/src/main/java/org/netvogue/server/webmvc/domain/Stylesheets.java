package org.netvogue.server.webmvc.domain;

import java.util.Set;

public class Stylesheets {
	String name;
	ImageURLsResponse profilepic;
	Set<Stylesheet> stylesheets;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Stylesheet> getStylesheets() {
		return stylesheets;
	}
	public void setStylesheets(Set<Stylesheet> stylesheets) {
		this.stylesheets = stylesheets;
	}
	public ImageURLsResponse getProfilepic() {
		return profilepic;
	}
	public void setProfilepic(ImageURLsResponse profilepic) {
		this.profilepic = profilepic;
	}
}
