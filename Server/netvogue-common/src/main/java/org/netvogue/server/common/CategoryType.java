package org.netvogue.server.common;

public enum CategoryType {
	APPAREL("APPAREL"),
	SHOES("SHOES"),
   	HANDBAGS("HANDBAGS"),
   	WATCHES("WATCHES"),
   	JEWELLERY("JEWELLERY"),
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
