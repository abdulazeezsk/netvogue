package org.netvogue.server.neo4japi.repository;

import java.util.List;
import java.util.Map;

import org.netvogue.server.neo4japi.domain.*;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends GraphRepository<User> {
	User findByemail(String email);
	User findByusername(String username);
	
	@Query( "START n=node:email({0}) WHERE n.id <> {1} RETURN n")
	User findByemailAndId(String email, Long id);
	//User findByemailOrUsername(String email, String username);
	
	@Query( "start category=node:category({cat}) /" +
			"with collect(category) as categories /" +
			"start user=node:index({search}) /" +
			"where p<>user AND ALL( c in categories WHERE user-[:HAS_CAT]->c)  /" +
			"return user " +
			"skip {pagenumber*pagesize} limit{pagesize}")
	Iterable<User> doAdvancedSearch(@Param("SelCategories") List<String> Categories, @Param("searchindex") Map<String, String> searchIndex,
									@Param("pagenumber") long pagenumber, @Param("pagesize") long pagesize);
	
	@Query( "START n=node:username(username={0}) MATCH n-[:GALLERY]->g RETURN g ORDER BY g.createdDate DESC")
	Iterable<Gallery> getGalleries(String username);
	
	@Query( "START n=node:username(username={0}) MATCH n-[:GALLERY]->g WHERE g.galleryname =~ {1} " +
			"RETURN g ORDER BY g.createdDate DESC")
	Iterable<Gallery> searchGalleryByName(String username, String galleryname);
	
}