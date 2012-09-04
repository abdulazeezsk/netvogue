package org.netvogue.server.neo4japi.domain;

//Project specific
import org.netvogue.server.neo4japi.common.*;

//Spring specific imports
import org.springframework.data.neo4j.annotation.*;
import org.springframework.data.neo4j.support.index.*;

//Generic
import java.util.*;

//This relationship will be created even if there is pending request
@RelationshipEntity
public class Network {

	@GraphId
	Long Id;
	
	@Indexed(indexName="networkstatus", indexType=IndexType.FULLTEXT)
	NetworkStatus status;
	
	@RelationshipType
	RelationType relationType;
	
	Date netvogueDate;
	
	@StartNode
	Boutique boutique;
	@EndNode
	Brand 	brand;
	
}
