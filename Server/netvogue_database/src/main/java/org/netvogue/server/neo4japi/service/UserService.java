package org.netvogue.server.neo4japi.service;

import org.netvogue.server.neo4japi.common.ResultStatus;
import org.netvogue.server.neo4japi.domain.Collection;
import org.netvogue.server.neo4japi.domain.Editorial;
import org.netvogue.server.neo4japi.domain.Gallery;
import org.netvogue.server.neo4japi.domain.Linesheet;
import org.netvogue.server.neo4japi.domain.PrintCampaign;
import org.netvogue.server.neo4japi.domain.Stylesheet;
import org.netvogue.server.neo4japi.domain.User;

public interface UserService {
	ResultStatus SaveUser(User user, String error);
	ResultStatus ValidateEmailAndId(String email, Long id);

	//Search related
	public Iterable<User> doBasicSearch(String query);
	
	//Gallery related
	public Iterable<Gallery> GetGalleries(User user);
	public Iterable<Gallery> searchGalleryByName(User user, String name);
	public Iterable<Gallery> searchGalleryByName(String username, String name);
	
	//Queries related to Printcampaigns
	public Iterable<PrintCampaign> getPrintCampaigns(User user);
	public Iterable<PrintCampaign> searchPrintCampaignByName(User user, String name);
	public Iterable<PrintCampaign> searchPrintCampaignByName(String username, String name);
	
	//Queries related to Editorials
	public Iterable<Editorial> getEditorials(User user);
	public Iterable<Editorial> searchEditorialByName(User user, String name);
	public Iterable<Editorial> searchEditorialByName(String username, String name);
	
	//Queries related to collections
	public Iterable<Collection> getCollections(User user);
	public Iterable<Collection> searchCollectionByName(User user, String name);
	public Iterable<Collection> searchCollectionByName(String username, String name);
	public Iterable<Collection> searchCollections(String username, String seasonname, String category, String brandname);

	//Queries related to Stylesheets
	public Iterable<Stylesheet> getStylesheets(User user);
	public Iterable<Stylesheet> searchStylesheetByName(User user, String name);
	public Iterable<Stylesheet> searchStylesheetByName(String username, String name);
	
	//Queries related to Linesheets
	public Iterable<Linesheet> getLinesheets(User user);
	public Iterable<Linesheet> searchLinesheetByName(User user, String name);
	public Iterable<Linesheet> searchLinesheetByName(String username, String name);
}
