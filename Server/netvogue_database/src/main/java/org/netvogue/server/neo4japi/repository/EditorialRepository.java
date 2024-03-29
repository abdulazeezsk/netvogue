package org.netvogue.server.neo4japi.repository;

import org.netvogue.server.neo4japi.domain.Editorial;
import org.netvogue.server.neo4japi.domain.EditorialPhoto;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface EditorialRepository extends GraphRepository<Editorial> {

	@Query( "START n=node:editorialid(editorialid={0}) RETURN n")
	Editorial getEditorial(String editorialid);
	
	@Query("START p = node:editorialid(editorialid={0}) SET p.editorialname = {1}, p.description = {2}")
	void editEditorial(String editorialid, String name, String desc);
	
	@Query("START p = node:editorialid(editorialid={0}) SET p.editorialname = {1}")
	void editEditorialName(String editorialid, String name);
	
	@Query("START p = node:editorialid(editorialid={0}) SET p.description = {1}")
	void editEditorialDesc(String editorialid, String desc);
	
	@Query("START p = node:editorialid(editorialid={0}) SET p.profilePicLink = {1}")
	void setProfilepic(String editorialid, String uniqueid);
	
	@Query("START n=node:editorialid(editorialid={0}) MATCH n-[rels*1..]->p " +
			"WITH n, rels, p, collect(p.editorialphotouniqueid) as photosid " +
			"FOREACH(rel IN rels: DELETE rel) DELETE p " +
			"WITH n, photosid MATCH n<-[r]-() DELETE n, r " +
			"RETURN photosid")
	Iterable<String> deleteEditorial(String editorialid);
	
	@Query( "START n=node:editorialid(editorialid={0}) " +
			"MATCH n-[:EDITORIALPHOTO]->p " +
			"RETURN p ORDER BY p.createdDate DESC " +
			"SKIP {1} LIMIT {2}")
	Iterable<EditorialPhoto> getPhotos(String editorialid, int skip, int limit);
	
	@Query( "START n=node:editorialid(editorialid={0}) " +
			"MATCH n-[:EDITORIALPHOTO]->p WHERE p.editorialphotoname! =~ {1} " +
			"RETURN p ORDER BY p.createdDate DESC " +
			"SKIP {2} LIMIT {3}")
	Iterable<EditorialPhoto> searchPhotosByName(String editorialid, String photoname, int skip, int limit);
	
	@Query("START p = node:editorialphotouniqueid(editorialphotouniqueid={0}) MATCH p-[r]-() DELETE p, r")
	void deletePhoto(String editorialphotouniqueid);
	
	@Query("START p = node:editorialphotouniqueid(editorialphotouniqueid={0}) " +
			"SET p.editorialphotoname = {1}, p.description = {2}")
	void editPhotoInfo(String editorialphotouniqueid, String editorialphotoName, String description);
	
	@Query("START p = node:editorialphotouniqueid(editorialphotouniqueid={0}) SET p.editorialphotoname = {1}")
	void editPhotoName(String editorialphotouniqueid, String editorialphotoname);
	
	@Query("START p = node:editorialphotouniqueid(editorialphotouniqueid={0}) SET p.description = {1}")
	void editPhotoDescription(String editorialphotouniqueid, String description);
}
