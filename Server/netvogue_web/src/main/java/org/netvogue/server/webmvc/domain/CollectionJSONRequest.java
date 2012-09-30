package org.netvogue.server.webmvc.domain;

public class CollectionJSONRequest {
	String id;
	String seasonname;
	String desc;
	String category;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getSeasonname() {
		return seasonname;
	}
	public void setSeasonname(String seasonname) {
		this.seasonname = seasonname;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
}
