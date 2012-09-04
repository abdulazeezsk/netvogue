package org.netvogue.server.neo4japi.repository;

//Netvogue specific imports
import org.netvogue.server.neo4japi.domain.*;

//Spring specific imports
import org.springframework.data.neo4j.repository.GraphRepository;

public interface BoutiqueRefNodeRepository extends GraphRepository<BoutiqueRefNode>/*, BoutiqueRefNodeRepositoryExtension*/{
	
}
