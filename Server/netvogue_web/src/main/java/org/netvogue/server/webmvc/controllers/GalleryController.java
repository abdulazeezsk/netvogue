package org.netvogue.server.webmvc.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.netvogue.server.aws.core.ImageType;
import org.netvogue.server.aws.core.UploadManager;
import org.netvogue.server.neo4japi.common.ResultStatus;
import org.netvogue.server.neo4japi.domain.Photo;
import org.netvogue.server.neo4japi.domain.User;
import org.netvogue.server.neo4japi.service.UserService;
import org.netvogue.server.webmvc.domain.Galleries;
import org.netvogue.server.webmvc.domain.Gallery;
import org.netvogue.server.webmvc.domain.JsonResponse;
import org.netvogue.server.webmvc.domain.PhotoWeb;
import org.netvogue.server.webmvc.domain.Photos;
import org.netvogue.server.webmvc.domain.UploadedFile;
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
public class GalleryController {

	@Autowired NetvogueUserDetailsService userDetailsService;
	@Autowired UserService userService;
	@Autowired ConversionService	conversionService;

	@Autowired
	private UploadManager uploadManager;
	
	/*@RequestMapping(method=RequestMethod.GET)
	public ModelAndView ShowAddCollections(Model model) throws Exception {
		System.out.println("Comming to Add Collections");
		final User user = userDetailsService.getUserFromSession();
		return new ModelAndView("AddCollections2", "Collections", new Collections());

	}*/
	
	@RequestMapping(value="getgalleries", method=RequestMethod.GET)
	public @ResponseBody Galleries GetGalleries(@ModelAttribute("profileid") String profileid) {
		System.out.println("Get Galleries");
		Galleries galleries = new Galleries();
		User loggedinUser = userDetailsService.getUserFromSession();
		if(profileid.isEmpty()) {
			galleries.setName(loggedinUser.getName());
			Set<Gallery> galleriesTemp = new HashSet<Gallery>();
			Iterable<org.netvogue.server.neo4japi.domain.Gallery> dbGalleries = userService.GetGalleries(loggedinUser);
			Iterator<org.netvogue.server.neo4japi.domain.Gallery> first = dbGalleries.iterator();
			while ( first.hasNext() ){
				org.netvogue.server.neo4japi.domain.Gallery dbGallery = first.next() ;
				galleriesTemp.add(conversionService.convert(dbGallery, Gallery.class));
			}
			galleries.setGalleries(galleriesTemp);
		}
		return galleries;
	}
	
	@RequestMapping(value="getphotos", method=RequestMethod.GET)
	public @ResponseBody Photos GetPhotos(@ModelAttribute("profileid") String profileid, 
											 @RequestParam("galleryid") String galleryid) {
		System.out.println("Get Galleries");
		Photos photos = new Photos();
		User loggedinUser = userDetailsService.getUserFromSession();
		if(galleryid.isEmpty()) {
			return photos;
		}
		
		if(profileid.isEmpty()) {
			photos.setName(loggedinUser.getName());
			photos.setGalleryname(userService.GetGallery(galleryid).getGalleryname());
			Set<PhotoWeb> photosTemp = new HashSet<PhotoWeb>();
			Iterable<org.netvogue.server.neo4japi.domain.Photo> dbPhotos = userService.GetPhotos(galleryid);
			if(null == dbPhotos) {
				return photos;
			}
			Iterator<org.netvogue.server.neo4japi.domain.Photo> first = dbPhotos.iterator();
			while ( first.hasNext() ){
				org.netvogue.server.neo4japi.domain.Photo dbPhoto = first.next() ;
				photosTemp.add(conversionService.convert(dbPhoto, PhotoWeb.class));
			}
			photos.setPhotos(photosTemp);
		}
		return photos;
	}
	
	@RequestMapping(value="creategallery", method=RequestMethod.POST)
	public @ResponseBody JsonResponse CreateGallery(@RequestBody String galleryname) {
		System.out.println("Create Gallery");
		String error = "";
		User loggedinUser = userDetailsService.getUserFromSession();
		org.netvogue.server.neo4japi.domain.Gallery newGallery = new org.netvogue.server.neo4japi.domain.Gallery(galleryname, loggedinUser);
		
		JsonResponse response = new JsonResponse();
		
		if(ResultStatus.SUCCESS == userService.SaveGallery(newGallery, error)) {  
			response.setStatus(true);
			response.setIdcreated(newGallery.getGalleryid());
		}
		else
			response.setError(error);
		
		return response;
	}
	
	@RequestMapping(value="AddPhotos", method=RequestMethod.POST)
	public @ResponseBody UploadedFileResponse AddPhotostoGallery(Model model, 
			@RequestParam("files[]") List<MultipartFile> fileuploads, @RequestParam("galleryid") String galleryId) {
		System.out.println("Add photos: Gallery Id:" + galleryId + "No:of Photos:" + fileuploads.size());
		UploadedFileResponse response = new UploadedFileResponse();
		final User user = userDetailsService.getUserFromSession();
		
		if(galleryId.isEmpty()) {
			response.setError("Gallery Id is empty");
			return response;
		}
		org.netvogue.server.neo4japi.domain.Gallery gallery = userService.GetGallery(galleryId);
		if(null == gallery) {
			response.setError("No Gallery is present with this id");
			return response;
		}
		
		List<UploadedFile> JSONFileData= new ArrayList<UploadedFile>();
		
		for ( MultipartFile fileupload : fileuploads ) {
			System.out.println("Came here" + fileupload.getOriginalFilename());
			Map<String, Object> uploadMap  = uploadManager.processUpload(fileupload, ImageType.GALLERY);
			String imagePath = (String)uploadMap.get(UploadManager.QUERY_STRING);
			Photo newPhoto = new Photo((String)uploadMap.get(UploadManager.FILE_ID));
			gallery.addPhotos(newPhoto);
			
			UploadedFile fileUploaded = new UploadedFile(fileupload.getOriginalFilename(),
				Long.valueOf(fileupload.getSize()).intValue(), imagePath, (String)uploadMap.get(UploadManager.FILE_ID));
			JSONFileData.add(fileUploaded);
		}
		String error ="";
		if(ResultStatus.SUCCESS == userService.SaveGallery(gallery, error)) {  
			response.setStatus(true);
			response.setFilesuploaded(JSONFileData);
		}
		else
			response.setError(error);
		/*if ( collection.getFileData() instanceof MultipartFile )
			
		else {
			LinkedList<MultipartFile> list = (LinkedList<MultipartFile>)collection.getFileData();
			uploadManager.processUpload(list, ImageType.COLLECTIONS);
		}*/
		return response;
	}

}