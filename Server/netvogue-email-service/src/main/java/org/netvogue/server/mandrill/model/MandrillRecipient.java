package org.netvogue.server.mandrill.model;

public class MandrillRecipient {
	
	String email;
	String name;
	
	public MandrillRecipient(String name, String email) {
		this.email = email;
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
