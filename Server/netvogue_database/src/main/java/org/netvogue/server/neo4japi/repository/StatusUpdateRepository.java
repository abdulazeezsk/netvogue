package org.netvogue.server.neo4japi.repository;

import java.util.Date;

import org.netvogue.server.neo4japi.domain.StatusUpdate;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface StatusUpdateRepository extends GraphRepository<StatusUpdate> {

	@Query("START n=node:search(username={0}) MATCH n-[r:STATUS]-oldsu-[:NEXT*0..]-su return su")
	Iterable<StatusUpdate> getMyStatusUpdates(String username);
	
	@Query("START n=node:search(username={0}) MATCH n-[r:NETWORK]-[r:STATUS]-oldsu-[:NEXT*0..]-su " +
			"return su ORDER BY su.postedDate DESC")
	Iterable<StatusUpdate> getAllStatusUpdates(String username);
	
	@Query(	"START n=node:search(username={0}) MATCH n-[r?:STATUS]->oldsu DELETE r " +
			"WITH n,oldsu " +
			"CREATE n-[:STATUS]->(newsu {statusid:{1},statusUpdate:{2},postedDate:{3}})-[:NEXT]->oldsu")
	void newStatusUpdate(String username, String statusid, String statusupdate, Date createddate);
	
	@Query("START n=node:statusid(statusid={0}) SET n.statusUpdate = {1}")
	void editStatusUpdate(String id, String message);
	
	@Query("START n=node:statusid(statusid={0}) MATCH n<-[:STATUS]-u, n<-[:NEXT]-previous, n-[:NEXT]->next " +
			"")
	void deleteStatusUpdate(String id);
	
}
