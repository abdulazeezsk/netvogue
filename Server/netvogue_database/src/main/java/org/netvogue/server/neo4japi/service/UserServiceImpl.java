package org.netvogue.server.neo4japi.service;

import org.netvogue.server.neo4japi.common.ResultStatus;
import org.netvogue.server.neo4japi.domain.User;
import org.netvogue.server.neo4japi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;

public class UserServiceImpl implements UserService{
	@Autowired Neo4jTemplate		neo4jTemplate;
	@Autowired UserRepository		userRepo;
	
	public ResultStatus SaveUser(User user, String error){
		try {
			//New Categories node will be created an relationship will also be added for this.
			//Saving it through Template instead of boutiquerepo so that categories node can also be saved
			neo4jTemplate.save(user);
			System.out.println("Updated User Successfully");
			return ResultStatus.SUCCESS;
		} catch(Exception e) {
			System.out.println("There was an error for" + user.getEmail() + " - " + e.toString());
			error = e.toString();
			return ResultStatus.FAILURE;
		}
	}
	
	public ResultStatus ValidateEmailAndId(String email, Long id) {
		if(email.isEmpty()) {
			return ResultStatus.FAILURE;
		}
		User b = userRepo.findByemailAndId(email, id);
		if(null == b) {
			return ResultStatus.SUCCESS;
		}
		return ResultStatus.USER_EXISTS;
	}
}
