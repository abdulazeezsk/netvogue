package org.netvogue.server.neo4japi.service;

import org.netvogue.server.common.ResultStatus;
import org.netvogue.server.neo4japi.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminRepository adminRepo;

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

}
