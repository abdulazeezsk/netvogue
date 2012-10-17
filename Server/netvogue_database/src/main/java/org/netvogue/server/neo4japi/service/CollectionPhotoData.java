package org.netvogue.server.neo4japi.service;

import org.netvogue.server.neo4japi.domain.CollectionPhoto;
import org.springframework.data.neo4j.annotation.MapResult;
import org.springframework.data.neo4j.annotation.ResultColumn;

@MapResult
public interface CollectionPhotoData {

	@ResultColumn("photo")
	CollectionPhoto getCollectionPhoto();
	
	@ResultColumn("name")
	String getName();
}
