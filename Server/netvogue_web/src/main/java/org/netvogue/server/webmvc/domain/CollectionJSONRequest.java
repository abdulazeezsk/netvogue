package org.netvogue.server.webmvc.domain;

public class CollectionJSONRequest {
	String id;
	String seasonname;
	String desc;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSesonName() {
		return seasonname;
	}
	public void setSeasonName(String name) {
		this.seasonname = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
