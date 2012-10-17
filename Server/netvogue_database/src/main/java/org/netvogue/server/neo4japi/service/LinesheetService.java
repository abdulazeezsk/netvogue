package org.netvogue.server.neo4japi.service;

import java.util.Date;

import org.netvogue.server.neo4japi.common.ResultStatus;
import org.netvogue.server.neo4japi.domain.Linesheet;

public interface LinesheetService {

	public ResultStatus SaveLinesheet(Linesheet newLinesheet, String error);
	
	public Linesheet getLinesheet(String id);
	public ResultStatus editLinesheet(String id, String name, Date deliverydate, String error);
	public ResultStatus deleteLinesheet(String id, String error);
	
	public Iterable<StyleData> getStyles(String linesheetId);
	/*public Iterable<PrintCampaignPhoto> searchPhotoByName(PrintCampaign printCampaign, String name);
	public Iterable<PrintCampaignPhoto> searchPhotoByName(String printcampaignId, String name);*/
	public ResultStatus deleteStyle(String styleId, String error);
}
