package org.netvogue.server.admin.controllers;

import org.netvogue.server.admin.domain.JsonResponse;
import org.netvogue.server.common.ResultStatus;
import org.netvogue.server.neo4japi.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	JsonResponse deactivateByUserName(@RequestBody final String username) {
		logger.debug("Method - deactivateUser and user name is:  " + username);
		System.out.println("userinfo.getUsername(): " + username);
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

	@RequestMapping(value = "/admin/deactivateUserByEmail", method = RequestMethod.PUT)
	public @ResponseBody
	JsonResponse deactivateUserByEmail(@RequestBody final String emailId) {
		logger.debug("Method - deactivateUser and user email id is:  "
				+ emailId);
		System.out.println("userinfo.getUsername(): " + emailId);
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
	JsonResponse activateByUserName(@RequestBody final String username) {
		logger.debug("Method - activateUser and user name is:  " + username);
		System.out.println("userinfo.getUsername(): " + username);
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

	@RequestMapping(value = "/admin/activateUserByEmail", method = RequestMethod.PUT)
	public @ResponseBody
	JsonResponse activateUserByEmail(@RequestBody final String emailId) {
		logger.debug("Method - activateUser and user email id is:  " + emailId);
		System.out.println("userinfo.getUsername(): " + emailId);
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

}
