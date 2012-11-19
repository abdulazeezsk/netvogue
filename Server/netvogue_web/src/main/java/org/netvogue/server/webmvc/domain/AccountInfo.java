package org.netvogue.server.webmvc.domain;

public class AccountInfo extends CommonResponse{
	
	String email;
	String name;
	EmailNotifications emailnotifications;

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

	public EmailNotifications getEmailnotifications() {
		return emailnotifications;
	}

	public void setEmailnotifications(EmailNotifications emailnotifications) {
		this.emailnotifications = emailnotifications;
	}
}
