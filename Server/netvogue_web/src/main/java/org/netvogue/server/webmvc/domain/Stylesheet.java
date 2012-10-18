package org.netvogue.server.webmvc.domain;

public class Stylesheet extends GenericGallery {
	String category;
	String left_url;
	String brandname;
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getLeftpic() {
		return left_url;
	}
	public void setLeftpic(String left_url) {
		this.left_url = left_url;
	}
	public String getBrandname() {
		return brandname;
	}
	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}
}
