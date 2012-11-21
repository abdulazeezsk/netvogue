package org.netvogue.server.webmvc.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.netvogue.server.aws.core.ImageType;
import org.netvogue.server.aws.core.UploadManager;
import org.netvogue.server.common.ProductLines;
import org.netvogue.server.common.ResultStatus;
import org.netvogue.server.common.USER_TYPE;
import org.netvogue.server.neo4japi.domain.Category;
import org.netvogue.server.neo4japi.domain.CollectionPhoto;
import org.netvogue.server.neo4japi.domain.User;
import org.netvogue.server.neo4japi.service.BoutiqueService;
import org.netvogue.server.neo4japi.service.CollectionData;
import org.netvogue.server.neo4japi.service.CollectionPhotoData;
import org.netvogue.server.neo4japi.service.CollectionService;
import org.netvogue.server.neo4japi.service.UserService;
import org.netvogue.server.webmvc.converters.CollectionConverter;
import org.netvogue.server.webmvc.converters.CollectionPhotoConverter;
import org.netvogue.server.webmvc.converters.ImageURLsConverter;
import org.netvogue.server.webmvc.domain.Collection;
import org.netvogue.server.webmvc.domain.CollectionJSONRequest;
import org.netvogue.server.webmvc.domain.Collections;
import org.netvogue.server.webmvc.domain.JsonRequest;
import org.netvogue.server.webmvc.domain.JsonResponse;
import org.netvogue.server.webmvc.domain.PhotoInfoJsonRequest;
import org.netvogue.server.webmvc.domain.PhotoWeb;
import org.netvogue.server.webmvc.domain.Photos;
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
public class CollectionController {
	@Autowired NetvogueUserDetailsService 	userDetailsService;
	@Autowired BoutiqueService  boutiqueService;
	@Autowired UserService 					userService;
	@Autowired ImageURLsConverter			imageURLsConverter;
	@Autowired CollectionService			collectionService;
	@Autowired CollectionConverter			collectionConverter;
	@Autowired CollectionPhotoConverter		collectionPhotoConverter;
	@Autowired ConversionService			conversionService;

	@Autowired
	private UploadManager uploadManager;

	@RequestMapping(value={"/getcollections", "/getcollections/{profileid}"}, method=RequestMethod.GET)
	public @ResponseBody Collections GetCollections(@ModelAttribute("profileid") String profileid, 
									@RequestParam(value="galleryname", required=false) String galleryname,
									@RequestParam(value="category", required=false) String categories,
									@RequestParam(value="brandname", required=false) String brandname,
									@RequestParam(value="pagenumber", required=false, defaultValue="0") int pagenumber) {
		System.out.println("Get Collections: " + galleryname+ ":" + categories + ":" + brandname);
		Collections collections = new Collections();
		
		User user;
		if(!profileid.isEmpty()) {
			user = userService.getUserByUsername(profileid);
			if(user == null || USER_TYPE.BOUTIQUE == user.getUserType()) {
				return collections;
			}
		} else {
			 user = userDetailsService.getUserFromSession();
		}
		
		if(0 == pagenumber) {
			collections.setName(user.getName());
			collections.setProfilepic(imageURLsConverter.convert(user.getProfilePicLink(), user.getUsername()));
		}
		Set<Collection> collectionTemp = new LinkedHashSet<Collection>();
		Iterable<CollectionData> dbCollections;
		if(galleryname.isEmpty() && categories.isEmpty() && brandname.isEmpty()) {
			dbCollections = userService.getCollections(user, pagenumber);
		} else {
			Set<String> productlines = new HashSet<String>();
			List<String> categoriesafter =  Arrays.asList(categories.split(","));
			for(String productline: categoriesafter) {
				ProductLines productLine = ProductLines.getValueOf(productline);
				if(null != productLine) {
					System.out.println("product line is:" + productLine.toString());
					productlines.add(productLine.toString());
				}
				else
					System.out.println("product line is null");
			}
			dbCollections = userService.searchCollections(user, galleryname, productlines, brandname, pagenumber);
		}
		if(null == dbCollections) {
			return collections;
		}
		Iterator<CollectionData> first = dbCollections.iterator();
		while ( first.hasNext() ){
			CollectionData dbCollection = first.next();
			
			Collection collection = collectionConverter.convert(dbCollection.getCollection(), dbCollection.getUsername());
			collection.setBrandname(dbCollection.getName());
			collectionTemp.add(collection);
			
		}
		collections.setCollections(collectionTemp);
		
		return collections;
	}
	
