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
public class Gallery {
	@GraphId
	Long nodeId;
	
	@Indexed(indexName="galleryid", unique = true)
	String galleryid;
	
	@Indexed(indexName="galleryname", indexType=IndexType.FULLTEXT)
	String galleryname;
	
	String description;
	
	String profilePicLink;
	
	Date createdDate = new Date();
	
	@RelatedTo(type="GALLERY", direction=Direction.INCOMING)
	User	createdBy;
	
	@RelatedTo(type="PHOTO", direction=Direction.OUTGOING)
	@Fetch Set<Photo>	photosAdded =  new HashSet<Photo>();
	
	public Gallery() {
		
	}
	
	public Gallery(String name, User createdByTemp) {
		galleryname = name;
		createdBy	= createdByTemp;
		galleryid = UUID.randomUUID().toString();
		profilePicLink = "http://placehold.it/220x150";
	}

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public String getGalleryid() {
		return galleryid;
	}

	public void setGalleryid(String galleryid) {
		this.galleryid = galleryid;
	}

	public String getGalleryname() {
		return galleryname;
	}

	public void setGalleryname(String galleryname) {
		this.galleryname = galleryname;
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

	public Date getCreatedDate(Date createdDate) {
		return this.createdDate;
	}
	
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public User getUser() {
		return createdBy;
	}

	public void setUser(User user) {
		this.createdBy = user;
	}
	
	public Set<Photo> getPhotosAdded() {
		return photosAdded;
	}
	
	public void addPhotos(Photo newPhoto) {
		System.out.println("No:of photos of gallery " + galleryname + " in gallery" + photosAdded.size());
		photosAdded.add(newPhoto);
		System.out.println("Photo with id" + newPhoto.getPhotouniqueid() + " added successfully");
		System.out.println("No:of photos in gallery" + photosAdded.size());
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
