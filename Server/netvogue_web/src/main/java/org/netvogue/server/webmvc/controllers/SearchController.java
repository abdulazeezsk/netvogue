package org.netvogue.server.webmvc.controllers;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.netvogue.server.neo4japi.common.USER_TYPE;
import org.netvogue.server.neo4japi.domain.User;
import org.netvogue.server.neo4japi.service.UserService;
import org.netvogue.server.webmvc.domain.SearchResponse;
import org.netvogue.server.webmvc.security.NetvogueUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SearchController {

	@Autowired NetvogueUserDetailsService userDetailsService;
	@Autowired UserService 			userService;
	@Autowired ConversionService	conversionService;

	@RequestMapping(value="basicsearch", method=RequestMethod.GET)
	public @ResponseBody Set<SearchResponse> doBasicSearch(@RequestParam("query") String query) {
		System.out.println("Get users: " + query);
		Set<SearchResponse> response = new LinkedHashSet<SearchResponse>();
		if(!query.isEmpty()) {
			
			Iterable<User> users = userService.doBasicSearch(query);
			
			if(null == users) {
				System.out.println("No users found: " + query);
				return response;
			}
			Iterator<User> first = users.iterator();
			while ( first.hasNext() ){
				User dbUser = first.next() ;
				System.out.println("user name: " + dbUser.getUsername());
				response.add(conversionService.convert(dbUser, SearchResponse.class));
			}
		}
		return response;
	}

	@RequestMapping(value="advancedsearch", method=RequestMethod.GET)
	public @ResponseBody Set<SearchResponse> doAdvancedSearch(
							@RequestParam("name") String name, @RequestParam("location") String location,
							@RequestParam("categories") List<String> categories, 
							@RequestParam("usertype")	String searchtype	) {
		
		System.out.println("Get users: name:" + name);
		System.out.println("\n location:" + location);
		System.out.println("\n categories:" + categories.toString());
		System.out.println("\n user type:" + searchtype);
		Set<SearchResponse> response = new LinkedHashSet<SearchResponse>();
		
		USER_TYPE user_type;
		if(searchtype.equals("true")) {
			user_type = USER_TYPE.BRAND;
		} else {
			user_type = USER_TYPE.BOUTIQUE;
		}
		
		Iterable<User> users = userService.doAdvancedSearch(user_type, name, location, categories);
		
		if(null == users) {
			System.out.println("No users found: ");
			return response;
		}
		Iterator<User> first = users.iterator();
		while ( first.hasNext() ){
			User dbUser = first.next() ;
			System.out.println("user name: " + dbUser.getUsername());
			response.add(conversionService.convert(dbUser, SearchResponse.class));
		}
		
		return response;
	}
}
