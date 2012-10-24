package org.netvogue.server.neo4japi.service;

import java.util.Set;

import org.netvogue.server.neo4japi.common.NetworkStatus;
import org.netvogue.server.neo4japi.common.ResultStatus;
import org.netvogue.server.neo4japi.common.USER_TYPE;
import org.netvogue.server.neo4japi.domain.Editorial;
import org.netvogue.server.neo4japi.domain.Gallery;
import org.netvogue.server.neo4japi.domain.PrintCampaign;
import org.netvogue.server.neo4japi.domain.User;

public interface UserService {
	ResultStatus SaveUser(User user, String error);
	ResultStatus ValidateEmailAndId(String email, Long id);
	public User getUserByUsername(String username);

	//Search related
	public Iterable<User> doBasicSearch(String query);
	public Iterable<User> doAdvancedSearch(USER_TYPE userType, String name, String location, Set<String> categories);
	public Iterable<User> getAllUsers();
	
	//network related
	NetworkStatus getNetworkStatus(String username1, String username2);
	
	//References
	public Iterable<ReferenceData> getReferences(String username, int pagenumber);
	
	//Trending
	
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
	public Iterable<CollectionData> getCollections(User user);
	public Iterable<CollectionData> searchCollections(User user, String seasonname, Set<String> categories, String brandname);

	//Queries related to Stylesheets
	public Iterable<StylesheetData> getStylesheets(User user);
	public Iterable<StylesheetData> searchStylesheetByName(User user, String name);
	public Iterable<StylesheetData> searchStylesheetByName(String username, String name);
	
	//Queries related to Linesheets
	public Iterable<LinesheetData> getLinesheets(User user);
	public Iterable<LinesheetData> searchLinesheetByName(User user, String name);
	public Iterable<LinesheetData> searchLinesheetByName(String username, String name);
}
