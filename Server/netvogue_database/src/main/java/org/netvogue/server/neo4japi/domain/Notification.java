package org.netvogue.server.neo4japi.domain;

//Project specific imports
import org.netvogue.server.common.*;

//Spring specific imports
import org.springframework.data.neo4j.annotation.*;

//Generic imports
import java.util.*;

@NodeEntity
public class Notification {

	@GraphId
	Long nodeId;
	
	@Indexed(indexName="notificationid", unique = true)
	String 				notificationid;
	
	NotificationType 	notificationType;
		
	String				desc;
	
	Date				notificationDate = new Date();
	
	boolean				isRead = false;
	
	@Fetch User			otherUser;
	
	NetworkStatus		status;
	
	//User				notificationsFor; Adding notifications in user entity
	public Notification() {
		
	}
	
	public Notification(User otheruser, NotificationType type) {
		this.otherUser = otheruser;
		this.notificationType = type;
		this.status = NetworkStatus.PENDING;
		this.notificationid = UUID.randomUUID().toString();
	}
	
	public Notification(User otheruser, NotificationType type, NetworkStatus status) {
		this.otherUser = otheruser;
		this.notificationType = type;
		this.status = status;
		this.notificationid = UUID.randomUUID().toString();
	}
	
	public String getNotificationid() {
		return notificationid;
	}

	public void setNotificationid(String notificationid) {
		this.notificationid = notificationid;
	}

	public NotificationType getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(NotificationType notificationType) {
		this.notificationType = notificationType;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Date getNotificationDate() {
		return notificationDate;
	}

	public void setNotificationDate(Date notificationDate) {
		this.notificationDate = notificationDate;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	public User getOtherUser() {
		return otherUser;
	}

	public void setOtherUser(User otherUser) {
		this.otherUser = otherUser;
	}

	public NetworkStatus getStatus() {
		return status;
	}

	public void setStatus(NetworkStatus networkStatus) {
		this.status = networkStatus;
	}

	@Override
    public boolean equals(Object other) {
		if (this == other) 
			return true;
		if (nodeId == null) 
			return false;
		if (! (other instanceof User)) 
			return false;
		return nodeId.equals(((User) other).nodeId);    
	}

    @Override
    public int hashCode() {
    	return nodeId == null ? System.identityHashCode(this) : nodeId.hashCode();
    }
}
