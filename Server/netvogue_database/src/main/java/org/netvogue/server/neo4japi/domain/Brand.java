package org.netvogue.server.neo4japi.domain;

//Spring specific
import org.netvogue.server.neo4japi.common.USER_TYPE;

public class Brand extends User{
	
	/*@RelatedTo(type="BRAND", direction=Direction.INCOMING)
	BrandRefNode brandRefNode;*/
	
	public Brand() {
		super();
	}
	
	public Brand(String email, String password) {
		super(email, password);
		accountEnabled = true;
		setUserType(USER_TYPE.BRAND);
		setRoles(Roles.ROLE_BRAND);
	}
	
	/*public void AddtoRefNode(BrandRefNode brandRefNode) {
		this.brandRefNode = brandRefNode;
	}*/
	
	@Override
    public boolean equals(Object other) {
		if (this == other) 
			return true;
		if (nodeId == null) 
			return false;
		if (! (other instanceof Brand)) 
			return false;
		return nodeId.equals(((Brand) other).nodeId);    
	}

    @Override
    public int hashCode() {
    	return nodeId == null ? System.identityHashCode(this) : nodeId.hashCode();
    }
}