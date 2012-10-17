package org.netvogue.server.webmvc.domain;

import java.util.Set;

public class Styles {
	String name;
	ImageURLsResponse profilepic;
	String stylesheetname;
	String brandname;
	Set<StyleResponse> styles;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStylesheetname() {
		return stylesheetname;
	}
	public void setStylesheetname(String stylesheetname) {
		this.stylesheetname = stylesheetname;
	}
	public String getBrandname() {
		return brandname;
	}
	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}
	public Set<StyleResponse> getStyles() {
		return styles;
	}
	public void setStyles(Set<StyleResponse> styles) {
		this.styles = styles;
	}
	public ImageURLsResponse getProfilepic() {
		return profilepic;
	}
	public void setProfilepic(ImageURLsResponse profilepic) {
		this.profilepic = profilepic;
	}
}
