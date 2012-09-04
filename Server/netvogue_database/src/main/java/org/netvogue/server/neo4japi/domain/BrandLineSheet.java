package org.netvogue.server.neo4japi.domain;

//Project specific imports
import org.netvogue.server.neo4japi.common.*;

//Spring specific imports
import org.springframework.data.neo4j.annotation.*;
import org.springframework.data.neo4j.support.index.*;

//Generic imports
import java.util.*;

@NodeEntity
public class BrandLineSheet {

	@GraphId
	Long nodeId;
	
	@Indexed(indexName="linesheetseason", indexType=IndexType.FULLTEXT)
	String seasonName;
	
	@Indexed(indexName="linesheetdesigner", indexType=IndexType.FULLTEXT)
	String designerName;

	//@Indexed(indexName="linesheetDeliveryDate", indexType=IndexType.)
	Date deliveryDate;
	
	Date cutoffDate;
	
	PrivacySetting privacy;
	
	Collection<BrandStyles> Styles;

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BrandLineSheet linesheet = (BrandLineSheet) o;
        if (nodeId == null) return super.equals(o);
        return nodeId.equals(linesheet.nodeId);

    }

    @Override
    public int hashCode() {
        return nodeId != null ? nodeId.hashCode() : super.hashCode();
    }	
	
}
