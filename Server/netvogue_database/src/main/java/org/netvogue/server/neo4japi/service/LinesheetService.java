package org.netvogue.server.neo4japi.service;

import java.util.Date;

import org.netvogue.server.neo4japi.common.ResultStatus;
import org.netvogue.server.neo4japi.domain.Linesheet;

public interface LinesheetService {

	public ResultStatus SaveLinesheet(Linesheet newLinesheet, String error);
	
	public Linesheet getLinesheet(String id);
	public ResultStatus editLinesheet(String id, String name, Date deliverydate, String error);
	public ResultStatus deleteLinesheet(String id, String error);
	
	public Iterable<StyleData> getStyles(String linesheetId, int pagenumber);
	public Iterable<StyleData> searchStyles(String linesheetId, String styleno, 
							long fromPrice, long toPrice, int pagenumber);
	public ResultStatus deleteStyle(String styleId, String error);
}
