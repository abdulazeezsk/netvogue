package org.netvogue.server.webmvc.domain;

public class Collection extends GenericGallery {

	String Category;
	String brandname;
	String left_url;
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	public String getBrandname() {
		return brandname;
	}
	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}
	public String getLeftpic() {
		return left_url;
	}
	public void setLeftpic(String leftpic) {
		this.left_url = leftpic;
	}
}
