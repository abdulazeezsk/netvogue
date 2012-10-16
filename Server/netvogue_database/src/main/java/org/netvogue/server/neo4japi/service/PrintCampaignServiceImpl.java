package org.netvogue.server.neo4japi.service;

import org.netvogue.server.neo4japi.common.ResultStatus;
import org.netvogue.server.neo4japi.common.Utils;
import org.netvogue.server.neo4japi.domain.PrintCampaign;
import org.netvogue.server.neo4japi.domain.PrintCampaignPhoto;
import org.netvogue.server.neo4japi.repository.PrintCampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;

public class PrintCampaignServiceImpl implements PrintCampaignService {

	@Autowired Neo4jTemplate			neo4jTemplate;
	@Autowired PrintCampaignRepository	printcampaignRepo;

	public ResultStatus SavePrintCampaign(PrintCampaign newPrintCampaign, StringBuffer error) {
		try {
			//New Categories node will be created an relationship will also be added for this.
			//Saving it through Template instead of boutiquerepo so that categories node can also be saved
			neo4jTemplate.save(newPrintCampaign);
			System.out.println("Saved Print Campaign Successfully with Photos:" + newPrintCampaign.getPhotosAdded().size());
			return ResultStatus.SUCCESS;
		} catch(Exception e) {
			System.out.println("There was an error for" + newPrintCampaign.getPrintcampaignname() + " - " + e.toString());
			error.append(e.toString());
			return ResultStatus.FAILURE;
		}
	}
	
	public PrintCampaign getPrintCampaign(String printcampaignId) {
		//galleryRepo.findByPropertyValue(arg0, galleryId);
		return printcampaignRepo.getPrintCampaign(printcampaignId);
	}
	
	public ResultStatus editPrintCampaign(String printCampaignId, String name, String desc, StringBuffer error) {
		try {
			printcampaignRepo.editPrintCampaign(printCampaignId, name, desc);
			return ResultStatus.SUCCESS;
		} catch(Exception e) {
			System.out.println("There was an error while editing print campaign" + printCampaignId + " - " + e.toString());
			error.append(e.toString());
			return ResultStatus.FAILURE;
		}
	}
	
	public ResultStatus editPrintCampaignName(String printCampaignId, String name, StringBuffer error) {
		try {
			printcampaignRepo.editPrintCampaignName(printCampaignId, name);
			return ResultStatus.SUCCESS;
		} catch(Exception e) {
			System.out.println("There was an error while editing print campaign" + printCampaignId + " - " + e.toString());
			error.append(e.toString());
			return ResultStatus.FAILURE;
		}
	}
	
	public ResultStatus setProfilepic(String printcampaignid, String uniqueid, StringBuffer error) {
		try {
			printcampaignRepo.setProfilepic(printcampaignid, uniqueid);
			System.out.println("Profilepic has been set:" + printcampaignid);
			return ResultStatus.SUCCESS;
		} catch(Exception e) {
			System.out.println("There was an error while setting profile pic:" + printcampaignid + " - " + e.toString());
			error.append(e.toString());
			return ResultStatus.FAILURE;
		}
	}
	public ResultStatus deletePrintCampaign(String printCampaignId, StringBuffer error)  {
		try {
			printcampaignRepo.deletePrintCampaign(printCampaignId);
			System.out.println("deleted gallery:" + printCampaignId);
			return ResultStatus.SUCCESS;
		} catch(Exception e) {
			System.out.println("There was an error while deleting print campaign:" + printCampaignId + " - " + e.toString());
			error.append(e.toString());
			return ResultStatus.FAILURE;
		}
	}
	
	public Iterable<PrintCampaignPhoto> getPhotos(String printcampaignId) {
		if(!printcampaignId.isEmpty()) {
			return printcampaignRepo.getPhotos(printcampaignId);
		}
		return null;
	}
	
	public Iterable<PrintCampaignPhoto> searchPhotoByName(PrintCampaign printcampaign, String name) {
		return searchPhotoByName(printcampaign.getPrintcampaignid(), name);
	}
	
	public Iterable<PrintCampaignPhoto> searchPhotoByName(String printcampaignId, String name) {
		return printcampaignRepo.searchPhotosByName(printcampaignId, Utils.SerializeQueryParamForSearch(name));
	}
	
	public ResultStatus editPhotoInfo(String photoId, String name, String seasonname, StringBuffer error) {
		if(null == photoId || photoId.isEmpty()) {
			error.append("photoid is empty");
			return ResultStatus.FAILURE;
		} else if(null == name || null == seasonname) {
			error.append("name/season name is empty");
			return ResultStatus.FAILURE;
		}
		try {
			printcampaignRepo.editPhotoInfo(photoId, name, seasonname);
			return ResultStatus.SUCCESS;
		} catch(Exception e) {
			System.out.println("There was an error while editing photo name" + photoId + " - " + e.toString());
			error.append(e.toString());
			return ResultStatus.FAILURE;
		}
	}
	
	public ResultStatus editPhotoName(String photoId, String name, StringBuffer error) {
		try {
			printcampaignRepo.editPhotoName(photoId, name);
			return ResultStatus.SUCCESS;
		} catch(Exception e) {
			System.out.println("There was an error while editing photo name" + photoId + " - " + e.toString());
			error.append(e.toString());
			return ResultStatus.FAILURE;
		}
	}
	
	public ResultStatus editPhotoDescription(String photoId, String desc, StringBuffer error) {
		try {
			printcampaignRepo.editPhotoDescription(photoId, desc);
			return ResultStatus.SUCCESS;
		} catch(Exception e) {
			System.out.println("There was an error while editing photo desc" + photoId + " - " + e.toString());
			error.append(e.toString());
			return ResultStatus.FAILURE;
		}
	}
	
	
	public ResultStatus deletePhoto(String photoId, StringBuffer error)  {
		if(null == photoId || photoId.isEmpty()){
			error.append("Photoid is empty");
			return ResultStatus.FAILURE;
		}
		try {
			printcampaignRepo.deletePhoto(photoId);
			return ResultStatus.SUCCESS;
		} catch(Exception e) {
			System.out.println("There was an error while deleting photo" + photoId + " - " + e.toString());
			error.append(e.toString());
			return ResultStatus.FAILURE;
		}
	}
}