	@RequestMapping(value={"/collection/getphotos", "/collection/getphotos/{profileid}"}, method=RequestMethod.GET)
	public @ResponseBody Photos GetPhotos(@ModelAttribute("profileid") String profileid, 
			@RequestParam(value="pagenumber", required=false, defaultValue="0") int pagenumber,
										  @RequestParam("galleryid") String galleryid,
										  @RequestParam("photoname") String photoname
											 ) {
		System.out.println("Get collection Photos: " + photoname);
		Photos photos = new Photos();
		if(galleryid.isEmpty()) {
			return photos;
		}
		
		User user;
		if(!profileid.isEmpty()) {
			user = userService.getUserByUsername(profileid);
			if(user == null || USER_TYPE.BOUTIQUE == user.getUserType()) {
				return photos;
			}
		} else {
			 user = userDetailsService.getUserFromSession();
		}
		
		if(0 == pagenumber) {
			photos.setName(user.getName());
			photos.setProfilepic(imageURLsConverter.convert(user.getProfilePicLink(), user.getUsername()));
			photos.setGalleryname(collectionService.getCollection(galleryid).getCollectionseasonname());
		}
		
		Set<PhotoWeb> photosTemp = new LinkedHashSet<PhotoWeb>();
		Iterable<CollectionPhotoData> dbPhotos;
		if(photoname.isEmpty()) {
			dbPhotos = collectionService.getPhotos(galleryid, pagenumber);
		} else {
			dbPhotos = collectionService.searchPhotoByName(galleryid, photoname, pagenumber);
		}
		if(null == dbPhotos) {
			return photos;
		}
		Iterator<CollectionPhotoData> first = dbPhotos.iterator();
		while ( first.hasNext() ){
			CollectionPhotoData dbPhoto = first.next() ;
			
			PhotoWeb newPhoto = collectionPhotoConverter.convert(dbPhoto.getCollectionPhoto(), dbPhoto.getUsername());
			photos.setBrandname(dbPhoto.getName());
			photosTemp.add(newPhoto);
		}
		photos.setPhotos(photosTemp);
		
		return photos;
	}
	
	@RequestMapping(value="collection/create", method=RequestMethod.POST)
	public @ResponseBody JsonResponse CreateCollection(@RequestBody CollectionJSONRequest request) {
		System.out.println("Create Collection");
		StringBuffer error = new StringBuffer();
		JsonResponse response = new JsonResponse();
		
		User loggedinUser = userDetailsService.getUserFromSession();
		if(loggedinUser.getUserType() != USER_TYPE.BRAND) {
			response.setError("Only brands can create collections");
			return response;
		}
		org.netvogue.server.neo4japi.domain.Collection newCollection = 
				new org.netvogue.server.neo4japi.domain.Collection(request.getSeasonname(), request.getDesc(), loggedinUser);
		
		if(null != request.getCategory()) {
			ProductLines productLine = ProductLines.getValueOf(request.getCategory());
			Category cat = boutiqueService.getOrCreateCategory(productLine);
			newCollection.setProductcategory(cat);
		}
		if(ResultStatus.SUCCESS == collectionService.SaveCollection(newCollection, error)) {  
			response.setStatus(true);
			response.setIdcreated(newCollection.getCollectionid());
		}
		else
			response.setError(error.toString());
		
		return response;
	}
	
