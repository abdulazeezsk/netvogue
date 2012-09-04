package org.netvogue.server.neo4japi.domain;
//Project specific
import org.netvogue.server.neo4japi.domain.*;

//Spring specific
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.*;

//Java specific
import java.util.*;

@NodeEntity
public class BrandRefNode {
	@GraphId
	Long nodeId;
	
	@Indexed
	String name = "brandrefnode";
	
	/*@RelatedTo(type="BRAND", direction=Direction.INCOMING)
	Set<Brand> brands;*/

	@RelatedTo(type="BRANDROOT")
	RootEntity rootEntity;
	
	public BrandRefNode() {
		//name = "brandrefnode";
	}
	
	public RootEntity getRoot() {
        return rootEntity;
    }

    public void setRoot(RootEntity root) {
        this.rootEntity = root;
    }

	/*public void AddNewBrand(Brand brand) {
		brands.add(brand);
	}*/
	
	public Long getId() {
		return nodeId;
	}
}
