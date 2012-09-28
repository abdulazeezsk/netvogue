package org.netvogue.server.neo4japi.domain;

import java.util.Date;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.support.index.IndexType;

@NodeEntity
public class EditorialPhoto {

	@GraphId
	Long nodeId;
	
	@Indexed(indexName="editorialphotouniqueid", unique = true)
	String editorialphotouniqueid;
	
	@Indexed(indexName="editorialphotoname", indexType=IndexType.FULLTEXT)
	String editorialphotoname;
	
	String description;
	
	String originalImageURL;
	
	Date createdDate = new Date();

	public EditorialPhoto() {
		
	}
	
	public EditorialPhoto(String photouniqueid) {
		this.editorialphotouniqueid = photouniqueid;
		this.editorialphotoname 	= "UNTITLED";
	}
	
	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public String getEditorialphotouniqueid() {
		return editorialphotouniqueid;
	}

	public void setEditorialphotouniqueid(String editorialphotouniqueid) {
		this.editorialphotouniqueid = editorialphotouniqueid;
	}

	public String getEditorialphotoname() {
		return editorialphotoname;
	}

	public void setEditorialphotoname(String editorialphotoname) {
		this.editorialphotoname = editorialphotoname;
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