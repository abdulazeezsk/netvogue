package org.netvogue.server.webmvc.controllers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.netvogue.server.aws.core.ImageType;
import org.netvogue.server.aws.core.UploadManager;
import org.netvogue.server.webmvc.domain.Collections;
import org.netvogue.server.webmvc.domain.JsonResponse;
import org.netvogue.server.webmvc.domain.UploadedFile;
import org.netvogue.server.webmvc.security.NetvogueUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.netvogue.server.neo4japi.domain.User;

@Controller
@RequestMapping("brand/AddCollections.htm")
public class AddCollectionsController {

	@Autowired NetvogueUserDetailsService userDetailsService;

	@Autowired
	private UploadManager uploadManager;
	
	/*@RequestMapping(method=RequestMethod.GET)
	public ModelAndView ShowAddCollections(Model model) throws Exception {
		System.out.println("Comming to Add Collections");
		final User user = userDetailsService.getUserFromSession();
		return new ModelAndView("AddCollections2", "Collections", new Collections());

	}*/
	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody List<UploadedFile> AddCollections(Model model, @RequestParam("files[]") List<MultipartFile> fileuploads) {
		final User user = userDetailsService.getUserFromSession();
		List<UploadedFile> JSONFileData= new ArrayList<UploadedFile>();
		
		for ( MultipartFile fileupload : fileuploads ) {
			System.out.println("Came here" + fileupload.getOriginalFilename());
			Map<String, Object> uploadMap  = uploadManager.processUpload(fileupload, ImageType.COLLECTIONS);
			String imagePath = (String)uploadMap.get(UploadManager.QUERY_STRING);
		
			UploadedFile fileUploaded = new UploadedFile(fileupload.getOriginalFilename(),
				Long.valueOf(fileupload.getSize()).intValue(), imagePath);
			JSONFileData.add(fileUploaded);
		}
		/*if ( collection.getFileData() instanceof MultipartFile )
			
		else {
			LinkedList<MultipartFile> list = (LinkedList<MultipartFile>)collection.getFileData();
			uploadManager.processUpload(list, ImageType.COLLECTIONS);
		}*/
		return JSONFileData;
	}
	
}