package org.netvogue.server.mandrill.request;

import java.util.List;

import org.codehaus.jackson.type.TypeReference;

import org.netvogue.server.mandrill.exception.RequestFailedException;
import org.netvogue.server.mandrill.model.MandrillMessageRequest;
import org.netvogue.server.mandrill.model.MandrillTemplatedMessageRequest;
import org.netvogue.server.mandrill.model.ServiceMethods;
import org.netvogue.server.mandrill.model.response.BaseMandrillAnonymousListResponse;
import org.netvogue.server.mandrill.model.response.message.MessageResponse;
import org.netvogue.server.mandrill.model.response.message.SendMessageResponse;

/**
 * This class holds various functions for the Mandrill Messages API
 * @author Brian Cribbs
 *
 */
public class MandrillMessagesRequest {
	
	MandrillRESTRequest request;
	TypeReference<List<MessageResponse>> messageResponseListReference = new TypeReference<List<MessageResponse>>(){};
	
	/**
	 * Send a new transactional message through Mandrill
	 * @param messageRequest a populated @see com.cribstechnologies.clients.mandrill.model.MandrillMessageRequest
	 * @throws RequestFailedException
	 */
	public SendMessageResponse sendMessage(MandrillMessageRequest messageRequest) throws RequestFailedException {
		SendMessageResponse response = new SendMessageResponse();
		response.setList(((BaseMandrillAnonymousListResponse<MessageResponse>) request.postRequest(messageRequest, ServiceMethods.Messages.SEND, SendMessageResponse.class, messageResponseListReference)).getList());
		return response;
	}
	
	public SendMessageResponse sendTemplatedMessage(MandrillTemplatedMessageRequest templateMessage) throws RequestFailedException {
		SendMessageResponse response = new SendMessageResponse();
		response.setList(((BaseMandrillAnonymousListResponse<MessageResponse>) request.postRequest(templateMessage, ServiceMethods.Messages.SEND_TEMPLATE, SendMessageResponse.class, messageResponseListReference)).getList());
		return response;
	}

	public void setRequest(MandrillRESTRequest request) {
		this.request = request;
	}
	
}
