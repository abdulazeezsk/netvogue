package org.netvogue.server.webmvc.controllers;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.netvogue.server.aws.core.ImageType;
import org.netvogue.server.aws.core.Size;
import org.netvogue.server.aws.core.UploadManager;
import org.netvogue.server.neo4japi.common.ResultStatus;
import org.netvogue.server.neo4japi.common.USER_TYPE;
import org.netvogue.server.neo4japi.domain.User;
import org.netvogue.server.neo4japi.service.StatusUpdateData;
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
		
	@RequestMapping(value={"getstatusupdates", "getstatusupdates/{profileid}"}, method=RequestMethod.GET)
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
		updates.setIsbrand(USER_TYPE.BRAND == user.getUserType()?true:false);
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
		
		
		if(profileid.isEmpty()) {
			Iterable<StatusUpdateData> dbupdates;
			dbupdates = statusUpdateService.getAllStatusUpdates(user.getUsername());
		
			if(null == dbupdates) {
				return updates;
			}

			Iterator<StatusUpdateData> first = dbupdates.iterator();
			while ( first.hasNext() ){
				StatusUpdateData dbUpdate = first.next() ;
				updatesTemp.add(conversionService.convert(dbUpdate, StatusUpdate.class));
			}
		} else {
			Iterable<org.netvogue.server.neo4japi.domain.StatusUpdate> dbupdates;
			Format formatter = new SimpleDateFormat("HH:mm:ss.SSS");
			
			System.out.println("Before call: Current time is:" + formatter.format(new Date()));
			dbupdates = statusUpdateService.getMyStatusUpdates(user.getUsername());
			System.out.println("After call: Current time is:" + formatter.format(new Date()));
			
			if(null == dbupdates) {
				return updates;
			}
		
			Iterator<org.netvogue.server.neo4japi.domain.StatusUpdate> first = dbupdates.iterator();
			while ( first.hasNext() ){
				org.netvogue.server.neo4japi.domain.StatusUpdate dbUpdate = first.next() ;
				StatusUpdate newTemp = conversionService.convert(dbUpdate, StatusUpdate.class);
				newTemp.setName(user.getUsername());
				newTemp.setProfileid(user.getUsername());
				String profilepic = user.getProfilePicLink();
				if(null != profilepic) {
					String topurl = uploadManager.getQueryString(profilepic, ImageType.PROFILE_PIC, Size.PTop);
					newTemp.setLeft_url(topurl);
				}
				updatesTemp.add(newTemp);
			}
		}
		
		updates.setUpdates(updatesTemp);
		System.out.println("Status updates returned: " + updatesTemp.size());
		return updates;
	}
	
	@RequestMapping(value="statusupdate/add", method=RequestMethod.POST)
	public @ResponseBody StatusUpdate addStatusUpdate(@RequestBody String status) {
		System.out.println("Add Status update: " + status);
		StatusUpdate update = null;
		StringBuffer error = new StringBuffer();
		
		User user = userDetailsService.getUserFromSession();
		JsonResponse response = new JsonResponse();
		
		org.netvogue.server.neo4japi.domain.StatusUpdate newUpdate = statusUpdateService.newStatusUpdate(user.getUsername(), status, error);
		if(null != newUpdate) {
			update = conversionService.convert(newUpdate, StatusUpdate.class);
			update.setName(user.getName());
			update.setProfileid(user.getUsername());
			String profilepic = user.getProfilePicLink();
			if(null != profilepic) {
				String topurl = uploadManager.getQueryString(profilepic, ImageType.PROFILE_PIC, Size.PTop);
				update.setLeft_url(topurl);
			}
			
			/*ObjectMapper mapper = new ObjectMapper();
			PusherChannel pusher= new PusherChannel(profileid);
			try{
				String notificationToSent = mapper.writeValueAsString(update);
				pusher.pushEvent("statusupdate", notificationToSent);
			} catch (Exception e) {
				System.out.println("Error in pusher" + error);
				return update;
			}*/
			response.setStatus(true);
		} else {
			response.setError(error.toString());
		}
		
		System.out.println("added status update" + error);
		return update;
	}
	
	@RequestMapping(value="statusupdate/edit", method=RequestMethod.POST)
	public @ResponseBody JsonResponse editStatusUpdate(@RequestBody JsonRequest request) {
		System.out.println("edit Status update: " + request.getId());
		StringBuffer error = new StringBuffer();
		
		JsonResponse response = new JsonResponse();
		
		if(ResultStatus.SUCCESS == statusUpdateService.editStatusUpdate(request.getId(), request.getValue(), error))   
			response.setStatus(true);
		else
			response.setError(error.toString());
		
		
		System.out.println("edited status update");
		return response;
	}
	
	@RequestMapping(value="statusupdate/delete", method=RequestMethod.POST)
	public @ResponseBody JsonResponse deleteStatusUpdate(@RequestBody String id) {
		System.out.println("delete Status update: " + id);
		StringBuffer error = new StringBuffer();
		
		JsonResponse response = new JsonResponse();
		
		if(ResultStatus.SUCCESS == statusUpdateService.deleteStatusUpdate(id, error))   
			response.setStatus(true);
		else
			response.setError(error.toString());
		
		
		System.out.println("delete status update");
		return response;
	}
}