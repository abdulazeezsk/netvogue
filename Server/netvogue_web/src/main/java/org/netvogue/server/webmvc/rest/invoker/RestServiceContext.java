package org.netvogue.server.webmvc.rest.invoker;

public class RestServiceContext {

	private String baseUrl;

	public RestServiceContext(final String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

}
