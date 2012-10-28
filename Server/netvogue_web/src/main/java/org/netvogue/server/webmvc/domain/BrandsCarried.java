package org.netvogue.server.webmvc.domain;

public class BrandsCarried {
	
	String brandname 	;
	String brandusername;
	String profilepic;
	
	public String getBrandname() {
		return brandname;
	}
	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}
	public String getBrandusername() {
		return brandusername;
	}
	public void setBrandusername(String brandusername) {
		this.brandusername = brandusername;
	}
	public String getProfilepic() {
		return profilepic;
	}
	public void setProfilepic(String profilepic) {
		this.profilepic = profilepic;
	}		
}