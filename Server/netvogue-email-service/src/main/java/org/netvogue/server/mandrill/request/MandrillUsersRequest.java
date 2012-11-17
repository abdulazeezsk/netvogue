package org.netvogue.server.mandrill.request;

import java.util.List;

import org.codehaus.jackson.type.TypeReference;

import org.netvogue.server.mandrill.exception.RequestFailedException;
import org.netvogue.server.mandrill.model.BaseMandrillRequest;
import org.netvogue.server.mandrill.model.MandrillRequestWithDomain;
import org.netvogue.server.mandrill.model.MandrillRequestWithEmail;
import org.netvogue.server.mandrill.model.ServiceMethods;
import org.netvogue.server.mandrill.model.response.BaseMandrillAnonymousListResponse;
import org.netvogue.server.mandrill.model.response.BaseMandrillResponse;
import org.netvogue.server.mandrill.model.response.BaseMandrillStringResponse;
import org.netvogue.server.mandrill.model.response.users.DisableResponse;
import org.netvogue.server.mandrill.model.response.users.MandrillSender;
import org.netvogue.server.mandrill.model.response.users.UsersInfoResponse;
import org.netvogue.server.mandrill.model.response.users.UsersSendersResponse;
import org.netvogue.server.mandrill.model.response.users.VerifyResponse;

/**
 * 
 * This class holds various functions for the Mandrill Users API.
 * 
 * @author Brian Cribbs, brian@cribbstechnologies.com
 *
 */
public class MandrillUsersRequest {
	
	MandrillRESTRequest request;

	TypeReference<List<MandrillSender>> usersListReference = new TypeReference<List<MandrillSender>>(){};
	/**
	 * Return the information about the API-connected user
	 * @param infoRequest a populated @see org.netvogue.server.mandrill.model.BaseMandrillRequest
	 * @throws RequestFailedException
	 */
	public UsersInfoResponse getInfo(BaseMandrillRequest infoRequest) throws RequestFailedException {
		BaseMandrillResponse response = request.postRequest(infoRequest, ServiceMethods.Users.INFO, UsersInfoResponse.class);
		return (UsersInfoResponse) response;
	}
	
	/**
	 * Validate an API key and respond to a ping
	 * @param pingRequest a populated @see org.netvogue.server.mandrill.model.BaseMandrillRequest
	 * @throws RequestFailedException
	 */
	public BaseMandrillStringResponse performPing(BaseMandrillRequest pingRequest) throws RequestFailedException {
		BaseMandrillStringResponse response = (BaseMandrillStringResponse) request.postRequest(pingRequest, ServiceMethods.Users.PING, null);
		return response;
	}
	
	/**
	 * Return the senders that have tried to use this account, both verified and unverified
	 * @param sendersRequest a populated @see org.netvogue.server.mandrill.model.BaseMandrillRequest
	 * @throws RequestFailedException
	 */
	public UsersSendersResponse getSenders(BaseMandrillRequest sendersRequest) throws RequestFailedException {
		UsersSendersResponse response = new UsersSendersResponse();
		response.setList(((BaseMandrillAnonymousListResponse<MandrillSender>) request.postRequest(sendersRequest, ServiceMethods.Users.SENDERS, UsersSendersResponse.class, usersListReference)).getList());
		return response;
	}
	
	/**
	 * Disable a sender from being able to send
	 * @param disableRequest a populated @see org.netvogue.server.mandrill.model.MandrillRequestWithEmail
	 * @throws RequestFailedException
	 */
	public DisableResponse disableSender(MandrillRequestWithDomain disableRequest) throws RequestFailedException {
		return (DisableResponse) request.postRequest(disableRequest, ServiceMethods.Users.DISABLE_SENDER, DisableResponse.class);
	}
	
	/**
	 * Send an email to the given address to verify that it is an accepted sender for your Mandrill account
	 * @param verifyRequest a populated @see org.netvogue.server.mandrill.model.MandrillRequestWithEmail
	 * @throws RequestFailedException
	 */
	public VerifyResponse verifySender(MandrillRequestWithEmail verifyRequest) throws RequestFailedException {
		return (VerifyResponse)request.postRequest(verifyRequest, ServiceMethods.Users.VERIFY_SENDER, VerifyResponse.class);
	}

	public void setRequest(MandrillRESTRequest request) {
		this.request = request;
	}

	public TypeReference<List<MandrillSender>> getUsersListReference() {
		return usersListReference;
	}
	
}
