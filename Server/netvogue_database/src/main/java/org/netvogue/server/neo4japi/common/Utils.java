package org.netvogue.server.neo4japi.common;

public class Utils {

	public static String SerializeQueryParam(String queryParam) {
		queryParam = "\"" + queryParam + "\"";
		return queryParam;
	}
	
	public static String SerializeQueryParamForSearch(String queryParam) {
		queryParam = queryParam.replaceAll(" ", "*");
		queryParam = "(?i).*" + queryParam + ".*";
		return queryParam;
	}
}
