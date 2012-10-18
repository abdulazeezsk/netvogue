package org.netvogue.server.neo4japi.service;

import java.util.Map;
import java.util.Set;

import org.netvogue.server.neo4japi.common.Constants;
import org.netvogue.server.neo4japi.common.NetworkStatus;
import org.netvogue.server.neo4japi.common.ResultStatus;
import org.netvogue.server.neo4japi.common.USER_TYPE;
import org.netvogue.server.neo4japi.common.Utils;
import org.netvogue.server.neo4japi.domain.Editorial;
import org.netvogue.server.neo4japi.domain.Gallery;
import org.netvogue.server.neo4japi.domain.Linesheet;
import org.netvogue.server.neo4japi.domain.PrintCampaign;
import org.netvogue.server.neo4japi.domain.Collection;
import org.netvogue.server.neo4japi.domain.Stylesheet;
import org.netvogue.server.neo4japi.domain.User;
import org.netvogue.server.neo4japi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;

public class UserServiceImpl implements UserService{
	@Autowired Neo4jTemplate		neo4jTemplate;
	@Autowired UserRepository		userRepo;
	
	public ResultStatus SaveUser(User user, String error){
		try {
			//New Categories node will be created an relationship will also be added for this.
			//Saving it through Template instead of boutiquerepo so that categories node can also be saved
			neo4jTemplate.save(user);
			System.out.println("Updated User Successfully");
			return ResultStatus.SUCCESS;
		} catch(Exception e) {
			System.out.println("There was an error for" + user.getEmail() + " - " + e.toString());
			error = e.toString();
			return ResultStatus.FAILURE;
		}
	}
	
	public User getUserByUsername(String username) {
		return userRepo.findByusername(username);
	}
	
	public ResultStatus ValidateEmailAndId(String email, Long id) {
		if(email.isEmpty()) {
			return ResultStatus.FAILURE;
		}
		User b = userRepo.findByemailAndId(email, id);
		if(null == b) {
			return ResultStatus.SUCCESS;
		}
		return ResultStatus.USER_EXISTS;
	}
	
	//Search related
	public Iterable<User> doBasicSearch(String query) {
		query = "username:*" + query + "* OR " + "name:*" + query + "*";
		return userRepo.doBasicSearch(query);
	}
	
	public Iterable<User> getAllUsers() {
		String query = "username:*";
		return userRepo.doBasicSearch(query);
	}
	
	public Iterable<User> doAdvancedSearch(USER_TYPE userType, String name, String location, Set<String> categories) {
		/*String query = "username:*" + name + "*";
		String cat = "";
		/*if(!name.isEmpty()) {
			query = "username:*" + name + "* OR " + "name:*" + name + "*" ;
		}
		if(!location.isEmpty()) {
			if(!query.isEmpty()) {
				query += (" AND ");
			}
			query += ("city:*" + location  + "* OR " + "country:*" + location + "*");
		}
		if(name.isEmpty() && location.isEmpty()) {
			query = "username:*";
		}
		
		if(categories.isEmpty()) {
			cat = "productline:*";
		} else {
			cat = "productline:" ;
			int index =1;
			for(String category: categories) {
				System.out.println("Category is:" + category);
				cat += category ;
				if(index++ < categories.size()) {
					cat += " OR ";
				}
			}
		}
		System.out.println(query);
		System.out.println(cat);
		return userRepo.doAdvancedSearch(cat, query);*/
		
		String query = "username:*" + name + "* OR " + "name:*" + name + "*";
		return userRepo.doBasicSearch(query);
	}
	
	//Network related
	public NetworkStatus getNetworkStatus(String username1, String username2) {
		return userRepo.getNetworkStatus(username1, username2);
	}
	
	//References
	public Iterable<ReferenceData> getReferences(String username, int pagenumber) {
		if(username.isEmpty()) 
			return null;
		return userRepo.getReferences(username, Constants.REFERENCES_PER_CALL * pagenumber, Constants.REFERENCES_PER_CALL);
	}
	
