package org.netvogue.server.neo4japi.mapper;

import org.springframework.data.neo4j.annotation.MapResult;
import org.springframework.data.neo4j.annotation.ResultColumn;

@MapResult
public interface UserInfoMapper {

	@ResultColumn("name")
	String getName();

	@ResultColumn("username")
	String getUsername();

	@ResultColumn("email")
	String getEmail();

}
