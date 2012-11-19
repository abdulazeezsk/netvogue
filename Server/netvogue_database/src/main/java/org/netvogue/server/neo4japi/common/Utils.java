package org.netvogue.server.neo4japi.common;

import java.util.Set;

public class Utils {

	public static String SerializeQueryParam(String queryParam) {
		queryParam = "\"" + queryParam + "\"";
		return queryParam;
	}
	
	public static String SerializeQueryParamWithSpaces(String columnname, String queryParam) {
		String response = "";
		String[] allvalues = queryParam.split(" ");
		for(int i=0; i < allvalues.length; i++) {
			response += (columnname + ":*" + allvalues[i]) + "*";
			if((i + 1) < allvalues.length) {
				response += " AND ";
			}
		}
		return response;
	}
	
	public static String SerializeQueryParamWithSpacesNoPattern(String columnname, String queryParam) {
		String response = "";
		String[] allvalues = queryParam.split(" ");
		for(int i=0; i < allvalues.length; i++) {
			response += (columnname + ":" + allvalues[i]);
			if((i + 1) < allvalues.length) {
				response += " AND ";
			}
		}
		return response;
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
		if(null == propertyParam || propertyParam.isEmpty()) {
			propertyParam = ".*";
		} else {
			propertyParam = propertyParam.replaceAll(" ", "*");
			propertyParam = "(?i).*" + propertyParam + ".*";
		}
		return propertyParam;
	}
}
