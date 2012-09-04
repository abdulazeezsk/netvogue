package org.netvogue.server.webmvc.domain;

public class ProductLine {
	
	String productlinename	= new String();
   	boolean selected;
   	
	public String getProductlinename() {
		return productlinename;
	}
	public void setProductlinename(String productlinename) {
		this.productlinename = productlinename;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}