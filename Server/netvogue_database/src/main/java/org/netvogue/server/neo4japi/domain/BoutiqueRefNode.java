package org.netvogue.server.neo4japi.domain;

//Project specific
import org.netvogue.server.neo4japi.domain.*;

//Spring specific
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.*;

//Java specific
import java.util.*;

@NodeEntity
//@Scope(StandardScopes.SINGLETON)
public class BoutiqueRefNode {
	@GraphId
	Long nodeId;
	
	@Indexed(indexName="boutiquerefnode")
	String name = "boutiquerefnode";

	@RelatedTo(type="BOUTIQUEROOT")
	RootEntity rootEntity;
	
	/*@RelatedTo(type="BOUTIQUE", direction=Direction.OUTGOING)
	Set<Boutique> boutiques;*/
	
	public BoutiqueRefNode() {
		//name = "boutiquerefnode";
	}
	
	public RootEntity getRoot() {
        return rootEntity;
    }

    public void setRoot(RootEntity root) {
        this.rootEntity = root;
    }
	
    /*public void AddNewBoutique(Boutique newBoutique) {
		boutiques.add(newBoutique);
	}*/
	
	public Long getId() {
		return nodeId;
	}
}