package org.netvogue.server.webmvc.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.netvogue.server.aws.core.ImageType;
import org.netvogue.server.aws.core.UploadManager;
import org.netvogue.server.common.ResultStatus;
import org.netvogue.server.common.USER_TYPE;
import org.netvogue.server.neo4japi.domain.PrintCampaignPhoto;
import org.netvogue.server.neo4japi.domain.User;
import org.netvogue.server.neo4japi.service.PrintCampaignService;
import org.netvogue.server.neo4japi.service.UserService;
import org.netvogue.server.webmvc.converters.ImageURLsConverter;
import org.netvogue.server.webmvc.converters.PrintCampaignConverter;
import org.netvogue.server.webmvc.converters.PrintCampaignPhotoConverter;
import org.netvogue.server.webmvc.domain.CampaignJSONRequest;
import org.netvogue.server.webmvc.domain.JsonRequest;
import org.netvogue.server.webmvc.domain.JsonResponse;
import org.netvogue.server.webmvc.domain.PhotoInfoJsonRequest;
import org.netvogue.server.webmvc.domain.PhotoWeb;
import org.netvogue.server.webmvc.domain.Photos;
import org.netvogue.server.webmvc.domain.PrintCampaign;
import org.netvogue.server.webmvc.domain.PrintCampaigns;
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
public class PrintCampaignController {
	@Autowired NetvogueUserDetailsService 	userDetailsService;
	@Autowired UserService 					userService;
	@Autowired ImageURLsConverter			imageURLsConverter;
	@Autowired PrintCampaignService			printcampaignService;
	@Autowired PrintCampaignConverter		printcampaignConverter;
	@Autowired PrintCampaignPhotoConverter	printcampaignPhotoConverter;
	@Autowired ConversionService			conversionService;

	@Autowired
	private UploadManager uploadManager;
		
	@RequestMapping(value={"/getprintcampaigns", "/getprintcampaigns/{profileid}"}, method=RequestMethod.GET)
	public @ResponseBody PrintCampaigns GetPrintCampaigns(@ModelAttribute("profileid") String profileid,
												@RequestParam("pagenumber") int pagenumber,
												@RequestParam("galleryname") String galleryname) {
		
		System.out.println("Get PrintCampaigns: " + profileid + ":" + galleryname);
		PrintCampaigns campaigns = new PrintCampaigns();
		
		User user;
		if(!profileid.isEmpty()) {
			user = userService.getUserByUsername(profileid);
			if(user == null) {
				return campaigns;
			}
		} else {
			 user = userDetailsService.getUserFromSession();
		}
		
		campaigns.setName(user.getName());
		campaigns.setIsbrand(USER_TYPE.BRAND == user.getUserType()?true:false);
		campaigns.setProfilepic(imageURLsConverter.convert(user.getProfilePicLink(), user.getUsername()));
		Set<PrintCampaign> campaignTemp = new LinkedHashSet<PrintCampaign>();
		Iterable<org.netvogue.server.neo4japi.domain.PrintCampaign> dbPrintCampaigns;
		if(galleryname.isEmpty()) {
			dbPrintCampaigns = userService.getPrintCampaigns(user, pagenumber);
		} else {
			dbPrintCampaigns = userService.searchPrintCampaignByName(user, galleryname, pagenumber);
		}
		if(null == dbPrintCampaigns) {
			return campaigns;
		}
		Iterator<org.netvogue.server.neo4japi.domain.PrintCampaign> first = dbPrintCampaigns.iterator();
		while ( first.hasNext() ){
			org.netvogue.server.neo4japi.domain.PrintCampaign dbPrintCampaign = first.next() ;
			System.out.println("Print Campaign name: " + dbPrintCampaign.getPrintcampaignname());
			campaignTemp.add(printcampaignConverter.convert(dbPrintCampaign, user.getUsername()));
		}
		campaigns.setGalleries(campaignTemp);
		
		return campaigns;
	}
	
