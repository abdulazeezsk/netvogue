package org.netvogue.server.mandrill.model;

public class MandrillRequestWithCode extends MandrillRequestWithName {

	String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
