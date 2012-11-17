package org.netvogue.server.mandrill.request;

import java.util.List;

import org.codehaus.jackson.type.TypeReference;

import org.netvogue.server.mandrill.exception.RequestFailedException;
import org.netvogue.server.mandrill.model.BaseMandrillRequest;
import org.netvogue.server.mandrill.model.MandrillRequestWithTag;
import org.netvogue.server.mandrill.model.ServiceMethods;
import org.netvogue.server.mandrill.model.response.BaseMandrillAnonymousListResponse;
import org.netvogue.server.mandrill.model.response.tags.BaseTag;
import org.netvogue.server.mandrill.model.response.tags.TagListResponse;
import org.netvogue.server.mandrill.model.response.tags.TagSeriesResponse;
import org.netvogue.server.mandrill.model.response.tags.TagWithTime;

public class MandrillTagsRequest {
	
	MandrillRESTRequest request;
	
	TypeReference<List<TagWithTime>> timeTagReference = new TypeReference<List<TagWithTime>>(){};
	TypeReference<List<BaseTag>> nameTagReference = new TypeReference<List<BaseTag>>(){};

	public TagListResponse getList(BaseMandrillRequest tagsRequest) throws RequestFailedException {
		TagListResponse response = new TagListResponse();
		response.setList(((BaseMandrillAnonymousListResponse<BaseTag>) request.postRequest(tagsRequest, ServiceMethods.Tags.LIST, TagListResponse.class, nameTagReference)).getList());
		return response;
	}
	
	public TagSeriesResponse getTimeSeries(MandrillRequestWithTag tagsRequest) throws RequestFailedException {
		TagSeriesResponse response = new TagSeriesResponse();
		response.setList(((BaseMandrillAnonymousListResponse<TagWithTime>)request.postRequest(tagsRequest, ServiceMethods.Tags.TIME_SERIES, TagSeriesResponse.class, timeTagReference)).getList());
		return response;
	}
	
	public TagSeriesResponse getAllTimeSeries(BaseMandrillRequest tagsRequest) throws RequestFailedException {
		TagSeriesResponse response = new TagSeriesResponse();
		response.setList(((BaseMandrillAnonymousListResponse<TagWithTime>)request.postRequest(tagsRequest, ServiceMethods.Tags.ALL_TIME_SERIES, TagSeriesResponse.class, timeTagReference)).getList());
		return response;
	}
	
	public void setRequest(MandrillRESTRequest request) {
		this.request = request;
	}
}
