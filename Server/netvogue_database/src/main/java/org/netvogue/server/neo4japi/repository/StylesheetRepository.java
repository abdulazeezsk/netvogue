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
	
	@Query(	"START n=node:stylesheetid(stylesheetid={0}) MATCH n-[rels*1..]->p " +
			"WITH n, rels, p, collect(p.availableImages) as photosid " +
			"FOREACH(rel IN rels: DELETE rel) DELETE p " +
			"WITH n, photosid MATCH n<-[r]-() DELETE n, r " +
			"RETURN DISTINCT photosid")
	Iterable<Iterable<String>> deleteStylesheet(String stylesheetid);
	
	@Query( "START n=node:styleid(styleid={0}) RETURN n")
	Style getStyle(String styleid);
	
	@Query( "START n=node:stylesheetid(stylesheetid={0}) " +
			"MATCH n-[:SS_STYLE]->styles, n-[:STYLESHEET]-user " +
			"RETURN user.name as name, styles ORDER BY styles.createdDate DESC " +
			"SKIP {1} LIMIT {2}")
	Iterable<StyleData> getStyles(String stylesheetid, int skip, int limit);
	
	@Query( "START n=node:search(username={0}) MATCH n-[:STYLESHEET]-ss<-[:Stylesheet_Category]-p " +
			"WHERE p.productline = {1}" +
			"WITH n, ss MATCH ss-[:SS_STYLE]-styles " +
			"RETURN n.name as name, styles ORDER BY styles.createdDate DESC")
	Iterable<StyleData> getStylesByCategory(String username, String category);
	
	@Query( "START n=node:stylesheetid(stylesheetid={0}) " +
			"MATCH n-[:LS_STYLE]->styles, n-[:LINESHEET]-user " +
			"WHERE styles.styleno = {1} AND styles.fabrication = {2}" +
			"AND styles.price >= {3} AND styles.price <= {4} " +
			"RETURN user.name as name, styles ORDER BY styles.createdDate DESC " +
			"SKIP {5} LIMIT {6}")
	Iterable<StyleData> searchStyles(String stylesheetid, String styleno, String fabrication, 
											long fromprice, long toprice, int skip, int limit);
	
	@Query("START p = node:styleid(styleid={0}) MATCH p-[r]-() " +
			"WITH p.availableImages as photoids, p, r " +
			"DELETE p, r " +
			"RETURN DISTINCT photoids")
	Iterable<Iterable<String>> deleteStyle(String styleid);
	
}
