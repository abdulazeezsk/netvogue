package org.netvogue.server.neo4japi.common;

public class Utils {

	public static String SerializeQueryParam(String queryParam) {
		queryParam = "\"" + queryParam + "\"";
		return queryParam;
	}
}
