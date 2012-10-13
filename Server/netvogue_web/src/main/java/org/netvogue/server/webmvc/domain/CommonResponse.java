package org.netvogue.server.webmvc.domain;

public class CommonResponse {
	String profileid;
	boolean isbrand;
	String name;
	ImageURLsResponse profilepic;
	
	public String getProfileid() {
		return profileid;
	}
	public void setProfileid(String profileid) {
		this.profileid = profileid;
	}
	public boolean isIsbrand() {
		return isbrand;
	}
	public void setIsbrand(boolean isbrand) {
		this.isbrand = isbrand;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ImageURLsResponse getProfilepic() {
		return profilepic;
	}
	public void setProfilepic(ImageURLsResponse profilepic) {
		this.profilepic = profilepic;
	}
}
