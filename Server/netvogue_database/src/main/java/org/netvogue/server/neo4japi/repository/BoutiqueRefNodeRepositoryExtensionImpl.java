package org.netvogue.server.neo4japi.repository;

//Netvogue specific imports
import org.netvogue.server.neo4japi.domain.*;

//Neo4j specific imports
import org.neo4j.graphdb.Node;

//Spring specific imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;

public class BoutiqueRefNodeRepositoryExtensionImpl implements BoutiqueRefNodeRepositoryExtension {

	//@Autowired BoutiqueRefNodeRepository 	repo;
	@Autowired Neo4jTemplate				neo4jTemplate;
	Long graphId = (long) 0;
	
	@Override
	public BoutiqueRefNode getrefnode() {
		// TODO Auto-generated method stub
		BoutiqueRefNode refNode = null;
		if(0 == graphId) {
			//refNode = repo.findByPropertyValue("name", "boutiquerefnode");
			final Node referenceNode = neo4jTemplate.getReferenceNode();
			//neo4jTemplate.creater
		    neo4jTemplate.postEntityCreation(referenceNode,RootEntity.class);
		    final RootEntity root = neo4jTemplate.findOne(referenceNode.getId(), RootEntity.class);
		    //root.setRootName("RootName");
		    //neo4jTemplate.save(root);
			refNode = new BoutiqueRefNode();
			refNode.setRoot(root);
			graphId = refNode.getId();
			neo4jTemplate.save(refNode);
		} else {
			//refNode = repo.findOne(graphId);
		}
		return refNode;
	}

	
}
