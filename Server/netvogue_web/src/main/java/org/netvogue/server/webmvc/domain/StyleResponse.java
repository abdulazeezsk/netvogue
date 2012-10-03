package org.netvogue.server.webmvc.domain;

import java.util.Set;

public class StyleResponse {
	String styleid = null;
	String stylename;
	String styleno;
	String profilethumbpic;
	String profileleftpic;
	String description;
	String fabrication;
	long price;
	Set<String> availableSizes;
	Set<String> availableColors;
	Set<PhotoWeb> availableImages;
	
	public String getStyleid() {
		return styleid;
	}
	public void setStyleid(String styleid) {
		this.styleid = styleid;
	}
	public String getStylename() {
		return stylename;
	}
	public void setStylename(String stylename) {
		this.stylename = stylename;
	}
	public String getStyleno() {
		return styleno;
	}
	public void setStyleno(String styleno) {
		this.styleno = styleno;
	}
	public String getProfilethumbpic() {
		return profilethumbpic;
	}
	public void setProfilethumbpic(String profilethumbpic) {
		this.profilethumbpic = profilethumbpic;
	}
	public String getProfileleftpic() {
		return profileleftpic;
	}
	public void setProfileleftpic(String profileleftpic) {
		this.profileleftpic = profileleftpic;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFabrication() {
		return fabrication;
	}
	public void setFabrication(String fabrication) {
		this.fabrication = fabrication;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public Set<String> getAvailableSizes() {
		return availableSizes;
	}
	public void setAvailableSizes(Set<String> availableSizes) {
		this.availableSizes = availableSizes;
	}
	public Set<String> getAvailableColors() {
		return availableColors;
	}
	public void setAvailableColors(Set<String> availableColors) {
		this.availableColors = availableColors;
	}
	public Set<PhotoWeb> getAvailableImages() {
		return availableImages;
	}
	public void setAvailableImages(Set<PhotoWeb> availableImages) {
		this.availableImages = availableImages;
	}
}
