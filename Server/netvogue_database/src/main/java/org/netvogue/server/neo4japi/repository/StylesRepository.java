package org.netvogue.server.neo4japi.repository;

//project specific
import org.netvogue.server.neo4japi.domain.*;
//Spring specific
import org.springframework.data.neo4j.repository.*;

public interface StylesRepository extends GraphRepository<BrandStyles> {

}