	@RequestMapping(value="collection/edit", method=RequestMethod.POST)
	public @ResponseBody JsonResponse EditCollection(@RequestBody CollectionJSONRequest request) {
		System.out.println("Edit Collection" + request.getSeasonname());
		StringBuffer error = new StringBuffer();
		JsonResponse response = new JsonResponse();
		
		if(null == request.getId() || request.getId().isEmpty()) {
			response.setError("editorial Id is empty");
			return response;
		} else if((null == request.getSeasonname() || request.getSeasonname().isEmpty()) && 
				  (null == request.getDesc()		  || request.getDesc().isEmpty())) {
			response.setError("new name and description are empty");
			return response;
		}
		
		org.netvogue.server.neo4japi.domain.Collection collection = collectionService.getCollection(request.getId());
		if(null == collection) {
			response.setError("There is no collection with this Id");
			return response;
		}
		if(collection.getProductcategory().getProductLine().getDesc() == request.getCategory()) {
			if(ResultStatus.SUCCESS == collectionService.editCollection(request.getId(), 
								request.getSeasonname(), request.getDesc(), error))   
				response.setStatus(true);
			else
				response.setError(error.toString());
		} else { //If we can write a cypher query for the below operation, then we can replace
			//this else part
			collection.setCollectionseasonname(request.getSeasonname());
			collection.setDescription(request.getDesc());
			ProductLines productLine = ProductLines.getValueOf(request.getCategory());
			Category cat = boutiqueService.getOrCreateCategory(productLine);
			collection.setProductcategory(cat);
			if(ResultStatus.SUCCESS == collectionService.SaveCollection(collection, error)) {  
				response.setStatus(true);
				response.setIdcreated(collection.getCollectionid());
			}
			else
				response.setError(error.toString());
		}
		System.out.println("Edited Collection " + error);
		return response;
	}
	
	@RequestMapping(value="collection/setprofilepic", method=RequestMethod.POST)
	public @ResponseBody JsonResponse setProfilepic(@RequestBody JsonRequest profilepic) {
		System.out.println("Set Profile pic for collection:" + profilepic.getId());
		StringBuffer error = new StringBuffer();
		JsonResponse response = new JsonResponse();
		if(profilepic.getId().isEmpty() || profilepic.getValue().isEmpty()) {
			response.setError("collectionid or profile pic is empty");
			return response;
		}
		if(ResultStatus.SUCCESS == collectionService.setProfilepic(profilepic.getId(), profilepic.getValue(), error)) 
			response.setStatus(true);
		else
			response.setError(error.toString());
		
		return response;
	}
	
	//Think about the categories as well
	@RequestMapping(value="collection/delete", method=RequestMethod.POST)
	public @ResponseBody JsonResponse DeleteCollection(@RequestBody String galleryid) {
		System.out.println("Delete Collection:"+ galleryid);
		StringBuffer error = new StringBuffer();
		JsonResponse response = new JsonResponse();
		
		if(null == galleryid || galleryid.isEmpty()) {
			response.setError("Galleryid is empty");
			return response;
		}
		User user = userDetailsService.getUserFromSession();
		if (null == user) {
			response.setError("user info missing");
			return response;
		}
		List<String> collectionIdsList = collectionService.deleteCollection(galleryid, error);
		response.setStatus(true);
		if(null != collectionIdsList && collectionIdsList.size() > 0) {
			ResultStatus status = uploadManager.deletePhotosList(collectionIdsList,
					ImageType.COLLECTION, user.getUsername());
			System.out.println("Result Status of deleting collection from S3: "
					+ status.toString());		
		}
		else
			response.setError(error.toString());
		
		return response;
	}
	
