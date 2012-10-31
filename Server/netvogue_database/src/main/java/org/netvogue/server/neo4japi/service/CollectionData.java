package org.netvogue.server.neo4japi.service;

import org.netvogue.server.neo4japi.domain.Collection;
import org.springframework.data.neo4j.annotation.MapResult;
import org.springframework.data.neo4j.annotation.ResultColumn;

@MapResult
public interface CollectionData {

	@ResultColumn("collection")
	Collection getCollection();
	
	@ResultColumn("username")
	String getUsername();
	
	@ResultColumn("name")
	String getName();
}
