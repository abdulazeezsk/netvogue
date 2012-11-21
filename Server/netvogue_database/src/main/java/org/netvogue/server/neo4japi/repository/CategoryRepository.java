package org.netvogue.server.neo4japi.repository;

import org.netvogue.server.common.ProductLines;
import org.netvogue.server.neo4japi.domain.Category;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface CategoryRepository extends GraphRepository<Category> {
	Category findByproductline(ProductLines line);
}