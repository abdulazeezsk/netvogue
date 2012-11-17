package org.netvogue.server.mandrill.request;

import java.util.List;

import org.codehaus.jackson.type.TypeReference;

import org.netvogue.server.mandrill.exception.RequestFailedException;
import org.netvogue.server.mandrill.model.BaseMandrillRequest;
import org.netvogue.server.mandrill.model.MandrillRequestWithCode;
import org.netvogue.server.mandrill.model.MandrillRequestWithName;
import org.netvogue.server.mandrill.model.ServiceMethods;
import org.netvogue.server.mandrill.model.response.BaseMandrillAnonymousListResponse;
import org.netvogue.server.mandrill.model.response.templates.TemplateListResponse;
import org.netvogue.server.mandrill.model.response.templates.TemplateResponse;

public class MandrillTemplatesRequest {
	
	MandrillRESTRequest request;
	
	TypeReference<List<TemplateResponse>> templatesListReference = new TypeReference<List<TemplateResponse>>(){};
	
	public TemplateResponse addTemplate(MandrillRequestWithCode addRequest) throws RequestFailedException {
		return (TemplateResponse) request.postRequest(addRequest, ServiceMethods.Templates.ADD, TemplateResponse.class);
	}
	
	public TemplateResponse getTemplateInfo(MandrillRequestWithName infoRequest) throws RequestFailedException {
		return (TemplateResponse) request.postRequest(infoRequest, ServiceMethods.Templates.INFO, TemplateResponse.class);
	}
	
	public TemplateResponse updateTemplate(MandrillRequestWithCode updateRequest) throws RequestFailedException {
		return (TemplateResponse) request.postRequest(updateRequest, ServiceMethods.Templates.UPDATE, TemplateResponse.class);
	}
	
	public TemplateResponse deleteTemplate(MandrillRequestWithName deleteRequest) throws RequestFailedException {
		return (TemplateResponse) request.postRequest(deleteRequest, ServiceMethods.Templates.DELETE, TemplateResponse.class);
	}
	
	public TemplateListResponse getTemplates(BaseMandrillRequest listRequest) throws RequestFailedException {
		TemplateListResponse response = new TemplateListResponse();
		response.setList(((BaseMandrillAnonymousListResponse<TemplateResponse>)request.postRequest(listRequest, ServiceMethods.Templates.LIST, TemplateResponse.class, templatesListReference)).getList());
		return response;
	}

	public void setRequest(MandrillRESTRequest request) {
		this.request = request;
	}

}
