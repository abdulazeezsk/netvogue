package org.netvogue.server.neo4japi.service;

import org.netvogue.server.neo4japi.common.ResultStatus;
import org.netvogue.server.neo4japi.domain.Stylesheet;

public interface StylesheetService {
	public ResultStatus SaveStylesheet(Stylesheet newStylesheet, String error);
}
