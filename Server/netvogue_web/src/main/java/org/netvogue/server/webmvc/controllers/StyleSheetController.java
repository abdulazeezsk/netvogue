package org.netvogue.server.webmvc.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.netvogue.server.aws.core.ImageType;
import org.netvogue.server.aws.core.UploadManager;
import org.netvogue.server.neo4japi.common.ProductLines;
import org.netvogue.server.neo4japi.common.ResultStatus;
import org.netvogue.server.neo4japi.common.USER_TYPE;
import org.netvogue.server.neo4japi.domain.Category;
import org.netvogue.server.neo4japi.domain.CollectionPhoto;
import org.netvogue.server.neo4japi.domain.User;
import org.netvogue.server.neo4japi.service.BoutiqueService;
import org.netvogue.server.neo4japi.service.CollectionService;
import org.netvogue.server.neo4japi.service.StylesheetService;
import org.netvogue.server.neo4japi.service.UserService;
import org.netvogue.server.webmvc.domain.CollectionJSONRequest;
import org.netvogue.server.webmvc.domain.Collections;
import org.netvogue.server.webmvc.domain.JsonRequest;
import org.netvogue.server.webmvc.domain.JsonResponse;
import org.netvogue.server.webmvc.domain.PhotoInfoJsonRequest;
import org.netvogue.server.webmvc.domain.PhotoWeb;
import org.netvogue.server.webmvc.domain.Photos;
import org.netvogue.server.webmvc.domain.Stylesheet;
import org.netvogue.server.webmvc.domain.StylesheetJsonRequest;
import org.netvogue.server.webmvc.domain.Stylesheets;
import org.netvogue.server.webmvc.domain.UploadedFileResponse;
import org.netvogue.server.webmvc.security.NetvogueUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class StyleSheetController {

	@Autowired NetvogueUserDetailsService 	userDetailsService;
	@Autowired BoutiqueService  			boutiqueService;
	@Autowired UserService 					userService;
	@Autowired StylesheetService			stylesheetService;
	@Autowired ConversionService			conversionService;

	@Autowired
	private UploadManager uploadManager;

	@RequestMapping(value="getstylesheets", method=RequestMethod.GET)
	public @ResponseBody Stylesheets GetStylesheets(@ModelAttribute("profileid") String profileid, 
												@RequestParam("stylesheetname") String stylesheetname,
												@RequestParam("category") String categoryname) {
		System.out.println("Get Stylesheets: " + stylesheetname);
		Stylesheets stylesheets = new Stylesheets();
		User loggedinUser = userDetailsService.getUserFromSession();
		if(profileid.isEmpty()) {
			stylesheets.setName(loggedinUser.getName());
			Set<Stylesheet> stylesheetTemp = new LinkedHashSet<Stylesheet>();
			Iterable<org.netvogue.server.neo4japi.domain.Stylesheet> dbStylesheets;
			if(stylesheetname.isEmpty()) {
				dbStylesheets = userService.getStylesheets(loggedinUser);
			} else {
				dbStylesheets = userService.getStylesheets(loggedinUser);
				//dbCollections = userService.searchCollections(loggedinUser.getUsername(), stylesheetname, categoryname);
			}
			if(null == dbStylesheets) {
				return stylesheets;
			}
			Iterator<org.netvogue.server.neo4japi.domain.Stylesheet> first = dbStylesheets.iterator();
			while ( first.hasNext() ){
				org.netvogue.server.neo4japi.domain.Stylesheet dbStylesheet = first.next() ;
				System.out.println("Style sheet name" + dbStylesheet.getStylesheetname());
				stylesheetTemp.add(conversionService.convert(dbStylesheet, Stylesheet.class));
			}
			stylesheets.setStylesheets(stylesheetTemp);
		}
		return stylesheets;
	}
	
	@RequestMapping(value="stylesheet/getphotos", method=RequestMethod.GET)
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
	
	@RequestMapping(value="stylesheet/create", method=RequestMethod.POST)
	public @ResponseBody JsonResponse CreateStylesheet(@RequestBody StylesheetJsonRequest request) {
		System.out.println("Create Stylesheet");
		String error = "";
		JsonResponse response = new JsonResponse();
		
		User loggedinUser = userDetailsService.getUserFromSession();
		if(loggedinUser.getUserType() != USER_TYPE.BRAND) {
			response.setError("Only brands can create stylesheets");
			return response;
		}
		org.netvogue.server.neo4japi.domain.Stylesheet newStylesheet = new org.netvogue.server.neo4japi.domain.Stylesheet(request.getName(), loggedinUser); 
		
		if(null != request.getCategory()) {
			ProductLines productLine = ProductLines.getValueOf(request.getCategory());
			Category cat = boutiqueService.getOrCreateCategory(productLine);
			newStylesheet.setProductcategory(cat);
		}
		if(ResultStatus.SUCCESS == stylesheetService.SaveStylesheet(newStylesheet, error)) {  
			response.setStatus(true);
			response.setIdcreated(newStylesheet.getStylesheetid());
		}
		else
			response.setError(error);
		
		return response;
	}
	
	@RequestMapping(value="stylesheet/edit", method=RequestMethod.POST)
	public @ResponseBody JsonResponse EditStylesheet(@RequestBody StylesheetJsonRequest request) {
		System.out.println("Edit Stylesheet");
		String error = "";
		JsonResponse response = new JsonResponse();
		
		if(null == request.getId() || request.getId().isEmpty()) {
			response.setError("stylesheet Id is empty");
			return response;
		} else if(null == request.getName()) {
			response.setError("new name is empty");
			return response;
		}
		
		if(ResultStatus.SUCCESS == stylesheetService.editStylesheet(request.getId(), request.getName(), error))   
			response.setStatus(true);
		else
				response.setError(error);	
		return response;
	}
	
	//Think about the categories as well
	@RequestMapping(value="stylesheet/delete", method=RequestMethod.POST)
	public @ResponseBody JsonResponse DeleteStylesheet(@RequestBody String stylesheetId) {
		System.out.println("Delete Stylesheet:"+ stylesheetId);
		String error = "";
		JsonResponse response = new JsonResponse();
		
		if(null == stylesheetId || stylesheetId.isEmpty()) {
			response.setError("Stylesheetid is empty");
			return response;
		}
		
		//Make sure that styles inside this stylesheet are not part of any linesheets
		if(ResultStatus.SUCCESS == stylesheetService.deleteStylesheet(stylesheetId, error)) {  
			response.setStatus(true);
		}
		else
			response.setError(error);
		
		return response;
	}
	
	/*@RequestMapping(value="stylesheet/addphotos", method=RequestMethod.POST)
	public @ResponseBody UploadedFileResponse AddPhotostoGallery(Model model, 
			@RequestParam("files[]") List<MultipartFile> fileuploads, @RequestParam("galleryid") String galleryId) {
		System.out.println("Add photos: Gallery Id:" + galleryId + "No:of Photos:" + fileuploads.size());
		UploadedFileResponse response = new UploadedFileResponse();
		
		if(galleryId.isEmpty()) {
			response.setError("Gallery Id is empty");
			return response;
		}
		org.netvogue.server.neo4japi.domain.Collection collection = collectionService.getCollection(galleryId);
		if(null == collection) {
			response.setError("No collection is present with this id");
			return response;
		}
		
		List<PhotoWeb> JSONFileData= new ArrayList<PhotoWeb>();
		
		for ( MultipartFile fileupload : fileuploads ) {
			System.out.println("Came here" + fileupload.getOriginalFilename());
			Map<String, Object> uploadMap  = uploadManager.processUpload(fileupload, ImageType.COLLECTION);
			String imagePath = (String)uploadMap.get(UploadManager.QUERY_STRING);
			CollectionPhoto newPhoto = new CollectionPhoto((String)uploadMap.get(UploadManager.FILE_ID));
			collection.addPhotos(newPhoto);
			
			JSONFileData.add(conversionService.convert(newPhoto, PhotoWeb.class));
		}
		String error ="";
		if(ResultStatus.SUCCESS == collectionService.SaveCollection(collection, error)) {  
			response.setStatus(true);
			response.setFilesuploaded(JSONFileData);
		}
		else
			response.setError(error);
		return response;
	}
	
	@RequestMapping(value="stylesheet/editphotoinfo", method=RequestMethod.POST)
	public @ResponseBody JsonResponse EditPhotoInfo(@RequestBody PhotoInfoJsonRequest photoInfo) {
		System.out.println("Edit Photo Info:" + photoInfo.toString());
		String error = "";
		JsonResponse response = new JsonResponse();
		String photoId = photoInfo.getPhotoid();
		if(null == photoId || photoId.isEmpty())
			response.setError("photoid is empty");
		if(ResultStatus.SUCCESS == collectionService.editPhotoInfo(photoInfo.getPhotoid(), photoInfo.getPhotoname(), 
													photoInfo.getSeasonname(), error))
			response.setStatus(true);
		else
			response.setError(error);
		
		return response;
	}
	
	@RequestMapping(value="stylesheet/editphotoname", method=RequestMethod.POST)
	public @ResponseBody JsonResponse EditPhotoName(@RequestBody JsonRequest request) {
		System.out.println("Edit Photo Name");
		String error = "";
		
		JsonResponse response = new JsonResponse();
		
		if(ResultStatus.SUCCESS == collectionService.editPhotoName(request.getId(), request.getValue(), error))   
			response.setStatus(true);
		else
			response.setError(error);
		
		return response;
	}
	
	@RequestMapping(value="stylesheet/editphotoseasonname", method=RequestMethod.POST)
	public @ResponseBody JsonResponse EditPhotoSeasonName(@RequestParam("photoname") String photoname,
														  @RequestParam("seasonname") String seasonname, 
														  @RequestParam("photoid") String photoid) {
		System.out.println("Edit Photo Name");
		String error = "";
		
		JsonResponse response = new JsonResponse();
		
		if(ResultStatus.SUCCESS == collectionService.editPhotoInfo(photoid, photoname, seasonname, error))   
			response.setStatus(true);
		else
			response.setError(error);
		
		return response;
	}

	@RequestMapping(value="stylesheet/deletephoto", method=RequestMethod.POST)
	public @ResponseBody JsonResponse DeletePhoto(@RequestBody String photoid) {
		System.out.println("Delete Photo:" + photoid);
		String error = "";
		
		JsonResponse response = new JsonResponse();
		if(!photoid.isEmpty()) {
			if(ResultStatus.SUCCESS == collectionService.deletePhoto(photoid, error)) {  
				response.setStatus(true);
			}
			else
				response.setError(error);
		} else {
			response.setError("photoid is empty");
		}
		
		return response;
	}*/
}
