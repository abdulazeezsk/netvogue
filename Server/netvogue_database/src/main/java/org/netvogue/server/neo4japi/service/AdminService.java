package org.netvogue.server.neo4japi.service;

import org.netvogue.server.common.ResultStatus;

public interface AdminService {

	ResultStatus deactivateUserByUserName(String userName) throws Exception;

	ResultStatus deactivateUserByEmail(String email) throws Exception;
	
	ResultStatus activateUserByUserName(String userName) throws Exception;
	
	ResultStatus activateUserByEmail(String email) throws Exception;

}
