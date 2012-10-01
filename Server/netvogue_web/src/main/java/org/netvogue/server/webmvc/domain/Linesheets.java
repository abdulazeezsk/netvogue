package org.netvogue.server.webmvc.domain;

import java.util.Set;

public class Linesheets {
	String name;
	Set<Linesheet> lineesheets;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Linesheet> getLinesheets() {
		return lineesheets;
	}
	public void setLinesheets(Set<Linesheet> lineesheets) {
		this.lineesheets = lineesheets;
	}
}
