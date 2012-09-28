package org.netvogue.server.neo4japi.domain;

import java.util.Date;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.support.index.IndexType;

@NodeEntity
public class Photo {

	@GraphId
	Long nodeId;
	
	@Indexed(indexName="photouniqueid", unique = true)
	String photouniqueid;
	
	@Indexed(indexName="photoname", indexType=IndexType.FULLTEXT)
	String photoname;
	
	@Indexed(indexName="seasonname", indexType=IndexType.FULLTEXT)
	String seasonname;
	
	String originalImageURL;
	
	Date createdDate = new Date();

	public Photo() {
		
	}
	
	public Photo(String photouniqueid) {
		this.photouniqueid = photouniqueid;
		this.photoname = "UNTITLED";
		this.seasonname = "UNTITLED SEASON";
	}
	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public String getPhotouniqueid() {
		return photouniqueid;
	}

	public void setPhotouniqueid(String photouniqueid) {
		this.photouniqueid = photouniqueid;
	}

	public String getPhotoname() {
		return photoname;
	}

	public void setPhotoname(String photoname) {
		this.photoname = photoname;
	}

	public String getSeasonname() {
		return seasonname;
	}

	public void setSeasonname(String seasonname) {
		this.seasonname = seasonname;
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
