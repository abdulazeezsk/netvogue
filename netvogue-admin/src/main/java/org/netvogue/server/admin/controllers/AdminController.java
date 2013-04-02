package org.netvogue.server.admin.controllers;

import java.util.List;
import java.util.Map;

import org.netvogue.server.admin.domain.JsonResponse;
import org.netvogue.server.admin.domain.UserInfo;
import org.netvogue.server.common.ResultStatus;
import org.netvogue.server.neo4japi.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminController {

	private static final Logger logger = LoggerFactory
			.getLogger(AdminController.class);

	@Autowired
	AdminService adminService;

	/**
	 * This method will deactivate the boutique/brand by his username
	 */
	
	@RequestMapping(value = "/admin/deactivateUser", method = RequestMethod.PUT)
	public @ResponseBody
	JsonResponse deactivateUser(@RequestBody final UserInfo userinfo) {
		 logger.debug("Method - deactivateUser and user name is:  " +
		 userinfo.getUserName());
		JsonResponse response = null;
		String username = userinfo.getUserName();
		String email = userinfo.getEmail();
		if (null != username && !"".equals(username)) {
			response = deactivateByUserName(username);
		} else if (null != email && !"".equals(email)) {
			response = deactivateUserByEmail(email);
		}

		return response;
	}


	private JsonResponse deactivateByUserName(String username) {
		logger.debug("Method - deactivateUser and user name is:  " + username);
//		System.out.println("username: " + username);
		JsonResponse response = new JsonResponse();
		try {
			if (ResultStatus.SUCCESS == adminService
					.deactivateUserByUserName(username))
				response.setStatus(true);
		} catch (Exception e) {
			response.setError("Error in deactivating user by username: "
					+ e.getMessage());
		}

		return response;
	}

	/**
	 * This method will deactivate the boutique/brand by his email id
	 */


	private JsonResponse deactivateUserByEmail(String emailId) {
		logger.debug("Method - deactivateUser and user email id is:  "
				+ emailId);
//		System.out.println("email: " + emailId);
		JsonResponse response = new JsonResponse();
		try {
			if (ResultStatus.SUCCESS == adminService
					.deactivateUserByEmail(emailId))
				response.setStatus(true);
		} catch (Exception e) {
			response.setError("Error in deactivating user by email: "
					+ e.getMessage());
		}

		return response;
	}
	
	/**
	 * This method will activate the boutique/brand by his username
	 */

	@RequestMapping(value = "/admin/activateUser", method = RequestMethod.PUT)
	public @ResponseBody
	JsonResponse activateUser(@RequestBody final UserInfo userinfo) {
		logger.debug("Method - activateUser and user name is:  " +
				 userinfo.getUserName());
		JsonResponse response = null;
		String username = userinfo.getUserName();
		String email = userinfo.getEmail();
		if (null != username && !"".equals(username)) {
			response = activateByUserName(username);
		} else if (null != email && !"".equals(email)) {
			response = activateUserByEmail(email);
		}
		return response;
	}

	/**
	 * This method will activate the boutique/brand by his username
	 */


	private JsonResponse activateByUserName(String username) {
		logger.debug("Method - activateUser and user name is:  " + username);
//		System.out.println("username: " + username);
		JsonResponse response = new JsonResponse();
		try {
			if (ResultStatus.SUCCESS == adminService
					.activateUserByUserName(username))
				response.setStatus(true);
		} catch (Exception e) {
			response.setError("Error in activating user by username: "
					+ e.getMessage());
		}

		return response;
	}

	/**
	 * This method will activate the boutique/brand by his email id
	 */


	private JsonResponse activateUserByEmail(String emailId) {
		logger.debug("Method - activateUser and user email id is:  " + emailId);
//		System.out.println("email: " + emailId);
		JsonResponse response = new JsonResponse();
		try {
			if (ResultStatus.SUCCESS == adminService
					.activateUserByEmail(emailId))
				response.setStatus(true);
		} catch (Exception e) {
			response.setError("Error in activating user by email: "
					+ e.getMessage());
		}

		return response;
	}

	/**
	 * This method is to show the list of boutiques
	 */

	@RequestMapping(value = "/admin/userlist", method = RequestMethod.GET)
	public @ResponseBody
	List<Map<String, Object>> getBoutiques(
			@RequestParam("userType") String userType) throws Exception {
		logger.info("Testing usertype " + userType);
		List<Map<String, Object>> userList = adminService.getAllUsers(userType);
		return userList;
	}

}
