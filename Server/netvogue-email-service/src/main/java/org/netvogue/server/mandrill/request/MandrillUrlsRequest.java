package org.netvogue.server.mandrill.request;

import java.util.List;

import org.codehaus.jackson.type.TypeReference;

import org.netvogue.server.mandrill.exception.RequestFailedException;
import org.netvogue.server.mandrill.model.BaseMandrillRequest;
import org.netvogue.server.mandrill.model.MandrillRequestWithQuery;
import org.netvogue.server.mandrill.model.MandrillRequestWithUrl;
import org.netvogue.server.mandrill.model.ServiceMethods;
import org.netvogue.server.mandrill.model.response.BaseMandrillAnonymousListResponse;
import org.netvogue.server.mandrill.model.response.urls.UrlListResponse;
import org.netvogue.server.mandrill.model.response.urls.UrlResponse;

public class MandrillUrlsRequest {

	MandrillRESTRequest request;
	
	TypeReference<List<UrlResponse>> urlsListReference = new TypeReference<List<UrlResponse>>(){};
	
	public UrlListResponse getList(BaseMandrillRequest listRequest) throws RequestFailedException {
		UrlListResponse response = new UrlListResponse();
		response.setList(((BaseMandrillAnonymousListResponse<UrlResponse>)request.postRequest(listRequest, ServiceMethods.Urls.LIST, UrlListResponse.class, urlsListReference)).getList());
		return response;
	}
	
	public UrlListResponse doSearch(MandrillRequestWithQuery searchRequest) throws RequestFailedException {
		UrlListResponse response = new UrlListResponse();
		response.setList(((BaseMandrillAnonymousListResponse<UrlResponse>)request.postRequest(searchRequest, ServiceMethods.Urls.SEARCH, UrlListResponse.class, urlsListReference)).getList());
		return response;
	}
	
	public UrlListResponse getTimeSeries(MandrillRequestWithUrl seriesRequest) throws RequestFailedException {
		UrlListResponse response = new UrlListResponse();
		response.setList(((BaseMandrillAnonymousListResponse<UrlResponse>)request.postRequest(seriesRequest, ServiceMethods.Urls.TIME_SERIES, UrlListResponse.class, urlsListReference)).getList());
		return response;
	} 

	public void setRequest(MandrillRESTRequest request) {
		this.request = request;
	}
	
}
