package org.netvogue.server.neo4japi.repository;

import org.netvogue.server.neo4japi.domain.Notification;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface NotificationRepository extends GraphRepository<Notification> {

	@Query("START n=node:search(username={0}) MATCH n-[:NOTIFICATION]->nf " +
			"WHERE nf.isRead = false RETURN nf ORDER BY nf.notificationDate DESC" )
	Iterable<Notification> getUnreadNotifications(String username);
	
	@Query("START n=node:search(username={0}) MATCH n-[:NOTIFICATION]->nf " +
			"RETURN nf ORDER BY nf.notificationDate DESC")
	Iterable<Notification> getAllNotifications(String username);
	
	@Query("START n=node:notificationid(notificationid={0}) SET n.isRead=true")
	void markNotificationRead(String notificationid);
	
	@Query( "START n=node:notificationid(notificationid={0}) SET r.status = 'DISCARD'")
	void DiscardNetwork(String id); //You can confirm afterwards
}
