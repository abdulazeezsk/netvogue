package org.netvogue.server.webmvc.domain;


public class JsonResponse {
    private boolean status = false;
    private String  error = null;
    private String  idcreated;

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
	public String getIdcreated() {
		return idcreated;
	}
	public void setIdcreated(String idcreated) {
		this.idcreated = idcreated;
	}
}

