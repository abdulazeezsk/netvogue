package org.netvogue.server.admin.domain;

public class JsonResponse {
	private boolean status = false;
	private String error = null;

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
