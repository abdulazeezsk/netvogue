package org.netvogue.server.webmvc.domain;

public class EmailNotifications {
	boolean networkrequest 	= false;
	boolean newsletter		= false;
	public boolean isNetworkrequest() {
		return networkrequest;
	}
	public void setNetworkrequest(boolean networkrequestFlag) {
		this.networkrequest = networkrequestFlag;
	}
	public boolean isNewsletter() {
		return newsletter;
	}
	public void setNewsletter(boolean newsletterFlag) {
		this.newsletter = newsletterFlag;
	} 
}
