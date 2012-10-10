package org.netvogue.server.webmvc.controllers;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.netvogue.server.aws.core.UploadManager;
import org.netvogue.server.neo4japi.common.ResultStatus;
import org.netvogue.server.neo4japi.domain.User;
import org.netvogue.server.neo4japi.service.StatusUpdateService;
import org.netvogue.server.neo4japi.service.UserService;
import org.netvogue.server.webmvc.domain.ContactInfo;
import org.netvogue.server.webmvc.domain.ImageURLsResponse;
import org.netvogue.server.webmvc.domain.JsonRequest;
import org.netvogue.server.webmvc.domain.JsonResponse;
import org.netvogue.server.webmvc.domain.StatusUpdate;
import org.netvogue.server.webmvc.domain.StatusUpdates;
import org.netvogue.server.webmvc.security.NetvogueUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StatusUpdateController {
	
	@Autowired NetvogueUserDetailsService userDetailsService;
	@Autowired UserService 			userService;
	@Autowired ConversionService	conversionService;
	@Autowired StatusUpdateService	statusUpdateService; 
	@Autowired UploadManager 		uploadManager;
		
	@RequestMapping(value="getstatusupdates", method=RequestMethod.GET)
	public @ResponseBody StatusUpdates getStatusUpdates(@ModelAttribute("profileid") String profileid) {
		System.out.println("Get Statuspupdates: " + profileid);
		StatusUpdates updates = new StatusUpdates();
		User user = userDetailsService.getUserFromSession();
		if(!profileid.isEmpty()) {
			user = userService.getUserByUsername(profileid);
			if(user == null) {
				return updates;
			}
		}

		updates.setName(user.getName());
		//Set profile pic
		if(null != user.getProfilePicLink()) {
			updates.setProfilepic(conversionService.convert(user.getProfilePicLink(), ImageURLsResponse.class));
		}
	
		//Get ContactInfo
		ContactInfo contactInfo = new ContactInfo();
		contactInfo.setAddress(user.getAddress());
		contactInfo.setCity(user.getCity());
		contactInfo.setState(user.getState());
		contactInfo.setCountry(user.getCountry());
		contactInfo.setZip(String.valueOf(user.getZipCode()));
		contactInfo.setEmail(user.getEmail());
		contactInfo.setLandline1(String.valueOf(user.getTelephoneNo1()));
		contactInfo.setLandline2(String.valueOf(user.getTelephoneNo2()));
		contactInfo.setMobile(String.valueOf(user.getMobileNo()));
		contactInfo.setWebsite(user.getWebsite());
		contactInfo.setYearest(user.getYearofEst());
		//Add it to profile Info
		updates.setContactinfo(contactInfo);
		
		Set<StatusUpdate> updatesTemp = new LinkedHashSet<StatusUpdate>();
		
		Iterable<org.netvogue.server.neo4japi.domain.StatusUpdate> dbupdates;
		if(profileid.isEmpty()) {
			dbupdates = statusUpdateService.getAllStatusUpdates(user.getUsername());
		} else {
			dbupdates = statusUpdateService.getMyStatusUpdates(user.getUsername());
		}
		if(null == dbupdates) {
			return updates;
		}
		
		Iterator<org.netvogue.server.neo4japi.domain.StatusUpdate> first = dbupdates.iterator();
			while ( first.hasNext() ){
				org.netvogue.server.neo4japi.domain.StatusUpdate dbUpdate = first.next() ;
				System.out.println("Status update is: " + dbUpdate.getStatusUpdate());
				updatesTemp.add(conversionService.convert(dbUpdate, StatusUpdate.class));
			}
		
		updates.setUpdates(updatesTemp);
		System.out.println("Status updates returned: " + profileid);
		return updates;
	}
	
	@RequestMapping(value="statusupdate/add", method=RequestMethod.POST)
	public @ResponseBody JsonResponse addStatusUpdate(@RequestBody String status) {
		System.out.println("Add Status update: " + status);
		String error = "";
		
		User user = userDetailsService.getUserFromSession();
		JsonResponse response = new JsonResponse();
		
		if(ResultStatus.SUCCESS == statusUpdateService.newStatusUpdate(status, user.getUsername(), error))   
			response.setStatus(true);
		else
			response.setError(error);
		
		
		System.out.println("added status update");
		return response;
	}
	
	@RequestMapping(value="statusupdate/edit", method=RequestMethod.POST)
	public @ResponseBody JsonResponse editStatusUpdate(@RequestBody JsonRequest request) {
		System.out.println("edit Status update: " + request.getId());
		String error = "";
		
		JsonResponse response = new JsonResponse();
		
		if(ResultStatus.SUCCESS == statusUpdateService.editStatusUpdate(request.getId(), request.getValue(), error))   
			response.setStatus(true);
		else
			response.setError(error);
		
		
		System.out.println("edited status update");
		return response;
	}
	
	@RequestMapping(value="statusupdate/delete", method=RequestMethod.POST)
	public @ResponseBody JsonResponse deleteStatusUpdate(@RequestBody String id) {
		System.out.println("delete Status update: " + id);
		String error = "";
		
		JsonResponse response = new JsonResponse();
		
		if(ResultStatus.SUCCESS == statusUpdateService.deleteStatusUpdate(id, error))   
			response.setStatus(true);
		else
			response.setError(error);
		
		
		System.out.println("delete status update");
		return response;
	}
}