package org.netvogue.server.webmvc.domain;

import java.util.Set;

public class Styles {
	String name;
	String stylesheetname;
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
	public Set<StyleResponse> getStyles() {
		return styles;
	}
	public void setStyles(Set<StyleResponse> styles) {
		this.styles = styles;
	}
}
