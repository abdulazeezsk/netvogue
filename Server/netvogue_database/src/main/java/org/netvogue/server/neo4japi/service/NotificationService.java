package org.netvogue.server.neo4japi.service;

import org.netvogue.server.neo4japi.common.ResultStatus;
import org.netvogue.server.neo4japi.domain.Notification;

public interface NotificationService {

	Iterable<Notification> getUnreadNotifications(String username, String error);
	Iterable<Notification> getAllNotifications(String username, String error);
	ResultStatus markNotificationRead(String notificationid, String error);
	ResultStatus discardNetwork(String notificationid, String error);
}
