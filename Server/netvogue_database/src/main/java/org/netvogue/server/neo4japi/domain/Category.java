package org.netvogue.server.neo4japi.domain;

import org.netvogue.server.neo4japi.common.CategoryType;
import org.netvogue.server.neo4japi.common.ProductLines;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.support.index.IndexType;

@NodeEntity
public class Category {
	@GraphId
	Long nodeId;
	
	@Indexed(indexName="productline", indexType=IndexType.FULLTEXT)
	CategoryType categorytype;
	
	@Indexed(indexName="productline", indexType=IndexType.FULLTEXT, unique=true)
	ProductLines productline;
	
	String productlineDesc;

	public Category() {
		
	}
	public Category(ProductLines line) {
		productline 	= line;
		categorytype 	= line.getCategoryType();
	}

	public CategoryType getCategorytype() {
		return categorytype;
	}
	public void setCategorytype(CategoryType categorytype) {
		this.categorytype = categorytype;
	}
	public ProductLines getProductLine() {
		return productline;
	}

	public void setProductLine(ProductLines productLine) {
		this.productline = productLine;
	}

	public String getProductlineDesc() {
		return productlineDesc;
	}

	public void setProductlineDesc(String productlineDesc) {
		this.productlineDesc = productlineDesc;
	}
	
	@Override
    public boolean equals(Object other) {
		if (this == other) 
			return true;
		if (nodeId == null) 
			return false;
		if (! (other instanceof Category)) 
			return false;
		return nodeId.equals(((Category) other).nodeId);    
	}

    @Override
    public int hashCode() {
    	return nodeId == null ? System.identityHashCode(this) : nodeId.hashCode();
    }
}
