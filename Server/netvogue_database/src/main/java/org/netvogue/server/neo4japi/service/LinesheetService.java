package org.netvogue.server.neo4japi.service;

import org.netvogue.server.neo4japi.common.ResultStatus;
import org.netvogue.server.neo4japi.domain.Linesheet;

public interface LinesheetService {

	public ResultStatus SaveLinesheet(Linesheet newLinesheet, String error);
}
