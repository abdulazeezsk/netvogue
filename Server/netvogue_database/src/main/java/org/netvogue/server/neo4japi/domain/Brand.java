package org.netvogue.server.neo4japi.domain;

//Spring specific
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.*;
import org.springframework.data.neo4j.support.index.*;
import org.netvogue.server.neo4japi.common.USER_TYPE;

//Generic
import java.util.*;

public class Brand extends User{
	
	@Indexed(indexName="brandname", indexType=IndexType.FULLTEXT)
	String brandName;
	
	//Stockists -- This is nothing but boutiqueStores
	Set<Boutique> stockists;
	
	int minPriceRange;
	
	int maxPriceRange;
	
	@RelatedTo(type="BRAND", direction=Direction.INCOMING)
	BrandRefNode brandRefNode;
	
	@RelatedTo(type="HAS_COLLECTION")
	Set<Collections> collections;
	
	public Brand() {
		super();
	}
	
	public Brand(String email, String password) {
		super(email, password);
		setUserType(USER_TYPE.BRAND);
		setRoles(Roles.ROLE_BRAND);
	}
	
	public String getBrandName() {
		return brandName;
	}
	
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
	public void AddtoRefNode(BrandRefNode brandRefNode) {
		this.brandRefNode = brandRefNode;
	}
	
	@Override
    public boolean equals(Object other) {
		if (this == other) 
			return true;
		if (nodeId == null) 
			return false;
		if (! (other instanceof Brand)) 
			return false;
		return nodeId.equals(((Brand) other).nodeId);    
	}

    @Override
    public int hashCode() {
    	return nodeId == null ? System.identityHashCode(this) : nodeId.hashCode();
    }
}