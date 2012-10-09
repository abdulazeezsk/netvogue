package org.netvogue.server.webmvc.domain;

import java.util.Set;

public class Notifications extends CommonResponse{
	Integer unreadnotifications;
	Set<Notification> notifications;

	public Integer getUnreadnotifications() {
		return unreadnotifications;
	}

	public void setUnreadnotifications(Integer unreadnotifications) {
		this.unreadnotifications = unreadnotifications;
	}

	public Set<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(Set<Notification> notifications) {
		this.notifications = notifications;
	}
}
