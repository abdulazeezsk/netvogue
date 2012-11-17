package org.netvogue.server.mandrill.model;

public class MandrillRequestWithQuery extends BaseMandrillRequest {

	private String q;

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}
	
}
