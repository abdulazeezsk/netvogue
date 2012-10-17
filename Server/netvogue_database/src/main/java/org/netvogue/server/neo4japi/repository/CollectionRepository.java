package org.netvogue.server.neo4japi.repository;

import org.netvogue.server.neo4japi.domain.Collection;
import org.netvogue.server.neo4japi.domain.CollectionPhoto;
import org.netvogue.server.neo4japi.service.CollectionPhotoData;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface CollectionRepository extends GraphRepository<Collection> {

	@Query( "START n=node:collectionid(collectionid={0}) RETURN n")
	Collection getCollection(String collectionid);
	
	@Query("START p = node:collectionid(collectionid={0}) SET p.collectionseasonname = {1}, p.description = {2}")
	void editCollection(String collectionid, String seasonname, String desc);
	
	@Query("START p = node:collectionid(collectionid={0}) SET p.collectionseasonname = {1}")
	void editCollectionSeasonName(String collectionid, String seasonname);
	
	@Query("START p = node:collectionid(collectionid={0}) SET p.description = {1}")
	void editCollectionDesc(String collectionid, String desc);
	
	@Query("START p = node:collectionid(collectionid={0}) SET p.profilePicLink = {1}")
	void setProfilepic(String collectionid, String uniqueid);
	
	@Query("START n=node:collectionid(collectionid={0}) MATCH n-[rels*0..]->p " +
			"FOREACH(rel IN rels: DELETE rel) DELETE p " +
			"WITH n MATCH n<-[r]-() DELETE n, r")
	void deleteCollection(String collectionid);
	
	@Query( "START n=node:collectionid(collectionid={0}) " +
			"MATCH n-[:COLLECTIONPHOTO]->photos RETURN n.name as name, photos ORDER BY photos.createdDate DESC")
	Iterable<CollectionPhotoData> getPhotos(String collectionid);
	
	@Query( "START n=node:collectionid(collectionid={0}) " +
			"MATCH n-[:COLLECTIONPHOTO]->photos WHERE photos.collectionphotoname! =~ {1} " +
			"RETURN n.name as name, photos ORDER BY photos.createdDate DESC")
	Iterable<CollectionPhotoData> searchPhotosByName(String collectionid, String photoname);
	
	@Query("START p = node:collectionphotouniqueid(collectionphotouniqueid={0}) MATCH p-[r]-() DELETE p, r")
	void deletePhoto(String collectionphotouniqueid);
	
	@Query("START p = node:collectionphotouniqueid(collectionphotouniqueid={0}) " +
			"SET p.collectionseasonname = {1}, p.description = {2}")
	void editPhotoInfo(String collectionphotouniqueid, String seasonname, String description);
	
	@Query("START p = node:collectionphotouniqueid(collectionphotouniqueid={0}) SET p.collectionphotoname = {1}")
	void editPhotoName(String collectionphotouniqueid, String photoname);
	
	@Query("START p = node:collectionphotouniqueid(collectionphotouniqueid={0}) SET p.description = {1}")
	void editPhotoDescription(String collectionphotouniqueid, String description);

}
