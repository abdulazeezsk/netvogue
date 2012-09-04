package org.netvogue.server.neo4japi.domain;

//Project specific imports
import org.netvogue.server.neo4japi.common.*;

//Spring specific imports
import org.springframework.data.neo4j.annotation.*;

//Generic imports
import java.util.*;

@NodeEntity
public class Notification {

	@GraphId
	Long nodeId;
	
	NotificationType 	notificationType;
		
	String				desc;
	
	Date				notificationDate;
	
	//To --Brand/Boutique
	//From -- Brand/Boutique
	
	//Add relation to boutique and brand and collections
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Notification notification = (Notification) o;
        if (nodeId == null) return super.equals(o);
        return nodeId.equals(notification.nodeId);

    }

    @Override
    public int hashCode() {
        return nodeId != null ? nodeId.hashCode() : super.hashCode();
    }
}
