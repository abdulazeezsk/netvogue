package org.netvogue.server.neo4japi.repository;

import org.netvogue.server.neo4japi.domain.Photo;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface PhotoRepository extends GraphRepository<Photo> {

}
