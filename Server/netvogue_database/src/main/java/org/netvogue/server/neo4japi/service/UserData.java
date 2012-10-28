package org.netvogue.server.neo4japi.service;

import org.netvogue.server.neo4japi.common.ProductLines;
import org.netvogue.server.neo4japi.domain.User;
import org.springframework.data.neo4j.annotation.MapResult;
import org.springframework.data.neo4j.annotation.ResultColumn;

@MapResult
public interface UserData {

	@ResultColumn("user")
	User getUser();
	
	@ResultColumn("productlines")
	Iterable<ProductLines> getProductlines();
	
	@ResultColumn("brandnames")
	Iterable<String> getBrandnames();
	
	@ResultColumn("brandusernames")
	Iterable<String> getBrandusernames();
	
	@ResultColumn("profilepics")
	Iterable<String> getprofilepics();
}
