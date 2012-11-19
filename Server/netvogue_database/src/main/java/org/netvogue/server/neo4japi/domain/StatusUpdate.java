package org.netvogue.server.neo4japi.domain;

//Spring specific imports
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.*;

//Generic
import java.util.*;

@NodeEntity
public class StatusUpdate {

	@GraphId
	Long nodeId;
	
	@Indexed(unique = true)
	String statusid;
	
	String statusUpdate;
	
	Date postedDate = new Date();
	
	@RelatedTo(type="NEXT", direction = Direction.OUTGOING)
	StatusUpdate status;
	
	@RelatedTo(type="STATUS", direction = Direction.INCOMING)
	User addedBy;
	
	boolean isDeleted = false;
	
	public StatusUpdate() {
		
	}
	
	public StatusUpdate(String statusupdate) {
		this.statusUpdate 	= statusupdate;
		statusid			= UUID.randomUUID().toString();
	}

	public String getStatusid() {
		return statusid;
	}

	public void setStatusid(String statusid) {
		this.statusid = statusid;
	}

	public String getStatusUpdate() {
		return statusUpdate;
	}

	public void setStatusUpdate(String statusUpdate) {
		this.statusUpdate = statusUpdate;
	}

	public Date getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}

	public StatusUpdate getStatus() {
		return status;
	}

	public void setStatus(StatusUpdate status) {
		this.status = status;
	}

	public User getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(User addedBy) {
		this.addedBy = addedBy;
	}

	public boolean isDeleted() {
          return isDeleted;
       }

       public void setDeleted(boolean isDeleted) {
           this.isDeleted = isDeleted;
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
