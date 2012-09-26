package org.netvogue.server.neo4japi.repository;

import org.netvogue.server.neo4japi.domain.Gallery;
import org.netvogue.server.neo4japi.domain.Photo;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.*;

public interface GalleryRepository extends GraphRepository<Gallery>{
	
	@Query( "START n=node:galleryid(galleryid={0}) RETURN n")
	Gallery getGallery(String id);
	
	@Query( "START n=node:galleryid(galleryid={0}) MATCH n-[:PHOTO]->g RETURN g")
	Iterable<Photo> getPhotos(String galleryid);
}