	@RequestMapping(value={"/printcampaign/getphotos", "/printcampaign/getphotos/{profileid}"}, method=RequestMethod.GET)
	public @ResponseBody Photos GetPhotos(@ModelAttribute("profileid") String profileid, 
			@RequestParam(value="pagenumber", required=false, defaultValue="0") int pagenumber,
										  @RequestParam("galleryid") String galleryid,
										  @RequestParam("photoname") String photoname
											 ) {
		System.out.println("Get Print Campaign Photos: " + photoname);
		Photos photos = new Photos();
		if(galleryid.isEmpty()) {
			return photos;
		}
		
		User user;
		if(!profileid.isEmpty()) {
			user = userService.getUserByUsername(profileid);
			if(user == null) {
				return photos;
			}
		} else {
			 user = userDetailsService.getUserFromSession();
		}
		if(0 == pagenumber) {
			photos.setName(user.getName());
			photos.setIsbrand(USER_TYPE.BRAND == user.getUserType()?true:false);
			photos.setProfilepic(imageURLsConverter.convert(user.getProfilePicLink(), user.getUsername()));
		}
		org.netvogue.server.neo4japi.domain.PrintCampaign printcampaign = printcampaignService.getPrintCampaign(galleryid);
		if(null == printcampaign) {
			return photos;
		}
		photos.setGalleryname(printcampaign.getPrintcampaignname());
		Set<PhotoWeb> photosTemp = new LinkedHashSet<PhotoWeb>();
		Iterable<PrintCampaignPhoto> dbPhotos;
		if(photoname.isEmpty()) {
			dbPhotos = printcampaignService.getPhotos(galleryid, pagenumber);
		} else {
			dbPhotos = printcampaignService.searchPhotoByName(galleryid, photoname, pagenumber);
		}
		if(null == dbPhotos) {
			return photos;
		}
		Iterator<PrintCampaignPhoto> first = dbPhotos.iterator();
		while ( first.hasNext() ){
			PrintCampaignPhoto dbPhoto = first.next() ;
			photosTemp.add(printcampaignPhotoConverter.convert(dbPhoto, user.getUsername()));
		}
		photos.setPhotos(photosTemp);
		
		return photos;
	}
	
	@RequestMapping(value="printcampaign/create", method=RequestMethod.POST)
	public @ResponseBody JsonResponse CreatePrintCampaign(@RequestBody CampaignJSONRequest request) {
		System.out.println("Create Print campaiggn");
		StringBuffer error = new StringBuffer();
		User loggedinUser = userDetailsService.getUserFromSession();
		org.netvogue.server.neo4japi.domain.PrintCampaign newPrintcampaign = 
				new org.netvogue.server.neo4japi.domain.PrintCampaign(request.getName(), request.getDesc(), loggedinUser);
		
		JsonResponse response = new JsonResponse();
		
		if(ResultStatus.SUCCESS == printcampaignService.SavePrintCampaign(newPrintcampaign, error)) {  
			response.setStatus(true);
			response.setIdcreated(newPrintcampaign.getPrintcampaignid());
		}
		else
			response.setError(error.toString());
		
		return response;
	}
	
	@RequestMapping(value="printcampaign/edit", method=RequestMethod.POST)
	public @ResponseBody JsonResponse EditPrintCampaign(@RequestBody CampaignJSONRequest request) {
		System.out.println("Edit Print Campaign");
		StringBuffer error = new StringBuffer();
		JsonResponse response = new JsonResponse();
		
		if(null == request.getId() || request.getId().isEmpty()) {
			response.setError("printcampaign Id is empty");
			return response;
		} else if(null == request.getName() || request.getDesc().isEmpty()) {
			response.setError("newgalleryname is empty");
			return response;
		}
		
		if(ResultStatus.SUCCESS == printcampaignService.editPrintCampaign(request.getId(), 
							request.getName(), request.getDesc(), error))   
			response.setStatus(true);
		else
			response.setError(error.toString());
		
		return response;
	}
	
	@RequestMapping(value="printcampaign/setprofilepic", method=RequestMethod.POST)
	public @ResponseBody JsonResponse setProfilepic(@RequestBody JsonRequest profilepic) {
		System.out.println("Set Profile pic for campaign:" + profilepic.getId());
		StringBuffer error = new StringBuffer();
		JsonResponse response = new JsonResponse();
		if(profilepic.getId().isEmpty() || profilepic.getValue().isEmpty()) {
			response.setError("campaignid or profile pic is empty");
			return response;
		}
		if(ResultStatus.SUCCESS == printcampaignService.setProfilepic(profilepic.getId(), profilepic.getValue(), error)) 
			response.setStatus(true);
		else
			response.setError(error.toString());
		
		return response;
	}
	
	@RequestMapping(value="printcampaign/delete", method=RequestMethod.POST)
	public @ResponseBody JsonResponse DeleteCampaign(@RequestBody String galleryid) {
		System.out.println("Delete Print Campaign:"+ galleryid);
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
		List<String> idsList = printcampaignService.deletePrintCampaign(galleryid, error);
		response.setStatus(true);
		if(null != idsList && idsList.size() > 0) {  
			ResultStatus status = uploadManager.deletePhotosList(idsList,
					ImageType.PRINT_CAMPAIGN, user.getUsername());
			System.out.println("Result Status of deleting print campaign from S3: "
					+ status.toString());			
		}
		else
			response.setError(error.toString());
		
		return response;
	}
	
