package org.netvogue.server.neo4japi.service;

import java.util.List;

import org.netvogue.server.common.ResultStatus;
import org.netvogue.server.neo4japi.domain.Style;
import org.netvogue.server.neo4japi.domain.Stylesheet;

public interface StylesheetService {
	public ResultStatus SaveStylesheet(Stylesheet newStylesheet, String error);
	public ResultStatus SaveStyle(Style newStyle, String error);
	
	public Stylesheet getStylesheet(String id);
	public ResultStatus editStylesheet(String id, String name, String error);
	public List<String> deleteStylesheet(String id, String error);
	
	public Style getStyle(String styleid, String error);
	public Iterable<StyleData> getStylesbyCategory(String username, String category);
	public Iterable<StyleData> getStyles(String StylesheetId, int pagenumber);
	public Iterable<StyleData> searchStyles(String stylesheetId, String styleno, String fabrication, 
			long fromPrice, long toPrice, int pagenumber);
	public ResultStatus deleteStyle(String styleId, Iterable<Iterable<String>> photoids, String error);
	
}
