package org.netvogue.server.webmvc.domain;

public class UsersAvailable {
	String name;
	String username;
	
	public UsersAvailable(String name, String username) {
		this.name = name;
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
