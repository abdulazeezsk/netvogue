package org.netvogue.server.webmvc.domain;

import java.util.Set;

public class Stylesheets extends CommonResponse{
	Set<Stylesheet> stylesheets;
	public Set<Stylesheet> getStylesheets() {
		return stylesheets;
	}
	public void setStylesheets(Set<Stylesheet> stylesheets) {
		this.stylesheets = stylesheets;
	}
}
