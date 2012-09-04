package org.netvogue.server.neo4japi.domain;

//Spring specific imports
import org.springframework.data.neo4j.annotation.*;

//Generic
import java.util.*;

@NodeEntity
public class StatusUpdate {

	@GraphId
	Long nodeId;
	
	String statusUpdate;
	
	Date postedDate;
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StatusUpdate statusUpdate = (StatusUpdate) o;
        if (nodeId == null) return super.equals(o);
        return nodeId.equals(statusUpdate.nodeId);

    }

    @Override
    public int hashCode() {
        return nodeId != null ? nodeId.hashCode() : super.hashCode();
    }
}
