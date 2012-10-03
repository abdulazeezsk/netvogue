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
public class Stylesheet {
	@GraphId
	Long nodeId;
	
	@Indexed(indexName="stylesheetid", unique = true)
	String stylesheetid;
	
	@Indexed(indexName="stylesheetname", indexType=IndexType.FULLTEXT)
	String stylesheetname;
	
	String profilePicLink;
	
	Date createdDate = new Date();
	
	@RelatedTo(type="Stylesheet_Category", direction=Direction.INCOMING)
	@Fetch Category productcategory;
	
	@RelatedTo(type="STYLESHEET", direction=Direction.INCOMING)
	User	createdBy;
	
	@RelatedTo(type="SS_STYLE", direction=Direction.OUTGOING)
	@Fetch Set<Style>	styles =  new HashSet<Style>();
	
	public Stylesheet() {
		
	}
	
	public Stylesheet(String name, User createdByTemp) {
		stylesheetname = name;
		createdBy	= createdByTemp;
		stylesheetid = UUID.randomUUID().toString();
		profilePicLink = "http://placehold.it/220X320";
	}

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}
	
	public String getStylesheetid() {
		return stylesheetid;
	}

	public void setStylesheetid(String stylesheetid) {
		this.stylesheetid = stylesheetid;
	}

	public String getStylesheetname() {
		return stylesheetname;
	}

	public void setStylesheetname(String stylesheetname) {
		this.stylesheetname = stylesheetname;
	}

	public void setStyles(Set<Style> styles) {
		this.styles = styles;
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

	public Set<Style> getStyles() {
		return styles;
	}
	
	public Style getStyle(String styleid) {
		for(Style style: styles) {
			if(style.getStyleid() == styleid)
				return style;
		}
		return null;
	}
	public void addStyles(Style newStyle) {
		if(0 == styles.size()) {
			setProfilePicLink(newStyle.getProfilePicLink());
		}
		styles.add(newStyle);
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