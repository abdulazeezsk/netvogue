package org.netvogue.server.neo4japi.service;

import org.netvogue.server.neo4japi.common.ResultStatus;
import org.netvogue.server.neo4japi.domain.Brand;

public interface BrandService {
	public ResultStatus AddNewBrand(Brand b, String error);
	public Iterable<Brand> GetBrand(String name);
}
