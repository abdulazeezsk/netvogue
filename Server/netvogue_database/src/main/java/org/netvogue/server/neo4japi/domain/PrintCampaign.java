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
public class PrintCampaign {
	@GraphId
	Long nodeId;
	
	@Indexed(indexName="printcampaignid", unique = true)
	String printcampaignid;
	
	@Indexed(indexName="printcampaignname", indexType=IndexType.FULLTEXT)
	String printcampaignname;
	
	String description;
	
	String profilePicLink;
	
	Date createdDate = new Date();
	
	@RelatedTo(type="PRINTCAMPAIGN", direction=Direction.INCOMING)
	User	createdBy;
	
	@RelatedTo(type="PRINTCAMPAIGNPHOTO", direction=Direction.OUTGOING)
	@Fetch Set<PrintCampaignPhoto>	photosAdded =  new HashSet<PrintCampaignPhoto>();
	
	public PrintCampaign() {
		
	}
	
	public PrintCampaign(String name, String desc, User createdByTemp) {
		printcampaignname 	= name;
		description			= desc;
		createdBy			= createdByTemp;
		printcampaignid 	= UUID.randomUUID().toString();
		profilePicLink 		= "http://placehold.it/231x306";
	}

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}
	
	public String getPrintcampaignid() {
		return printcampaignid;
	}

	public void setPrintcampaignid(String printcampaignid) {
		this.printcampaignid = printcampaignid;
	}

	public String getPrintcampaignname() {
		return printcampaignname;
	}

	public void setPrintcampaignname(String printcampaignname) {
		this.printcampaignname = printcampaignname;
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

	public Set<PrintCampaignPhoto> getPhotosAdded() {
		return photosAdded;
	}
	
	public void addPhotos(PrintCampaignPhoto newPhoto) {
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
