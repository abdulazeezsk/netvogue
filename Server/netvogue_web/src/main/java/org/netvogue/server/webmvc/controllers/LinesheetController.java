package org.netvogue.server.webmvc.controllers;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.netvogue.server.aws.core.UploadManager;
import org.netvogue.server.neo4japi.common.ProductLines;
import org.netvogue.server.neo4japi.common.ResultStatus;
import org.netvogue.server.neo4japi.common.USER_TYPE;
import org.netvogue.server.neo4japi.domain.Category;
import org.netvogue.server.neo4japi.domain.User;
import org.netvogue.server.neo4japi.service.BoutiqueService;
import org.netvogue.server.neo4japi.service.LinesheetService;
import org.netvogue.server.neo4japi.service.UserService;
import org.netvogue.server.webmvc.domain.JsonResponse;
import org.netvogue.server.webmvc.domain.Linesheet;
import org.netvogue.server.webmvc.domain.LinesheetJSONRequest;
import org.netvogue.server.webmvc.domain.Linesheets;
import org.netvogue.server.webmvc.domain.Photos;
import org.netvogue.server.webmvc.domain.StylesheetJsonRequest;
import org.netvogue.server.webmvc.security.NetvogueUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LinesheetController {
	@Autowired NetvogueUserDetailsService 	userDetailsService;
	@Autowired BoutiqueService  			boutiqueService;
	@Autowired UserService 					userService;
	@Autowired LinesheetService				linesheetService;
	@Autowired ConversionService			conversionService;

	@Autowired
	private UploadManager uploadManager;

	@RequestMapping(value="getlinesheets", method=RequestMethod.GET)
	public @ResponseBody Linesheets GetLinesheets(@ModelAttribute("profileid") String profileid, 
												@RequestParam("linesheetname") String linesheetname,
												@RequestParam("category") String categoryname) {
		System.out.println("Get Linesheets: " + linesheetname);
		Linesheets linesheets = new Linesheets();
		User loggedinUser = userDetailsService.getUserFromSession();
		if(profileid.isEmpty()) {
			linesheets.setName(loggedinUser.getName());
			Set<Linesheet> linesheetTemp = new LinkedHashSet<Linesheet>();
			Iterable<org.netvogue.server.neo4japi.domain.Linesheet> dbLinesheets;
			if(linesheetname.isEmpty()) {
				dbLinesheets = userService.getLinesheets(loggedinUser);
			} else {
				dbLinesheets = userService.getLinesheets(loggedinUser);
				//dbCollections = userService.searchCollections(loggedinUser.getUsername(), stylesheetname, categoryname);
			}
			if(null == dbLinesheets) {
				return linesheets;
			}
			Iterator<org.netvogue.server.neo4japi.domain.Linesheet> first = dbLinesheets.iterator();
			while ( first.hasNext() ){
				org.netvogue.server.neo4japi.domain.Linesheet dbSheet = first.next() ;
				System.out.println("line sheet name" + dbSheet.getLinesheetname());
				linesheetTemp.add(conversionService.convert(dbSheet, Linesheet.class));
			}
			linesheets.setLinesheets(linesheetTemp);
		}
		return linesheets;
	}
	
	@RequestMapping(value="linesheet/getphotos", method=RequestMethod.GET)
	public @ResponseBody Photos GetPhotos(@ModelAttribute("profileid") String profileid, 
										  @RequestParam("galleryid") String galleryid,
										  @RequestParam("photoname") String photoname
											 ) {
		System.out.println("Get collection Photos: " + photoname);
		Photos photos = new Photos();
		/*User loggedinUser = userDetailsService.getUserFromSession();
		if(galleryid.isEmpty()) {
			return photos;
		}
		
		if(profileid.isEmpty()) {
			photos.setName(loggedinUser.getName());
			photos.setGalleryname(collectionService.getCollection(galleryid).getCollectionseasonname());
			Set<PhotoWeb> photosTemp = new LinkedHashSet<PhotoWeb>();
			Iterable<CollectionPhoto> dbPhotos;
			if(photoname.isEmpty()) {
				dbPhotos = collectionService.getPhotos(galleryid);
			} else {
				dbPhotos = collectionService.searchPhotoByName(galleryid, photoname);
			}
			if(null == dbPhotos) {
				return photos;
			}
			Iterator<CollectionPhoto> first = dbPhotos.iterator();
			while ( first.hasNext() ){
				CollectionPhoto dbPhoto = first.next() ;
				photosTemp.add(conversionService.convert(dbPhoto, PhotoWeb.class));
			}
			photos.setPhotos(photosTemp);
		}*/
		return photos;
	}
	
	@RequestMapping(value="linesheet/create", method=RequestMethod.POST)
	public @ResponseBody JsonResponse CreateLinesheet(@RequestBody LinesheetJSONRequest request) {
		System.out.println("Create Linesheet:" + request.toString());
		String error = "";
		JsonResponse response = new JsonResponse();
		
		User loggedinUser = userDetailsService.getUserFromSession();
		if(loggedinUser.getUserType() != USER_TYPE.BRAND) {
			response.setError("Only brands can create Linesheets");
			return response;
		}
		org.netvogue.server.neo4japi.domain.Linesheet newLinesheet = 
				new org.netvogue.server.neo4japi.domain.Linesheet(request.getName(), loggedinUser, request.getDeliverydate()); 
		
		if(null != request.getCategory()) {
			ProductLines productLine = ProductLines.getValueOf(request.getCategory());
			Category cat = boutiqueService.getOrCreateCategory(productLine);
			newLinesheet.setProductcategory(cat);
		}
		if(ResultStatus.SUCCESS == linesheetService.SaveLinesheet(newLinesheet, error)) {  
			response.setStatus(true);
			response.setIdcreated(newLinesheet.getLinesheetid());
		}
		else
			response.setError(error);
		
		return response;
	}
	
	@RequestMapping(value="linesheet/edit", method=RequestMethod.POST)
	public @ResponseBody JsonResponse EditLinesheet(@RequestBody LinesheetJSONRequest request) {
		System.out.println("Edit Linesheet");
		String error = "";
		JsonResponse response = new JsonResponse();
		
		if(null == request.getId() || request.getId().isEmpty()) {
			response.setError("linesheet Id is empty");
			return response;
		} else if(null == request.getName()) {
			response.setError("new name is empty");
			return response;
		}
		
		if(ResultStatus.SUCCESS == linesheetService.editLinesheet(request.getId(), request.getName(), request.getDeliverydate(), error))   
			response.setStatus(true);
		else
				response.setError(error);	
		return response;
	}
	
	//Think about the categories as well
	@RequestMapping(value="linesheet/delete", method=RequestMethod.POST)
	public @ResponseBody JsonResponse DeleteLinesheet(@RequestBody String linesheetId) {
		System.out.println("Delete Linesheet:"+ linesheetId);
		String error = "";
		JsonResponse response = new JsonResponse();
		
		if(null == linesheetId || linesheetId.isEmpty()) {
			response.setError("linesheetId is empty");
			return response;
		}
		if(ResultStatus.SUCCESS == linesheetService.deleteLinesheet(linesheetId, error)) {  
			response.setStatus(true);
		}
		else
			response.setError(error);
		
		return response;
	}
}