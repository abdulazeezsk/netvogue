package org.netvogue.server.neo4japi.domain;

//project specific imports
import org.netvogue.server.neo4japi.common.*;

//Spring Framework imports
import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.*;
import org.springframework.data.neo4j.support.index.IndexType;

//Generic imports
import java.util.Collection;
import java.util.Map;
import java.util.Set;


public class Boutique extends User{

	@RelatedTo(type="BOUTIQUE", direction=Direction.INCOMING)
	BoutiqueRefNode boutiqueRefNode;
	Set<ProductLines> productLines;
	
	//Because of this there would be two relationships between brand and boutique
	//BrandsCarried of boutique may or may not be part of their network
	//If any particular brand is not there in our network, just add with basic information and move ahead. Tomorrow, if someone else
	// add this new brand into our network. Then these boutiques must be automatically connected to them
	Set<Brand> brandsCarried; //Azeez -- How do we connect it to existing brands in our network or to the new network.
	
	//Relation to Brand 
	Set<Brand> network;
	
	//Stores -- Add new relationship/class to stores
	
	//Relation to collection relation relation type to brand collection
	//Map<String, Collections> myCollections;
	
	Collection<Notification> myNotifications;

	//This is for spring neo4j
	public Boutique() {
		super();
	}
	
	public Boutique(String email, String password)
	{
		super(email, password);
		setUserType(USER_TYPE.BOUTIQUE);
		setRoles(Roles.ROLE_BOUTIQUE);
	}
	
	public void AddtoRefNode(BoutiqueRefNode boutiqueRefNode) {
		this.boutiqueRefNode = boutiqueRefNode;
	}
	
	@Override
    public boolean equals(Object other) {
		if (this == other) 
			return true;
		if (nodeId == null) 
			return false;
		if (! (other instanceof Boutique)) 
			return false;
		return nodeId.equals(((Boutique) other).nodeId);    
	}

    @Override
    public int hashCode() {
    	return nodeId == null ? System.identityHashCode(this) : nodeId.hashCode();
    }

}