	@RequestMapping(value="collection/addphotos", method=RequestMethod.POST)
	public @ResponseBody UploadedFileResponse AddPhotostoGallery(Model model, 
			@RequestParam("files[]") List<MultipartFile> fileuploads, @RequestParam("galleryid") String galleryId) {
		System.out.println("Add photos: Gallery Id:" + galleryId + "No:of Photos:" + fileuploads.size());
		User user = userDetailsService.getUserFromSession();
		if(null == user)
			return null;
		
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
			Map<String, Object> uploadMap  = uploadManager.processUpload(fileupload, ImageType.COLLECTION, user.getUsername());
			CollectionPhoto newPhoto = new CollectionPhoto((String)uploadMap.get(UploadManager.FILE_ID));
			collection.addPhotos(newPhoto);
			
			JSONFileData.add(collectionPhotoConverter.convert(newPhoto, user.getUsername()));
		}
		StringBuffer error = new StringBuffer();
		if(ResultStatus.SUCCESS == collectionService.SaveCollection(collection, error)) {  
			response.setStatus(true);
			response.setFilesuploaded(JSONFileData);
		}
		else
			response.setError(error.toString());
		return response;
	}
	
	@RequestMapping(value="collection/editphotoinfo", method=RequestMethod.POST)
	public @ResponseBody JsonResponse EditPhotoInfo(@RequestBody PhotoInfoJsonRequest photoInfo) {
		System.out.println("Edit Photo Info:" + photoInfo.toString());
		StringBuffer error = new StringBuffer();
		JsonResponse response = new JsonResponse();
		String photoId = photoInfo.getPhotoid();
		if(null == photoId || photoId.isEmpty())
			response.setError("photoid is empty");
		if(ResultStatus.SUCCESS == collectionService.editPhotoInfo(photoInfo.getPhotoid(), photoInfo.getPhotoname(), 
													photoInfo.getSeasonname(), error))
			response.setStatus(true);
		else
			response.setError(error.toString());
		
		return response;
	}
	
	@RequestMapping(value="collection/editphotoname", method=RequestMethod.POST)
	public @ResponseBody JsonResponse EditPhotoName(@RequestBody JsonRequest request) {
		System.out.println("Edit Photo Name");
		StringBuffer error = new StringBuffer();
		
		JsonResponse response = new JsonResponse();
		
		if(ResultStatus.SUCCESS == collectionService.editPhotoName(request.getId(), request.getValue(), error))   
			response.setStatus(true);
		else
			response.setError(error.toString());
		
		return response;
	}
	
	@RequestMapping(value="collection/editphotoseasonname", method=RequestMethod.POST)
	public @ResponseBody JsonResponse EditPhotoSeasonName(@RequestParam("photoname") String photoname,
														  @RequestParam("seasonname") String seasonname, 
														  @RequestParam("photoid") String photoid) {
		System.out.println("Edit Photo Name");
		StringBuffer error = new StringBuffer();
		
		JsonResponse response = new JsonResponse();
		
		if(ResultStatus.SUCCESS == collectionService.editPhotoInfo(photoid, photoname, seasonname, error))   
			response.setStatus(true);
		else
			response.setError(error.toString());
		
		return response;
	}

	@RequestMapping(value="collection/deletephoto", method=RequestMethod.POST)
	public @ResponseBody JsonResponse DeletePhoto(@RequestBody String photoid) {
		System.out.println("Delete Collections Photo:" + photoid);
		StringBuffer error = new StringBuffer();		
		JsonResponse response = new JsonResponse();
		User user = userDetailsService.getUserFromSession();
		if (null == user) {
			response.setError("user info missing");
			return response;
		}
		if(!photoid.isEmpty()) {
			if(ResultStatus.SUCCESS == collectionService.deletePhoto(photoid, error)) {  
				response.setStatus(true);
				ResultStatus status = uploadManager.deletePhotosById(photoid,
						ImageType.COLLECTION, user.getUsername());
				System.out.println("Result Status of deleting from S3: "
						+ status.toString());				
			}
			else
				response.setError(error.toString());
		} else {
			response.setError("photoid is empty");
		}
		
		return response;
	}
}