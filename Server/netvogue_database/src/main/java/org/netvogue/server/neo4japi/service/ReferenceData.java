package org.netvogue.server.neo4japi.service;

import org.netvogue.server.neo4japi.domain.User;
import org.springframework.data.neo4j.annotation.MapResult;
import org.springframework.data.neo4j.annotation.ResultColumn;

@MapResult
public interface ReferenceData {

	@ResultColumn("references")
	User getReferences();
	
	@ResultColumn("mutualfriends")
	int getMutualfriends();
}
