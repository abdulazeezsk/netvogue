package org.netvogue.server.neo4japi.repository;

//project specific
import org.netvogue.server.neo4japi.domain.BrandRefNode;

//Spring specific
import org.springframework.data.neo4j.repository.GraphRepository;

public interface BrandRefNodeRepository extends GraphRepository<BrandRefNode>/*, BrandRefNodeRepositoryExtension */{

}
