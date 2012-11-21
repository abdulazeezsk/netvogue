package org.netvogue.server.neo4japi.service;

import java.util.Date;
import java.util.Set;

import org.netvogue.server.common.NetworkStatus;
import org.netvogue.server.common.ResultStatus;
import org.netvogue.server.common.USER_TYPE;
import org.netvogue.server.neo4japi.domain.Editorial;
import org.netvogue.server.neo4japi.domain.EmailNotifications;
import org.netvogue.server.neo4japi.domain.Gallery;
import org.netvogue.server.neo4japi.domain.PrintCampaign;
import org.netvogue.server.neo4japi.domain.User;

public interface UserService {
	ResultStatus SaveUser(User user, StringBuffer error);
	ResultStatus ValidateEmailAndId(String email, Long id);
	public User getUserByUsername(String username);
	public User getUserByUserId(String userId);
	public UserData getUserDataByUsername(String username);
	public void getBrandsCarriedAndCategories(User user);
	public void getEmailNotifications(User user);

	//Email notifications
	public ResultStatus SaveEmailNotifications(EmailNotifications notification, StringBuffer error);
	public ResultStatus saveEmail(String userName, String email, StringBuffer error);
	      public ResultStatus savePassword(String userName, String password, StringBuffer error);
	//Search related
	public Iterable<User> doBasicSearch(String query);
	public Iterable<User> doAdvancedSearch(USER_TYPE userType, String name, String location, 
			Set<String> categories, Set<String> usersCarried, long fromPrice, long toPrice, int pagenumber);
	public Iterable<User> getAllUsers(String query);
	
	//network related
	NetworkStatus getNetworkStatus(String username1, String username2);
	
	//References
	public Iterable<ReferenceData> getReferences(String username, int pagenumber);
	
	//Trending
	
	//Gallery related
	public Iterable<Gallery> GetGalleries(User user, int pagenumber);
	public Iterable<Gallery> searchGalleryByName(User user, String name, int pagenumber);
	public Iterable<Gallery> searchGalleryByName(String username, String name, int pagenumber);
	
	//Queries related to Printcampaigns
	public Iterable<PrintCampaign> getPrintCampaigns(User user, int pagenumber);
	public Iterable<PrintCampaign> searchPrintCampaignByName(User user, String name, int pagenumber);
	public Iterable<PrintCampaign> searchPrintCampaignByName(String username, String name, int pagenumber);
	
	//Queries related to Editorials
	public Iterable<Editorial> getEditorials(User user, int pagenumber);
	public Iterable<Editorial> searchEditorialByName(User user, String name, int pagenumber);
	public Iterable<Editorial> searchEditorialByName(String username, String name, int pagenumber);
	
	//Queries related to collections
	public Iterable<CollectionData> getCollections(User user, int pagenumber);
	public Iterable<CollectionData> searchCollections(User user, String seasonname, Set<String> categories, 
													String brandname, int pagenumber);

	//Queries related to Stylesheets
	public Iterable<StylesheetData> getStylesheets(User user, int pagenumber);
	public Iterable<StylesheetData> searchStylesheets(User user, String name, Set<String> categories, int pagenumber);
	
	//Queries related to Linesheets
	public Iterable<LinesheetData> getLinesheets(User user, int pagenumber);
	public Iterable<LinesheetData> searchLinesheets(User user, String seasonname, Set<String> categories,
			 			Date fromDate, Date toDate, long fromPrice, long toPrice, String brandname, int pagenumber);
	
}
