package org.netvogue.server.neo4japi.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.netvogue.server.common.ResultStatus;
import org.netvogue.server.neo4japi.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.data.neo4j.support.Neo4jTemplate;

public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminRepository adminRepo;

	@Autowired
	Neo4jTemplate neo4jTemplate;

	public ResultStatus deactivateUserByUserName(String username) {
		System.out.println("username: " + username);

		boolean accountEnabled = adminRepo.deactivateUserByUserName(username);
		return (accountEnabled != true) ? ResultStatus.SUCCESS
				: ResultStatus.FAILURE;

	}

	public ResultStatus deactivateUserByEmail(String email) {
		System.out.println("email: " + email);

		boolean accountEnabled = adminRepo.deactivateUserByEmail(email);
		return (accountEnabled != true) ? ResultStatus.SUCCESS
				: ResultStatus.FAILURE;

	}

	public ResultStatus activateUserByUserName(String username) {
		System.out.println("username: " + username);

		boolean accountEnabled = adminRepo.activateUserByUserName(username);
		return (accountEnabled == true) ? ResultStatus.SUCCESS
				: ResultStatus.FAILURE;

	}

	public ResultStatus activateUserByEmail(String email) {
		System.out.println("email: " + email);

		boolean accountEnabled = adminRepo.activateUserByEmail(email);
		return (accountEnabled == true) ? ResultStatus.SUCCESS
				: ResultStatus.FAILURE;

	}

	public List<Map<String, Object>> getAllUsers(String userType) {

		List<Map<String, Object>> userList = new ArrayList<Map<String, Object>>();
		String query = null;
		if (null != userType && !"".equals(userType))
			query = "START n=node:search('username:*') WHERE n.userType = '"
					+ userType
					+ "' RETURN n.name as name, n.username as username, n.email as email";
		else
			query = "START n=node:search('username:*') RETURN n.name as name, n.username as username, n.email as email";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "name");
		map.put("username", "username");
		map.put("email", "email");
		Result<Map<String, Object>> result = neo4jTemplate.query(query, map);
		Iterator<Map<String, Object>> it = result.iterator();
		while (null != it && it.hasNext()) {
			Map<String, Object> userMap = it.next();
			/*
			 * for (Map.Entry<String, Object> entry : userMap.entrySet()) {
			 * System.out.println(entry.getKey() + "/" + entry.getValue()); }
			 */
//			System.out.println(userMap);
			userList.add(userMap);
		}
		return userList;
	}
}
