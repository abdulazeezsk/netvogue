package org.netvogue.server.neo4japi.service;

import org.netvogue.server.neo4japi.common.ResultStatus;
import org.netvogue.server.neo4japi.domain.Stylesheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;

public class StylesheetServiceImpl implements StylesheetService {

	@Autowired Neo4jTemplate		neo4jTemplate;
	
	public ResultStatus SaveStylesheet(Stylesheet newStylesheet, String error) {
		try {
			//New Categories node will be created an relationship will also be added for this.
			//Saving it through Template instead of boutiquerepo so that categories node can also be saved
			neo4jTemplate.save(newStylesheet);
			System.out.println("Saved Stylesheet Successfully with styles:" + newStylesheet.getStyles().size());
			return ResultStatus.SUCCESS;
		} catch(Exception e) {
			System.out.println("There was an error for" + newStylesheet.getStylesheetname() + " - " + e.toString());
			error = e.toString();
			return ResultStatus.FAILURE;
		}
	}
}
