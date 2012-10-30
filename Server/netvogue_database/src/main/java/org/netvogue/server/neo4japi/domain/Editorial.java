package org.netvogue.server.neo4japi.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.support.index.IndexType;

@NodeEntity
public class Editorial {
	@GraphId
	Long nodeId;
	
	@Indexed(indexName="editorialid", unique = true)
	String editorialid;
	
	@Indexed(indexName="editorialname", indexType=IndexType.FULLTEXT)
	String editorialname;
	
	String description;
	
	String profilePicLink;
	
	Date createdDate = new Date();
	
	@RelatedTo(type="EDITORIAL", direction=Direction.INCOMING)
	User	createdBy;
	
	@RelatedTo(type="EDITORIALPHOTO", direction=Direction.OUTGOING)
	Set<EditorialPhoto>	photosAdded =  new HashSet<EditorialPhoto>();
	
	public Editorial() {
		
	}
	
	public Editorial(String name, String desc, User createdByTemp) {
		editorialname 	= name;
		description   	= desc;
		createdBy		= createdByTemp;
		editorialid 	= UUID.randomUUID().toString();
	}

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}
	
	public String getEditorialid() {
		return editorialid;
	}

	public void setEditorialid(String editorialid) {
		this.editorialid = editorialid;
	}

	public String getEditorialname() {
		return editorialname;
	}

	public void setEditorialname(String editorialname) {
		this.editorialname = editorialname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProfilePicLink() {
		return profilePicLink;
	}

	public void setProfilePicLink(String profilePicLink) {
		this.profilePicLink = profilePicLink;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public Set<EditorialPhoto> getPhotosAdded() {
		return photosAdded;
	}
	
	public void addPhotos(EditorialPhoto newPhoto) {
		if(0 == photosAdded.size()) {
			setProfilePicLink(newPhoto.getEditorialphotouniqueid());
		}
		photosAdded.add(newPhoto);
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
