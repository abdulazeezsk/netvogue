package org.netvogue.server.neo4japi.service;

import org.netvogue.server.neo4japi.domain.Stylesheet;
import org.springframework.data.neo4j.annotation.MapResult;
import org.springframework.data.neo4j.annotation.ResultColumn;

@MapResult
public interface StylesheetData {

	@ResultColumn("stylesheet")
	Stylesheet getStylesheet();
	
	@ResultColumn("name")
	String getName();
	
}
