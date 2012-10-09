package org.netvogue.server.neo4japi.service;

import org.netvogue.server.neo4japi.common.ResultStatus;
import org.netvogue.server.neo4japi.domain.Notification;
import org.netvogue.server.neo4japi.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class NotificationServiceImpl implements NotificationService {
	@Autowired NotificationRepository notificationRepo;

	public Iterable<Notification> getUnreadNotifications(String username, String error) {
		if(username.isEmpty()) {
			error = "username is empty";
			return null;
		}
		try {
			return notificationRepo.getUnreadNotifications(username);
		} catch(Exception e) {
			error = e.toString();
			return null;
		}
	}
	
	public Iterable<Notification> getAllNotifications(String username, String error) {
		if(username.isEmpty()) {
			error = "username is empty";
			return null;
		}
		try {
			return notificationRepo.getAllNotifications(username);
		} catch(Exception e) {
			error = e.toString();
			return null;
		}
	}
	
	public ResultStatus markNotificationRead(String notificationid, String error) {
		if(notificationid.isEmpty()) {
			error = "notificationid is empty";
			return null;
		}
		try {
			notificationRepo.markNotificationRead(notificationid);
			return ResultStatus.SUCCESS;
		} catch(Exception e) {
			error = e.toString();
			return ResultStatus.FAILURE;
		}
	}
	
	public ResultStatus discardNetwork(String notificationid, String error) {
		if(notificationid.isEmpty()) {
			error = "notificationid is empty";
			return null;
		}
		try {
			notificationRepo.DiscardNetwork(notificationid);
			return ResultStatus.SUCCESS;
		} catch(Exception e) {
			error = e.toString();
			return ResultStatus.FAILURE;
		}
	}
}
