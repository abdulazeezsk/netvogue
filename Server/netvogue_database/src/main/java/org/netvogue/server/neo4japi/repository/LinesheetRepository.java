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
			"MATCH n-[:LS_STYLE]->styles RETURN n.name as name, styles ORDER BY styles.createdDate DESC")
	Iterable<StyleData> getStyles(String linesheetid);
	
	/*@Query( "START n=node:stylesheetid(printcampaignid={0}) " +
			"MATCH n-[:PRINTCAMPAIGNPHOTO]->p WHERE p.printcampaignphotoname! =~ {1} " +
			"RETURN p ORDER BY p.createdDate DESC")
	Iterable<Style> searchStyles(String stylesheetid, String query);*/
	
	@Query("START p = node:styleid(styleid={0}) MATCH p-[r:LS_STYLE]-() DELETE r")
	void deleteStyle(String styleid);
}