package org.netvogue.server.neo4japi.domain;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

@NodeEntity
public class RootEntity {
    @GraphId Long id;

    String rootName;

    public Long getId() {
        return id;
    }

    public String getRootName() {
        return rootName;
    }

    public void setRootName(String rootName) {
        this.rootName = rootName;
    }
}