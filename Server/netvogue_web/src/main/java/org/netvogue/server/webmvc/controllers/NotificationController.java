package org.netvogue.server.webmvc.controllers;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.netvogue.server.neo4japi.common.ResultStatus;
import org.netvogue.server.neo4japi.domain.User;
import org.netvogue.server.neo4japi.service.NotificationService;
import org.netvogue.server.webmvc.converters.ImageURLsConverter;
import org.netvogue.server.webmvc.domain.ImageURLsResponse;
import org.netvogue.server.webmvc.domain.JsonResponse;
import org.netvogue.server.webmvc.domain.Notification;
import org.netvogue.server.webmvc.domain.Notifications;
import org.netvogue.server.webmvc.security.NetvogueUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NotificationController {
	@Autowired NotificationService notificationService;
	@Autowired ImageURLsConverter	imageURLsConverter;
	@Autowired ConversionService	conversionService;
	@Autowired NetvogueUserDetailsService userDetailsService; 

	@RequestMapping(value="notifications/unread", method=RequestMethod.GET)
	public @ResponseBody Notifications getUnreadNotifications() {
		System.out.println("Get unread Notifications: " );
		
		User user = userDetailsService.getUserFromSession();
		
		Notifications response = new Notifications();
		
		response.setName(user.getName());
		response.setProfileid(user.getUsername());
		response.setProfilepic(imageURLsConverter.convert(user.getProfilePicLink(), user.getUsername()));
		Set<Notification> notificationsTemp = new LinkedHashSet<Notification>();
		
		String username = user.getUsername();
		String error = "";
		Iterable<org.netvogue.server.neo4japi.domain.Notification> dbNotifications = notificationService.getUnreadNotifications(username, error);
		if(null == dbNotifications) {
			System.out.println("No notifications found: ");
			return response;
		}
		
		Iterator<org.netvogue.server.neo4japi.domain.Notification> first = dbNotifications.iterator();
		while ( first.hasNext() ){
			org.netvogue.server.neo4japi.domain.Notification dbNotification= first.next() ;
			notificationsTemp.add(conversionService.convert(dbNotification, Notification.class));
		}		
		
		response.setNotifications(notificationsTemp);
		response.setUnreadnotifications(notificationsTemp.size());
		System.out.println("Sent unread Notifications:" + response.getNotifications().size());
		return response;
	}
	
	@RequestMapping(value="notifications/get", method=RequestMethod.GET)
	public @ResponseBody Notifications getNotifications() {
		System.out.println("Get Notifications: " );
		
		User user = userDetailsService.getUserFromSession();
		
		Notifications response = new Notifications();
		
		response.setName(user.getName());
		response.setProfilepic(imageURLsConverter.convert(user.getProfilePicLink(), user.getUsername()));
		Set<Notification> notificationsTemp = new LinkedHashSet<Notification>();
		
		String username = user.getUsername();
		String error = "";
		Iterable<org.netvogue.server.neo4japi.domain.Notification> dbNotifications = notificationService.getAllNotifications(username, error);
		if(null == dbNotifications) {
			System.out.println("No notifications found: ");
			return response;
		}
		
		Iterator<org.netvogue.server.neo4japi.domain.Notification> first = dbNotifications.iterator();
		while ( first.hasNext() ){
			org.netvogue.server.neo4japi.domain.Notification dbNotification= first.next() ;
			notificationsTemp.add(conversionService.convert(dbNotification, Notification.class));
		}		
		
		response.setNotifications(notificationsTemp);
		System.out.println("Sent Notifications:" + response.getNotifications().size());
		return response;
	}
	
	@RequestMapping(value="notifications/markread", method=RequestMethod.GET)
	public @ResponseBody JsonResponse markNotifications(@RequestBody String id) {
		System.out.println("Mark Notification read: id is:" + id);
		String error = "";
		JsonResponse response = new JsonResponse();
		if(id.isEmpty()) {
			response.setError("notification id is empty");
			return response;
		}
		
		if(ResultStatus.SUCCESS == notificationService.markNotificationRead(id, error)) {  
			response.setStatus(true);
		}
		else
			response.setError(error);
		
		System.out.println("Notification read");
		return response;
	}
	
	@RequestMapping(value="notifications/discardnetwork", method=RequestMethod.POST)
	public @ResponseBody JsonResponse discardNotification(@RequestBody String id) {
		System.out.println("Discard Notification: id is:" + id);
		String error = "";
		JsonResponse response = new JsonResponse();
		if(id.isEmpty()) {
			response.setError("notification id is empty");
			return response;
		}
		
		if(ResultStatus.SUCCESS == notificationService.discardNetwork(id, error)) {  
			response.setStatus(true);
		}
		else
			response.setError(error);
		
		System.out.println("Notification Discarded");
		return response;
	}
}
