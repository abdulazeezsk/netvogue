package org.netvogue.server.neo4japi.service;

import org.netvogue.server.neo4japi.common.Constants;
import org.netvogue.server.neo4japi.common.ResultStatus;
import org.netvogue.server.neo4japi.common.Utils;
import org.netvogue.server.neo4japi.domain.Style;
import org.netvogue.server.neo4japi.domain.Stylesheet;
import org.netvogue.server.neo4japi.repository.StylesheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;

public class StylesheetServiceImpl implements StylesheetService {

	@Autowired Neo4jTemplate		neo4jTemplate;
	@Autowired StylesheetRepository stylesheetRepo;
	
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
	
	public ResultStatus SaveStyle(Style newStyle, String error) {
		try {
			//New Categories node will be created an relationship will also be added for this.
			//Saving it through Template instead of boutiquerepo so that categories node can also be saved
			neo4jTemplate.save(newStyle);
			System.out.println("Saved Style Successfully");
			return ResultStatus.SUCCESS;
		} catch(Exception e) {
			System.out.println("There was an error for" + newStyle.getStylename());
			error = e.toString();
			return ResultStatus.FAILURE;
		}
	}
	public Stylesheet getStylesheet(String stylesheetId) {
		//galleryRepo.findByPropertyValue(arg0, galleryId);
		return stylesheetRepo.getStylesheet(stylesheetId);
	}
	
	public ResultStatus editStylesheet(String stylesheetId, String name, String error) {
		try {
			stylesheetRepo.editStylesheet(stylesheetId, name);
			return ResultStatus.SUCCESS;
		} catch(Exception e) {
			System.out.println("There was an error while editing style sheet" + stylesheetId + " - " + e.toString());
			error = e.toString();
			return ResultStatus.FAILURE;
		}
	}
	
	public ResultStatus deleteStylesheet(String stylesheetId,  Iterable<Iterable<String>> photoids, String error)  {
		try {
			photoids = stylesheetRepo.deleteStylesheet(stylesheetId);
			//stylesheetRepo.deleteStylesheet(stylesheetId);
			System.out.println("deleted style sheet:" + stylesheetId);
			return ResultStatus.SUCCESS;
		} catch(Exception e) {
			System.out.println("There was an error while deleting stylesheetId:" + stylesheetId + " - " + e.toString());
			error = e.toString();
			return ResultStatus.FAILURE;
		}
	}
	
	public Style getStyle(String styleId, String error) {
		if(!styleId.isEmpty()) {
			return stylesheetRepo.getStyle(styleId);
		} else {
			error = "empty styleid";
		}
		return null;
	}
	public Iterable<StyleData> getStyles(String stylesheetId, int pagenumber) {
		if(!stylesheetId.isEmpty()) {
			return stylesheetRepo.getStyles(stylesheetId,
					Constants.STYLEPAGE_LIMIT * pagenumber, Constants.STYLEPAGE_LIMIT);
		}
		return null;
	}
	public Iterable<StyleData> getStylesbyCategory(String username, String category) {
		if(!category.isEmpty()) {
			return stylesheetRepo.getStylesByCategory(username, category);
		}
		return null;
 	}
	public Iterable<StyleData> searchStyles(String stylesheetId, String styleno, String fabrication, 
								long fromPrice, long toPrice, int pagenumber) {
		if(!stylesheetId.isEmpty()) {
			String no = Utils.SerializePropertyParamForSearch(styleno);
			System.out.println("stylesheet ID is:" + stylesheetId);
			System.out.println("Style number is:" + no);
			System.out.println("fabrication is:" + fabrication);
		
			Long fromprice = Long.MIN_VALUE;
			Long toprice = Long.MAX_VALUE;
			if(0 == fromPrice)
				fromprice = fromPrice;
			if(0 == toPrice)
				toprice = toPrice;
			
			System.out.println("From Price is" + fromprice);
			System.out.println("To Price is" + toprice);
			return stylesheetRepo.searchStyles(stylesheetId, no, fabrication, fromprice, toprice,
					Constants.STYLEPAGE_LIMIT * pagenumber, Constants.STYLEPAGE_LIMIT);
		}
		return null;
	}
	public ResultStatus deleteStyle(String styleId, Iterable<Iterable<String>> photoids, String error)  {
		if(null == styleId || styleId.isEmpty()){
			error = "styleId is empty";
			return ResultStatus.FAILURE;
		}
		try {
			photoids = stylesheetRepo.deleteStyle(styleId);
			return ResultStatus.SUCCESS;
		} catch(Exception e) {
			System.out.println("There was an error while deleting Style" + styleId + " - " + e.toString());
			error = e.toString();
			return ResultStatus.FAILURE;
		}
	}
}
