package org.netvogue.server.neo4japi.repository;

import org.netvogue.server.neo4japi.domain.Style;
import org.netvogue.server.neo4japi.domain.Stylesheet;
import org.netvogue.server.neo4japi.service.StyleData;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface StylesheetRepository extends GraphRepository<Stylesheet> {

	@Query( "START n=node:stylesheetid(stylesheetid={0}) RETURN n")
	Stylesheet getStylesheet(String stylesheetid);
	
	@Query("START p = node:stylesheetid(stylesheetid={0}) SET p.stylesheetname = {1}")
	void editStylesheet(String stylesheetid, String name);
	
	@Query("START n=node:stylesheetid(stylesheetid={0}) MATCH n-[rels*0..]->p " +
			"FOREACH(rel IN rels: DELETE rel) DELETE p " +
			"WITH n MATCH n<-[r]-() DELETE n, r")
	void deleteStylesheet(String stylesheetid);
	
	@Query( "START n=node:styleid(styleid={0}) RETURN n")
	Style getStyle(String styleid);
	
	@Query( "START n=node:stylesheetid(stylesheetid={0}) " +
			"MATCH n-[:SS_STYLE]->styles, n-[:STYLESHEET]-user " +
			"RETURN user.name as name, styles ORDER BY styles.createdDate DESC")
	Iterable<StyleData> getStyles(String stylesheetid);
	
	@Query( "START n=node:search(username={0}) MATCH n-[:STYLESHEET]-ss<-[:Stylesheet_Category]-p " +
			"WHERE p.productline = {1}" +
			"WITH n, ss MATCH ss-[:SS_STYLE]-styles " +
			"RETURN n.name name, styles ORDER BY styles.createdDate DESC")
	Iterable<StyleData> getStylesByCategory(String username, String category);
	
	/*@Query( "START n=node:stylesheetid(printcampaignid={0}) " +
			"MATCH n-[:PRINTCAMPAIGNPHOTO]->p WHERE p.printcampaignphotoname! =~ {1} " +
			"RETURN p ORDER BY p.createdDate DESC")
	Iterable<Style> searchStyles(String stylesheetid, String query);*/
	
	@Query("START p = node:styleid(styleid={0}) MATCH p-[r]-() DELETE p, r")
	void deleteStyle(String styleid);
	
}