	@RequestMapping(value="printcampaign/addphotos", method=RequestMethod.POST)
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
		org.netvogue.server.neo4japi.domain.PrintCampaign printcampaign = printcampaignService.getPrintCampaign(galleryId);
		if(null == printcampaign) {
			response.setError("No Print Campaign is present with this id");
			return response;
		}
		
		List<PhotoWeb> JSONFileData= new ArrayList<PhotoWeb>();
		
		for ( MultipartFile fileupload : fileuploads ) {
			System.out.println("Came here" + fileupload.getOriginalFilename());
			Map<String, Object> uploadMap  = uploadManager.processUpload(fileupload, ImageType.PRINT_CAMPAIGN, user.getUsername());
			PrintCampaignPhoto newPhoto = new PrintCampaignPhoto((String)uploadMap.get(UploadManager.FILE_ID));
			printcampaign.addPhotos(newPhoto);
			
			JSONFileData.add(printcampaignPhotoConverter.convert(newPhoto, user.getUsername()));
		}
		StringBuffer error = new StringBuffer();
		if(ResultStatus.SUCCESS == printcampaignService.SavePrintCampaign(printcampaign, error)) {  
			response.setStatus(true);
			response.setFilesuploaded(JSONFileData);
		}
		else
			response.setError(error.toString());
		return response;
	}
	
	@RequestMapping(value="printcampaign/editphotoinfo", method=RequestMethod.POST)
	public @ResponseBody JsonResponse EditPhotoInfo(@RequestBody PhotoInfoJsonRequest photoInfo) {
		System.out.println("Edit Photo Info:" + photoInfo.toString());
		StringBuffer error = new StringBuffer();
		JsonResponse response = new JsonResponse();
		String photoId = photoInfo.getPhotoid();
		if(null == photoId || photoId.isEmpty())
			response.setError("photoid is empty");
		if(ResultStatus.SUCCESS == printcampaignService.editPhotoInfo(photoInfo.getPhotoid(), photoInfo.getPhotoname(), 
													photoInfo.getSeasonname(), error))
			response.setStatus(true);
		else
			response.setError(error.toString());
		
		return response;
	}
	
	@RequestMapping(value="printcampaign/editphotoname", method=RequestMethod.POST)
	public @ResponseBody JsonResponse EditPhotoName(@RequestBody JsonRequest request) {
		System.out.println("Edit Photo Name");
		StringBuffer error = new StringBuffer();
		
		JsonResponse response = new JsonResponse();
		
		if(ResultStatus.SUCCESS == printcampaignService.editPhotoName(request.getId(), request.getValue(), error))   
			response.setStatus(true);
		else
			response.setError(error.toString());
		
		return response;
	}
	
	@RequestMapping(value="printcampaign/editphotoseasonname", method=RequestMethod.POST)
	public @ResponseBody JsonResponse EditPhotoSeasonName(@RequestParam("photoname") String photoname,
														  @RequestParam("seasonname") String seasonname, 
														  @RequestParam("photoid") String photoid) {
		System.out.println("Edit Photo Name");
		StringBuffer error = new StringBuffer();
		
		JsonResponse response = new JsonResponse();
		
		if(ResultStatus.SUCCESS == printcampaignService.editPhotoInfo(photoid, photoname, seasonname, error))   
			response.setStatus(true);
		else
			response.setError(error.toString());
		
		return response;
	}

	@RequestMapping(value = "printcampaign/deletephoto", method = RequestMethod.POST)
	public @ResponseBody JsonResponse DeletePhoto(@RequestBody String photoid) {
		System.out.println("Delete Print Campaign Photo:" + photoid);
		StringBuffer error = new StringBuffer();

		JsonResponse response = new JsonResponse();
		User user = userDetailsService.getUserFromSession();
		if (null == user) {
			response.setError("user info missing");
			return response;
		}
		if (!photoid.isEmpty()) {
			if (ResultStatus.SUCCESS == printcampaignService.deletePhoto(
					photoid, error)) {
				response.setStatus(true);
				ResultStatus status = uploadManager.deletePhotosById(photoid,
						ImageType.PRINT_CAMPAIGN, user.getUsername());
				System.out.println("Result Status of deleting from S3: "
						+ status.toString());
			} else
				response.setError(error.toString());
		} else {
			response.setError("photoid is empty");
		}

		return response;
	}
}
