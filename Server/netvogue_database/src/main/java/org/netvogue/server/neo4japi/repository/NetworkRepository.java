package org.netvogue.server.neo4japi.repository;

import org.netvogue.server.neo4japi.domain.Network;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface NetworkRepository extends GraphRepository<Network> {

	@Query( "START n=node:search(username={0}) MATCH n-[r:NETWORK]-() " +
			"WHERE r.status IN ['CONFIRMED', 'PENDING', 'BLOCKED'] RETURN r")
	Iterable<Network> getNetworks(String username);
	
	@Query( "START n=node:search(username={0}) MATCH n-[r:NETWORK]-() WHERE f.username={1} " +
			"RETURN r")
	Network getNetwork(String username1, String username2);
	
	//Creating, editing and deleting network
	
	@Query( "START n1=node:search(username={0}),n2=node:search(username={1}) " +
			"CREATE UNIQUE n1-[r:NETWORK{status:'PENDING'}]-n2 RETURN r ")
	Network CreateNetwork(String usernameBy, String usernameTo);
	
	@Query( "START n=node:search(username={0}) MATCH n-[r:NETWORK|NOTIFICATION]-f WHERE f.username={1} " +
			"SET r.status = 'CONFIRMED'")
	void ConfirmNetwork(String confirmBy, String usernameTo);
	
	@Query( "START n=node:search(username={0}) MATCH n-[r:NOTIFICATION]-f WHERE f.username={0} " +
			"SET r.status = 'DISCARD'")
	void DiscardNetwork(String discardBy, String usernameTo); //You can confirm afterwards
	
	@Query( "START n=node:search(username={0}) MATCH n-[r:NETWORK]-f WHERE f.username={0} " +
			"SET r.status = 'BREAKUP', r.breakupBy ={0} ")
	void DeleteNetwork(String DeleteBy, String usernameTo); //Fresh request must be sent
	
	@Query( "START n=node:search(username={0}) MATCH n-[r:NETWORK]-f WHERE f.username={0} " +
			"SET r.status = 'BLOCK', r.breakupBy ={0} ")
	void BlockNetwork(String BlockBy, String usernameTo); //Cant any send more requests
}
