package org.netvogue.server.webmvc.domain;

import java.util.Set;

public class Linesheets extends CommonResponse{
	Set<Linesheet> lineesheets;
	
	public Set<Linesheet> getLinesheets() {
		return lineesheets;
	}
	public void setLinesheets(Set<Linesheet> lineesheets) {
		this.lineesheets = lineesheets;
	}
}
