package org.netvogue.server.neo4japi.repository;

import org.netvogue.server.neo4japi.domain.Gallery;
import org.netvogue.server.neo4japi.domain.Photo;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.*;

public interface GalleryRepository extends GraphRepository<Gallery>{
	
	@Query( "START n=node:galleryid(galleryid={0}) RETURN n")
	Gallery getGallery(String id);
	
	@Query("START p = node:galleryid(galleryid={0}) SET p.galleryname = {1}")
	void editGalleryName(String galleryid, String name);
	
	@Query("START p = node:galleryid(galleryid={0}) SET p.profilePicLink = {1}")
	void setProfilepic(String galleryid, String uniqueid);
	
	@Query("START n=node:galleryid(galleryid={0}) MATCH n-[rels*0..]->p FOREACH(rel IN rels: DELETE rel) DELETE p " +
			"WITH n MATCH n<-[r]-() DELETE n, r")
	void deleteGallery(String galleryId);
	
	@Query( "START n=node:galleryid(galleryid={0}) MATCH n-[:PHOTO]->p " +
			"RETURN p ORDER BY p.createdDate DESC " +
			"SKIP {1} LIMIT {2}")
	Iterable<Photo> getPhotos(String galleryid, int skip, int limit);
	
	@Query( "START n=node:galleryid(galleryid={0}) " +
			"MATCH n-[:PHOTO]->p WHERE p.photoname! =~ {1} " +
			"RETURN p ORDER BY p.createdDate DESC " +
			"SKIP {2} LIMIT {3}")
	Iterable<Photo> searchPhotosByName(String galleryid, String photoname, int skip, int limit);
	
	@Query("START p = node:photouniqueid(photouniqueid={0}) MATCH p-[r]-() DELETE p, r")
	void deletePhoto(String photoId);
	
	@Query("START p = node:photouniqueid(photouniqueid={0}) SET p.photoname = {1}, p.seasonname = {2}")
	void editPhotoInfo(String photoId, String photoName, String seasonname);
	
	@Query("START p = node:photouniqueid(photouniqueid={0}) SET p.name = {1}")
	void editPhotoName(String photoId, String photoName);
	
	@Query("START p = node:photouniqueid(photouniqueid={0}) SET p.seasonname = {1}")
	void editPhotoSeasonName(String photoId, String seasonName);
}
