package org.netvogue.server.neo4japi.repository;

//project specific
import java.util.Date;

import org.netvogue.server.neo4japi.domain.*;
import org.netvogue.server.neo4japi.service.StyleData;
import org.springframework.data.neo4j.annotation.Query;
//Spring specific
import org.springframework.data.neo4j.repository.*;

public interface LinesheetRepository extends GraphRepository<Linesheet>{
	
	@Query( "START n=node:linesheetid(linesheetid={0}) RETURN n")
	Linesheet getLinesheet(String linesheetId);
	
	@Query("START p = node:linesheetid(linesheetid={0}) SET p.linesheetname = {1}, p.deliveryDate = {2}")
	void editLinesheet(String linesheetId, String name, Date deliverydate);
	
	@Query("START n=node:linesheetid(linesheetid={0}) MATCH n-[r]-() DELETE n, r")
	void deleteLinesheet(String linesheetid);

	@Query( "START n=node:linesheetid(linesheetid={0}) " +
			"MATCH n-[:LS_STYLE]->styles, n-[:LINESHEET]-user " +
			"RETURN user.name as name, styles ORDER BY styles.createdDate DESC")
	Iterable<StyleData> getStyles(String linesheetid);
	
	@Query( "START n=node:linesheetid(linesheetid={0}) " +
			"MATCH n-[:LS_STYLE]->styles, n-[:LINESHEET]-user " +
			"WHERE styles.styleno = {1} AND styles.price >= {2} AND styles.price <= {3}" +
			"RETURN user.name as name, styles ORDER BY styles.createdDate DESC")
	Iterable<StyleData> searchStyles(String stylesheetid, String styleno, long fromprice, long toprice);
	
	@Query("START p = node:styleid(styleid={0}) MATCH p-[r:LS_STYLE]-() DELETE r")
	void deleteStyle(String styleid);
}