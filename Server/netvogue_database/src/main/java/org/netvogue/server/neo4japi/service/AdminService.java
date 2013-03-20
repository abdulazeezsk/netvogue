package org.netvogue.server.neo4japi.service;

import java.util.List;
import java.util.Map;

import org.netvogue.server.common.ResultStatus;

public interface AdminService {

	ResultStatus deactivateUserByUserName(String userName) throws Exception;

	ResultStatus deactivateUserByEmail(String email) throws Exception;

	ResultStatus activateUserByUserName(String userName) throws Exception;

	ResultStatus activateUserByEmail(String email) throws Exception;

	List<Map<String, Object>> getAllUsers(String userType) throws Exception;

}
