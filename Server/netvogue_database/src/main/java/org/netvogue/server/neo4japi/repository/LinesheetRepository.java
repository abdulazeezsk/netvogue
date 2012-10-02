package org.netvogue.server.neo4japi.repository;

//project specific
import java.util.Date;

import org.netvogue.server.neo4japi.domain.*;
import org.springframework.data.neo4j.annotation.Query;
//Spring specific
import org.springframework.data.neo4j.repository.*;

public interface LinesheetRepository extends GraphRepository<Linesheet>{
	
	@Query( "START n=node:linesheetId(linesheetId={0}) RETURN n")
	Linesheet getLinesheet(String linesheetId);
	
	@Query("START p = node:linesheetId(linesheetId={0}) SET p.linesheetname = {1}, p.deliveryDate = {2}")
	void editLinesheet(String linesheetId, String name, Date deliverydate);
	
	@Query("START n=node:linesheetid(linesheetid={0}) MATCH n-[r]-() DELETE n, r")
	void deleteLinesheet(String linesheetid);
	
}