package org.netvogue.server.neo4japi.service;

import org.netvogue.server.neo4japi.common.ResultStatus;
import org.netvogue.server.neo4japi.domain.Linesheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;

public class LinesheetServiceImpl implements LinesheetService {

@Autowired Neo4jTemplate		neo4jTemplate;
	
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
}
