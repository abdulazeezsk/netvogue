package org.netvogue.server.neo4japi.domain;

import java.util.Date;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.support.index.IndexType;

@NodeEntity
public class CollectionPhoto {

	@GraphId
	Long nodeId;
	
	@Indexed(indexName="collectionphotouniqueid", unique = true)
	String collectionphotouniqueid;
	
	@Indexed(indexName="collectionphotoname", indexType=IndexType.FULLTEXT)
	String collectionphotoname;
	
	String description;
	
	String originalImageURL;
	
	Date createdDate = new Date();

	public CollectionPhoto() {
		
	}
	
	public CollectionPhoto(String photouniqueid) {
		this.collectionphotouniqueid = photouniqueid;
		//this.collectionphotoname = "UNTITLED";
	}
	
	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public String getCollectionphotouniqueid() {
		return collectionphotouniqueid;
	}

	public void setCollectionphotouniqueid(String collectionphotouniqueid) {
		this.collectionphotouniqueid = collectionphotouniqueid;
	}

	public String getCollectionphotoname() {
		return collectionphotoname;
	}

	public void setCollectionphotoname(String collectionphotoname) {
		this.collectionphotoname = collectionphotoname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOriginalImageURL() {
		return originalImageURL;
	}

	public void setOriginalImageURL(String profilePicLink) {
		this.originalImageURL = profilePicLink;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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
