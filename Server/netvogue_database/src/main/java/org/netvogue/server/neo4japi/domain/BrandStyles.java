package org.netvogue.server.neo4japi.domain;

//Project specific imports
import org.netvogue.server.neo4japi.common.*;

//Spring specific imports
import org.springframework.data.neo4j.annotation.*;

//Generic imports
import java.util.Set;
import java.util.Collection;

@NodeEntity
public class BrandStyles {

	@GraphId
	Long nodeId;
	
	String garmentName;
	
	String styleNo;
	
	String desc;
	
	@Indexed
	int price;
	
	Set<StyleSize> sizes;
	
	ProductLines category;
	
	PrivacySetting privacy;
	
	Collection<String> styleImages;
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BrandStyles style = (BrandStyles) o;
        if (nodeId == null) return super.equals(o);
        return nodeId.equals(style.nodeId);

    }

    @Override
    public int hashCode() {
        return nodeId != null ? nodeId.hashCode() : super.hashCode();
    }
}
