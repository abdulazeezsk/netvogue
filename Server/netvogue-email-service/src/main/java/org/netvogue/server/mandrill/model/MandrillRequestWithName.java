package org.netvogue.server.mandrill.model;

public class MandrillRequestWithName extends BaseMandrillRequest {

	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