	//Queries related to galleries
	public Iterable<Gallery> GetGalleries(User user) {
		if(null != user) {
			return userRepo.getGalleries(user.getUsername());
		}
		return null;
	}
	
	public Iterable<Gallery> searchGalleryByName(User user, String name) {
		return searchGalleryByName(user.getUsername(), name);
	}
	
	public Iterable<Gallery> searchGalleryByName(String username, String name) {
		return userRepo.searchGalleryByName(username, Utils.SerializeQueryParamForSearch(name));
	}
	
	//Queries related to printcampaigns
	public Iterable<PrintCampaign> getPrintCampaigns(User user) {
		if(null != user) {
			return userRepo.getPrintCampaigns(user.getUsername());
		}
		return null;
	}
	
	public Iterable<PrintCampaign> searchPrintCampaignByName(User user, String name) {
		return searchPrintCampaignByName(user.getUsername(), name);
	}
	
	public Iterable<PrintCampaign> searchPrintCampaignByName(String username, String name) {
		return userRepo.searchPrintCampaignByName(username, Utils.SerializeQueryParamForSearch(name));
	}
	
	//Queries related to Editorials
	public Iterable<Editorial> getEditorials(User user) {
		if(null != user) {
			return userRepo.getEditorials(user.getUsername());
		}
		return null;
	}
	
	public Iterable<Editorial> searchEditorialByName(User user, String name) {
		return searchEditorialByName(user.getUsername(), name);
	}
	
	public Iterable<Editorial> searchEditorialByName(String username, String name) {
		return userRepo.searchEditorialByName(username, Utils.SerializeQueryParamForSearch(name));
	}
	
	//Queries related to collections
	public Iterable<CollectionData> getCollections(User user) {
		if(null != user) {
			if(USER_TYPE.BOUTIQUE == user.getUserType()) {
				return userRepo.getMyNetworkCollections(user.getUsername());
			} else if(USER_TYPE.BRAND == user.getUserType()) {
				return userRepo.getCollections(user.getUsername());
			}
		}
		return null;
	}
	
	public Iterable<CollectionData> searchCollectionByName(User user, String name) {
		return searchCollectionByName(user.getUsername(), name);
	}
	
	public Iterable<CollectionData> searchCollectionByName(String username, String seasonname) {
		return userRepo.searchCollectionByName(username, Utils.SerializeQueryParamForSearch(seasonname));
	}
	
	public Iterable<CollectionData> searchCollections(String username, String seasonname, String category, String brandname){
		//if(category.isEmpty() && 
		return userRepo.searchCollectionByName(username, Utils.SerializeQueryParamForSearch(seasonname));
	}
	
	public Iterable<StylesheetData> getStylesheets(User user) {
		if(null != user) {
			return userRepo.getStylesheets(user.getUsername());
		}
		return null;
	}
	
	public Iterable<StylesheetData> searchStylesheetByName(User user, String name) {
		return searchStylesheetByName(user.getUsername(), name);
	}
	
	public Iterable<StylesheetData> searchStylesheetByName(String username, String name) {
		return userRepo.searchStylesheetByName(username, name);
	}
	
	public Iterable<LinesheetData> getLinesheets(User user) {
		if(null != user) {
			if(USER_TYPE.BOUTIQUE == user.getUserType()) {
				return userRepo.getMyNetworkLinesheets(user.getUsername());
			} else if(USER_TYPE.BRAND == user.getUserType()) {
				return userRepo.getLinesheets(user.getUsername());
			}
			return userRepo.getLinesheets(user.getUsername());
		}
		return null;
	}
	
	public Iterable<LinesheetData> searchLinesheetByName(User user, String name) {
		return searchLinesheetByName(user.getUsername(), name);
	}
	
	public Iterable<LinesheetData> searchLinesheetByName(String username, String name) {
		return userRepo.searchLinesheetByName(username, name);
	}
}
