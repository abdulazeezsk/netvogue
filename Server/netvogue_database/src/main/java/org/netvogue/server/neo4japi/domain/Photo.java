package org.netvogue.server.neo4japi.domain;

import org.neo4j.graphdb.Direction;
import org.netvogue.server.aws.core.Size;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.support.index.IndexType;

@NodeEntity
public class Photo {

	@GraphId
	Long nodeId;
	
	@Indexed(indexName="galleryid", unique = true)
	String photouniqueid;
	
	@Indexed(indexName="name", indexType=IndexType.FULLTEXT)
	String name;
	
	@Indexed(indexName="seasonname", indexType=IndexType.FULLTEXT)
	String seasonname;
	
	String originalImageURL;
	
	long createdDate;

	public Photo() {
		
	}
	
	public Photo(String photouniqueid) {
		this.photouniqueid = photouniqueid;
		
		createdDate = System.currentTimeMillis();
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public long getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(long createdDate) {
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
