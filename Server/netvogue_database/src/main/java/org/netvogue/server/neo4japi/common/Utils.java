package org.netvogue.server.neo4japi.common;

import java.util.Set;

public class Utils {

	public static String SerializeQueryParam(String queryParam) {
		queryParam = "\"" + queryParam + "\"";
		return queryParam;
	}
	
	public static String SerializeQueryParamForSet(String columnname, Set<String> categories) {
		String cat = "" ;
		if(0 == categories.size()) {
			cat = columnname + ":*"; 
			return cat;
		}
		int index =1;
		for(String category: categories) {
			cat += (columnname + ":" + category) ;
			if(index++ < categories.size()) {
				cat += " OR ";
			}
		}
		System.out.println("After serialization is " + cat);
		return cat;
	}
	
	public static String SerializePropertyParamForSearch(String propertyParam) {
		propertyParam = propertyParam.replaceAll(" ", "*");
		propertyParam = "(?i).*" + propertyParam + ".*";
		return propertyParam;
	}
}
