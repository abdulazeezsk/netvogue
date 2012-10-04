package org.netvogue.server.neo4japi.service;

import java.util.Date;

import org.netvogue.server.neo4japi.common.ResultStatus;
import org.netvogue.server.neo4japi.domain.Linesheet;
import org.netvogue.server.neo4japi.domain.Style;
import org.netvogue.server.neo4japi.repository.LinesheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;

public class LinesheetServiceImpl implements LinesheetService {

	@Autowired Neo4jTemplate		neo4jTemplate;
	@Autowired LinesheetRepository  linesheetRepo;
	
	public ResultStatus SaveLinesheet(Linesheet newLinesheet, String error) {
		try {
			//New Categories node will be created an relationship will also be added for this.
			//Saving it through Template instead of boutiquerepo so that categories node can also be saved
			neo4jTemplate.save(newLinesheet);
			System.out.println("Saved Linesheet Successfully with styles:" + newLinesheet.getStyles().size());
			return ResultStatus.SUCCESS;
		} catch(Exception e) {
			System.out.println("There was an error for" + newLinesheet.getLinesheetname() + " - " + e.toString());
			error = e.toString();
			return ResultStatus.FAILURE;
		}
	}
	
	public Linesheet getLinesheet(String linesheetId) {
		//galleryRepo.findByPropertyValue(arg0, galleryId);
		return linesheetRepo.getLinesheet(linesheetId);
	}
	
	public ResultStatus editLinesheet(String linesheetId, String name, Date deliverydate, String error) {
		try {
			linesheetRepo.editLinesheet(linesheetId, name, deliverydate);
			return ResultStatus.SUCCESS;
		} catch(Exception e) {
			System.out.println("There was an error while editing linesheet" + linesheetId + " - " + e.toString());
			error = e.toString();
			return ResultStatus.FAILURE;
		}
	}
	
	public ResultStatus deleteLinesheet(String linesheetId, String error)  {
		try {
			linesheetRepo.deleteLinesheet(linesheetId);
			System.out.println("deleted linesheet:" + linesheetId);
			return ResultStatus.SUCCESS;
		} catch(Exception e) {
			System.out.println("There was an error while deleting linesheet:" + linesheetId + " - " + e.toString());
			error = e.toString();
			return ResultStatus.FAILURE;
		}
	}
	
	public Iterable<Style> getStyles(String linesheetId) {
		if(!linesheetId.isEmpty()) {
			return linesheetRepo.getStyles(linesheetId);
		}
		return null;
	}
	public ResultStatus deleteStyle(String styleId, String error)  {
		if(null == styleId || styleId.isEmpty()){
			error = "styleId is empty";
			return ResultStatus.FAILURE;
		}
		try {
			linesheetRepo.deleteStyle(styleId);
			return ResultStatus.SUCCESS;
		} catch(Exception e) {
			System.out.println("There was an error while deleting Style" + styleId + " - " + e.toString());
			error = e.toString();
			return ResultStatus.FAILURE;
		}
	}
}
