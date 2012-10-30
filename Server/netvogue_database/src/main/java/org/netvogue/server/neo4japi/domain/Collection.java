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
public class Collection {
	@GraphId
	Long nodeId;
	
	@Indexed(indexName="collectionid", unique = true)
	String collectionid;
	
	@Indexed(indexName="collectionname", indexType=IndexType.FULLTEXT)
	String collectionname;
	
	@Indexed(indexName="collectionseasonname", indexType=IndexType.FULLTEXT)
	String collectionseasonname;
	
	String description;
	
	String profilePicLink;
	
	Date createdDate = new Date();
	
	@RelatedTo(type="Collection_Category", direction=Direction.INCOMING)
	@Fetch Category productcategory;
	
	@RelatedTo(type="COLLECTION", direction=Direction.INCOMING)
	User	createdBy;
	
	@RelatedTo(type="COLLECTIONPHOTO", direction=Direction.OUTGOING)
	@Fetch Set<CollectionPhoto>	photosAdded =  new HashSet<CollectionPhoto>();
	
	public Collection() {
		
	}
	
	public Collection(String seasonname, String description, User createdByTemp) {
		collectionseasonname = seasonname;
		createdBy	= createdByTemp;
		collectionid = UUID.randomUUID().toString();
	}

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}
	
	public String getCollectionid() {
		return collectionid;
	}

	public void setCollectionid(String collectionid) {
		this.collectionid = collectionid;
	}

	public String getCollectionname() {
		return collectionname;
	}

	public void setCollectionname(String collectionname) {
		this.collectionname = collectionname;
	}

	public String getCollectionseasonname() {
		return collectionseasonname;
	}

	public void setCollectionseasonname(String collectionseasonname) {
		this.collectionseasonname = collectionseasonname;
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

	public Category getProductcategory() {
		return productcategory;
	}

	public void setProductcategory(Category productcategory) {
		this.productcategory = productcategory;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public Set<CollectionPhoto> getPhotosAdded() {
		return photosAdded;
	}
	
	public void addPhotos(CollectionPhoto newPhoto) {
		if(0 == photosAdded.size()) {
			setProfilePicLink(newPhoto.getCollectionphotouniqueid());
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
