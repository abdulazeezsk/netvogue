package org.netvogue.server.webmvc.domain;

public class StyleJSONResponse {

	private boolean status = false;
    private String  error = null;
    private StyleResponse  style;
	public boolean isStatus() {
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
	public StyleResponse getStyle() {
		return style;
	}
	public void setStyle(StyleResponse style) {
		this.style = style;
	}
    
}
