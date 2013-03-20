package org.netvogue.server.neo4japi.repository;

import java.util.List;

import org.netvogue.server.neo4japi.domain.User;
import org.netvogue.server.neo4japi.mapper.UserInfoMapper;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface AdminRepository extends GraphRepository<User> {

	@Query("START n=node:search(username={0}) SET n.accountEnabled=false "
			+ "RETURN n.accountEnabled")
	boolean deactivateUserByUserName(String username);

	@Query("START n=node:email(email={0}) SET n.accountEnabled=false "
			+ "RETURN n.accountEnabled")
	boolean deactivateUserByEmail(String email);

	@Query("START n=node:search(username={0}) SET n.accountEnabled=true "
			+ "RETURN n.accountEnabled")
	boolean activateUserByUserName(String username);

	@Query("START n=node:email(email={0}) SET n.accountEnabled=true "
			+ "RETURN n.accountEnabled")
	boolean activateUserByEmail(String email);

	/*@Query("START n=node:search('username:*') RETURN n.name as name, n.username as username, n.email as email")
	List<UserInfoMapper> getAllUsers(String regex);*/

}
