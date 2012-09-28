package org.netvogue.server.neo4japi.service;

import org.netvogue.server.neo4japi.common.ResultStatus;import org.netvogue.server.neo4japi.domain.PrintCampaign;
import org.netvogue.server.neo4japi.domain.PrintCampaignPhoto;

public interface PrintCampaignService {

	public ResultStatus SavePrintCampaign(PrintCampaign newPrintCampaign, String error);
	
	public PrintCampaign getPrintCampaign(String printcampaignId);
	public ResultStatus editPrintCampaign(String printCampaignId, String name, String desc, String error);
	public ResultStatus editPrintCampaignName(String printcampaignId, String name, String error);
	public ResultStatus deletePrintCampaign(String printcampaignId, String error);
	
	public Iterable<PrintCampaignPhoto> getPhotos(String printcampaignId);
	public Iterable<PrintCampaignPhoto> searchPhotoByName(PrintCampaign printCampaign, String name);
	public Iterable<PrintCampaignPhoto> searchPhotoByName(String printcampaignId, String name);
	public ResultStatus editPhotoInfo(String photoId, String name, String desc, String error);
	public ResultStatus editPhotoName(String photoId, String name, String error);
	public ResultStatus editPhotoDescription(String photoId, String name, String error);
	public ResultStatus deletePhoto(String photoId, String error);
}
