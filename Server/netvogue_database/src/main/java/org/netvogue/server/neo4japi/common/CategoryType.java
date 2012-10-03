package org.netvogue.server.neo4japi.common;

public enum CategoryType {
	APPAREL("APPAREL"),
	SHOES("SHOES"),
   	HANDBAGS("HANDBAGS"),
   	WATCHES("WATCHES"),
   	OTHERS("OTHERS");
   	
   	String desc;
   	StyleSize size;
	CategoryType(String desc) {
		this.desc 	= desc;
	}
	
	public String getDesc() {
		return desc;
	}
	
}
