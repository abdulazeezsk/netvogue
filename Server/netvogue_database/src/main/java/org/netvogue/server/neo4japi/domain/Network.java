package org.netvogue.server.neo4japi.domain;

//Project specific
import org.netvogue.server.neo4japi.common.*;

//Spring specific imports
import org.springframework.data.neo4j.annotation.*;
import org.springframework.data.neo4j.support.index.*;

//Generic
import java.util.*;

//This relationship will be created even if there is pending request
@RelationshipEntity(type="NETWORK")
public class Network {

	@GraphId
	Long Id;
	
	@Indexed(indexName="networkstatus", indexType=IndexType.FULLTEXT)
	NetworkStatus status;
	
	/*@RelationshipType
	RelationType relationType;*/
	
	Date createdDate = new Date();
	Date acceptedDate; //This should be created to new when Network is created successfully.i.e.request is accepted
	
	/*@StartNode
	Boutique boutique;
	@EndNode
	Brand 	brand;*/
	
	@StartNode
	User 	requestBy;
	@EndNode
	User 	requestTo;
	
	String breakupby; //In case if there is a breakup
	
	public Network() {
		
	}
	
	public Network(User requestby, User requestto) {
		this.requestBy = requestby;
		this.requestTo = requestto;
		status = NetworkStatus.PENDING;
	}
	
	
	public NetworkStatus getStatus() {
		return status;
	}

	public void setStatus(NetworkStatus status) {
		if(status == NetworkStatus.CONFIRMED) {
			acceptedDate = new Date();
		}
		this.status = status;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getAcceptedDate() {
		return acceptedDate;
	}

	public void setAcceptedDate(Date acceptedDate) {
		this.acceptedDate = acceptedDate;
	}

	public User getRequestBy() {
		return requestBy;
	}

	public void setRequestBy(User requestBy) {
		this.requestBy = requestBy;
	}

	public User getRequestTo() {
		return requestTo;
	}

	public void setRequestTo(User requestTo) {
		this.requestTo = requestTo;
	}

	public String getBreakupby() {
		return breakupby;
	}

	public void setBreakupby(String breakupby) {
		this.breakupby = breakupby;
	}

	@Override
    public boolean equals(Object other) {
		if (this == other) 
			return true;
		if (Id == null) 
			return false;
		if (! (other instanceof User)) 
			return false;
		return Id.equals(((User) other).nodeId);    
	}

    @Override
    public int hashCode() {
    	return Id == null ? System.identityHashCode(this) : Id.hashCode();
    }
}
