package org.netvogue.server.neo4japi.service;

import org.netvogue.server.neo4japi.domain.Style;
import org.springframework.data.neo4j.annotation.MapResult;
import org.springframework.data.neo4j.annotation.ResultColumn;

@MapResult
public interface StyleData {

	@ResultColumn("styles")
	Style getStyle();
	
	@ResultColumn("username")
	String getUsername();
	
	@ResultColumn("name")
	String getName();
	
}
