package org.netvogue.server.neo4japi.service;

import org.netvogue.server.neo4japi.common.ResultStatus;
import org.netvogue.server.neo4japi.domain.Stylesheet;

public interface StylesheetService {
	public ResultStatus SaveStylesheet(Stylesheet newStylesheet, String error);
	
	public Stylesheet getStylesheet(String id);
	public ResultStatus editStylesheet(String id, String name, String error);
	public ResultStatus deleteStylesheet(String id, String error);	
	
}
