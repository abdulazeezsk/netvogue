package org.netvogue.server.webmvc.domain;

import java.util.Set;

public class UsersResponse {
	String name;
	ImageURLsResponse profilepic;
	Set<SearchResponse> users;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ImageURLsResponse getProfilepic() {
		return profilepic;
	}
	public void setProfilepic(ImageURLsResponse profilepic) {
		this.profilepic = profilepic;
	}
	public Set<SearchResponse> getUsers() {
		return users;
	}
	public void setUsers(Set<SearchResponse> users) {
		this.users = users;
	}
}
