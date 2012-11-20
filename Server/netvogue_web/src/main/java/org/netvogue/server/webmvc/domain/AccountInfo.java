package org.netvogue.server.webmvc.domain;

public class AccountInfo extends CommonResponse{

	String email;
	String name;
	String password;
	EmailNotifications emailnotifications;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
  public String getName() {
		return name;
	}

	@Override
  public void setName(String name) {
		this.name = name;
	}

	public EmailNotifications getEmailnotifications() {
		return emailnotifications;
	}

	public void setEmailnotifications(EmailNotifications emailnotifications) {
		this.emailnotifications = emailnotifications;
	}

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
