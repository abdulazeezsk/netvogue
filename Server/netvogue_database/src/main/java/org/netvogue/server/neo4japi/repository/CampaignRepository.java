package org.netvogue.server.neo4japi.repository;

import java.awt.print.Pageable;

import org.netvogue.server.neo4japi.domain.Campaign;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface CampaignRepository extends GraphRepository<Campaign>{
	
	Iterable<Campaign> findByTitleOrDesc(Pageable p);
}
