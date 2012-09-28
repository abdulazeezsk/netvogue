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
	
	//Queries related to gallery
	@Query( "START n=node:username(username={0}) MATCH n-[:GALLERY]->g RETURN g ORDER BY g.createdDate DESC")
	Iterable<Gallery> getGalleries(String username);
	
	@Query( "START n=node:username(username={0}) MATCH n-[:GALLERY]->g WHERE g.galleryname =~ {1} " +
			"RETURN g ORDER BY g.createdDate DESC")
	Iterable<Gallery> searchGalleryByName(String username, String galleryname);
	
	//Queries related to Print campaigns
	@Query( "START n=node:username(username={0}) MATCH n-[:PRINTCAMPAIGN]->pc RETURN pc ORDER BY pc.createdDate DESC")
	Iterable<PrintCampaign> getPrintCampaigns(String username);
	
	@Query( "START n=node:username(username={0}) MATCH n-[:PRINTCAMPAIGN]->pc WHERE pc.printcampaignname =~ {1} " +
			"RETURN pc ORDER BY pc.createdDate DESC")
	Iterable<PrintCampaign> searchPrintCampaignByName(String username, String printcampaignname);
	
	//Queries related to editorials
	@Query( "START n=node:username(username={0}) MATCH n-[:EDITORIAL]->e RETURN e ORDER BY e.createdDate DESC")
	Iterable<Editorial> getEditorials(String username);
	
	@Query( "START n=node:username(username={0}) MATCH n-[:EDITORIAL]->e WHERE e.editorialname =~ {1} " +
			"RETURN e ORDER BY e.createdDate DESC")
	Iterable<Editorial> searchEditorialByName(String username, String editorialname);
	
	//queries related to collections
	@Query( "START n=node:username(username={0}) MATCH n-[:COLLECTION]->c RETURN c ORDER BY c.createdDate DESC")
	Iterable<Collection> getCollections(String username);
	
	@Query( "START n=node:username(username={0}) MATCH n-[:COLLECTION]->c WHERE c.collectionname =~ {1} " +
			"RETURN c ORDER BY c.createdDate DESC")
	Iterable<Collection> searchCollectionByName(String username, String collectionname);
	
}