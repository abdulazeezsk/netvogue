package org.netvogue.server.webmvc.domain;

import java.util.Date;

public class Linesheet extends GenericGallery{
	String brandname;
	String category;
	String left_url;
	String deliverydate;
	public String getBrandname() {
		return brandname;
	}
	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}
	public String getLeft_url() {
		return left_url;
	}
	public void setLeft_url(String left_url) {
		this.left_url = left_url;
	}
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
	public String getDeliverydate() {
		return deliverydate;
	}
	public void setDeliverydate(String deliverydate) {
		this.deliverydate = deliverydate;
	}
}
