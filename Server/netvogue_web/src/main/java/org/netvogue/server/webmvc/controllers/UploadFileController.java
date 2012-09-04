package org.netvogue.server.webmvc.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.netvogue.server.aws.core.ImageType;
import org.netvogue.server.aws.core.UploadManager;
import org.netvogue.server.webmvc.domain.BoutiqueNew;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "boutique/uploadfile")
public class UploadFileController {
	
	@Autowired
	private UploadManager uploadManager;

	@RequestMapping(method = RequestMethod.POST)
	public String create(BoutiqueNew boutiqueNew, BindingResult result,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				System.err.println("Error: " + error.getCode() + " - "
						+ error.getDefaultMessage());
			}
			return "boutique/Boutique_RegistrationStep3";
		}

		try {
			/*MultipartFile file = boutiqueNew.getFileData();
			
			Map<String, Object> uploadMap = uploadManager.processUpload(file, ImageType.PROFILE_PIC);
            boutiqueNew.setFilename((String)uploadMap.get(UploadManager.FILE_ID));
			String imagePath = (String)uploadMap.get(UploadManager.QUERY_STRING);
			//imagePath = java.net.URLDecoder.decode( imagePath, "UTF-8");
			session.setAttribute("uploadFile", imagePath);*/
			//session.setAttribute("uploadFile", "<img src=\"" + (String)uploadMap.get(UploadManager.QUERY_STRING) + "\"/>" );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "boutique/Boutique_RegistrationStep3";
	}

}
