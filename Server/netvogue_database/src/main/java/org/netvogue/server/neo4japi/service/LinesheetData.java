package org.netvogue.server.neo4japi.service;

import org.netvogue.server.neo4japi.domain.Linesheet;
import org.springframework.data.neo4j.annotation.MapResult;
import org.springframework.data.neo4j.annotation.ResultColumn;

@MapResult
public interface LinesheetData {

	@ResultColumn("linesheet")
	Linesheet getLinesheet();
	
	@ResultColumn("name")
	String getName();
}
