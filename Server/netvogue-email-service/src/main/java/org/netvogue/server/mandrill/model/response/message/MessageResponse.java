package org.netvogue.server.mandrill.model.response.message;

import org.netvogue.server.mandrill.model.response.BaseMandrillResponse;

public class MessageResponse extends BaseMandrillResponse {

	String email; 
	String status;

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
}
