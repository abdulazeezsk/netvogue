package org.netvogue.server.neo4japi.domain;

//This is for stores apart from Main store

//Spring specific imports
import org.springframework.data.neo4j.annotation.*;
import org.springframework.data.neo4j.support.index.*;

//Generic
import java.util.*;
//Azeez - Do brands too have stores??
@NodeEntity
public class BoutiqueStore {
	
	@GraphId
	Long nodeId;
	
	String storePicLink;
	
	String 	address;

	@Indexed(indexName="storecity", indexType=IndexType.FULLTEXT)
	String 	city;
	
	String 	state;
	
	int 	zipCode;

	@Indexed(indexName="storecountry", indexType=IndexType.FULLTEXT)
	String 	country;
	
	int 	telephoneNo;
	
	//For weekday as well as weekends
	Date	openingTime;
	
	Date	closingTime;
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BoutiqueStore store = (BoutiqueStore) o;
        if (nodeId == null) return super.equals(o);
        return nodeId.equals(store.nodeId);

    }

    @Override
    public int hashCode() {
        return nodeId != null ? nodeId.hashCode() : super.hashCode();
    }
}
