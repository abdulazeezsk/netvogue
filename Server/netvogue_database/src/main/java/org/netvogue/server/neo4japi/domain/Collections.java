package org.netvogue.server.neo4japi.domain;

//Project specific imports
import org.netvogue.server.neo4japi.common.*;

//Spring specific imports
import org.springframework.data.neo4j.annotation.*;
import org.springframework.data.neo4j.support.index.*;

//Generic imports
import java.util.Set;
import java.util.Collection;

@NodeEntity
public class Collections {

	@GraphId
	Long nodeId;

	@Indexed(indexName="collectionsSeasonName", indexType=IndexType.FULLTEXT)
	String 			seasonName;
	
	String 			designerName;
	
	PrivacySetting 	privacy;
	
	String 			coverImage;
	
	Set<String>		CollectionImages;
	
	String			season; //Do we have any pre-configured seasons.
	
	Set<ProductLines> categories;
	
	Collection<String> Collections;
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Collections collections = (Collections) o;
        if (nodeId == null) return super.equals(o);
        return nodeId.equals(collections.nodeId);

    }

    @Override
    public int hashCode() {
        return nodeId != null ? nodeId.hashCode() : super.hashCode();
    }
}
