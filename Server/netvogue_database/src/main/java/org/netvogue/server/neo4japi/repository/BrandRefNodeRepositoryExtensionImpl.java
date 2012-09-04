package org.netvogue.server.neo4japi.repository;

//Project specific imports
import org.netvogue.server.neo4japi.domain.BoutiqueRefNode;
import org.netvogue.server.neo4japi.domain.BrandRefNode;
import org.netvogue.server.neo4japi.domain.RootEntity;

import org.neo4j.graphdb.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;

public class BrandRefNodeRepositoryExtensionImpl implements
		BrandRefNodeRepositoryExtension {

	@Autowired BrandRefNodeRepository	 	repo;
	@Autowired Neo4jTemplate				neo4jTemplate;
	Long graphId = (long) 0;
	
	@Override
	public BrandRefNode getrefnode() {
		// TODO Auto-generated method stub
		BrandRefNode refNode = null;
		if(0 == graphId) {
			final Node referenceNode = neo4jTemplate.getReferenceNode();
			neo4jTemplate.postEntityCreation(referenceNode,RootEntity.class);
		    final RootEntity root = neo4jTemplate.findOne(referenceNode.getId(), RootEntity.class);
		    refNode = new BrandRefNode();
			refNode.setRoot(root);
			graphId = refNode.getId();
			neo4jTemplate.save(refNode);
		} else {
			refNode = repo.findOne(graphId);
		}
		return refNode;
	}

}
