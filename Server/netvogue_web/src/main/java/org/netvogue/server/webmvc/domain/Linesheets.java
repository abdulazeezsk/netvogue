package org.netvogue.server.webmvc.domain;

import java.util.Set;

public class Linesheets {
	String name;
	ImageURLsResponse profilepic;
	Set<Linesheet> lineesheets;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Linesheet> getLinesheets() {
		return lineesheets;
	}
	public void setLinesheets(Set<Linesheet> lineesheets) {
		this.lineesheets = lineesheets;
	}
	public ImageURLsResponse getProfilepic() {
		return profilepic;
	}
	public void setProfilepic(ImageURLsResponse profilepic) {
		this.profilepic = profilepic;
	}
}
