package org.netvogue.server.webmvc.domain;

import java.util.Set;

public class UsersResponse extends CommonResponse{
	Set<SearchResponse> users;
	
	public Set<SearchResponse> getUsers() {
		return users;
	}
	public void setUsers(Set<SearchResponse> users) {
		this.users = users;
	}
}
