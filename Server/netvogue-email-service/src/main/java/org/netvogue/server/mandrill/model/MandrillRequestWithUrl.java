package org.netvogue.server.mandrill.model;

public class MandrillRequestWithUrl extends BaseMandrillRequest {

	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
