package org.netvogue.server.neo4japi.domain;

import org.springframework.data.neo4j.annotation.*;

@NodeEntity
public class Press {

	@GraphId
	Long nodeId;
	
	//List of ad campaigns
	//List of editorials
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Press press = (Press) o;
        if (nodeId == null) return super.equals(o);
        return nodeId.equals(press.nodeId);

    }

    @Override
    public int hashCode() {
        return nodeId != null ? nodeId.hashCode() : super.hashCode();
    }
}
