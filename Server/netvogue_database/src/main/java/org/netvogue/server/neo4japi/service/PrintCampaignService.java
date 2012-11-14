package org.netvogue.server.neo4japi.service;

import java.util.List;

import org.netvogue.server.neo4japi.common.ResultStatus;import org.netvogue.server.neo4japi.domain.PrintCampaign;
import org.netvogue.server.neo4japi.domain.PrintCampaignPhoto;

public interface PrintCampaignService {

	public ResultStatus SavePrintCampaign(PrintCampaign newPrintCampaign, StringBuffer error);
	
	public PrintCampaign getPrintCampaign(String printcampaignId);
	public ResultStatus editPrintCampaign(String printCampaignId, String name, String desc, StringBuffer error);
	public ResultStatus editPrintCampaignName(String printcampaignId, String name, StringBuffer error);
	public ResultStatus setProfilepic(String printcampaignid, String uniqueid, StringBuffer error);
	public List<String> deletePrintCampaign(String printcampaignId, StringBuffer error);
	
	public Iterable<PrintCampaignPhoto> getPhotos(String printcampaignId, int pagenumber);
	public Iterable<PrintCampaignPhoto> searchPhotoByName(PrintCampaign printCampaign, String name, int pagenumber);
	public Iterable<PrintCampaignPhoto> searchPhotoByName(String printcampaignId, String name, int pagenumber);
	public ResultStatus editPhotoInfo(String photoId, String name, String desc, StringBuffer error);
	public ResultStatus editPhotoName(String photoId, String name, StringBuffer error);
	public ResultStatus editPhotoDescription(String photoId, String name, StringBuffer error);
	public ResultStatus deletePhoto(String photoId, StringBuffer error);
}
