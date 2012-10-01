package org.netvogue.server.webmvc.domain;

import java.util.Date;

public class LinesheetJSONRequest {
	String id;
	String name;
	String category;
	Date deliverydate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Date getDeliverydate() {
		return deliverydate;
	}
	public void setDeliverydate(Date deliverydate) {
		this.deliverydate = deliverydate;
	}
	@Override
	public String toString() {
		String result = "\nName:" + name;
		result += "\nCategory:" + category;
		result += "\ndeliverydate" + deliverydate;
		return result;
	}
}
