package org.netvogue.server.webmvc.controllers;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.netvogue.server.aws.core.ImageType;
import org.netvogue.server.aws.core.Size;
import org.netvogue.server.aws.core.UploadManager;
import org.netvogue.server.common.USER_TYPE;
import org.netvogue.server.neo4japi.domain.User;
import org.netvogue.server.neo4japi.service.ReferenceData;
import org.netvogue.server.neo4japi.service.UserService;
import org.netvogue.server.webmvc.common.Constants;
import org.netvogue.server.webmvc.domain.Reference;
import org.netvogue.server.webmvc.security.NetvogueUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SpecialController {

	@Autowired NetvogueUserDetailsService userDetailsService;
	@Autowired UserService 			userService;
	@Autowired ConversionService	conversionService;
	@Autowired UploadManager 		uploadManager;
	
	@RequestMapping(value={"getreferences"}, method=RequestMethod.GET)
	public @ResponseBody Set<Reference> GetReferences(@RequestParam(value="pagenumber", required=false) Integer pagenumber) {
		System.out.println("Get References: id is:" + pagenumber);
		User user = userDetailsService.getUserFromSession();
		if(user == null) {
			return null;
		}
		
		Set<Reference> response = new LinkedHashSet<Reference>();
	
		Iterable<ReferenceData> dbReferences = userService.getReferences(user.getUsername(), pagenumber.intValue());
		if(null == dbReferences) {
			System.out.println("No references found: ");
			return response;
		}
		
		Iterator<ReferenceData> first = dbReferences.iterator();
		while ( first.hasNext() ){
			ReferenceData dbReference = first.next() ;
			Reference newReference = new Reference();
			
			String thumbpic = "";
			User networkUser = dbReference.getReferences();
			newReference.setProfileid(networkUser.getUsername());
			newReference.setCity(networkUser.getCity());
			newReference.setCountry(networkUser.getCountry());
			newReference.setUsertype(networkUser.getUserType() == USER_TYPE.BRAND? "BRAND": "BOUTIQUE");
			newReference.setName(networkUser.getName());
			thumbpic = networkUser.getProfilePicLink();
			
			if(null == thumbpic || thumbpic.isEmpty()) {
				thumbpic = Constants.PROFILE_DefaultPic;
			} else {
				thumbpic = uploadManager.getQueryString(thumbpic, ImageType.PROFILE_PIC, Size.PThumb, networkUser.getUsername());
			}
			newReference.setThumbnail_url(thumbpic);
			newReference.setMutualfriends(dbReference.getMutualfriends());
			response.add(newReference);
		}		
		
		System.out.println("Sent references:" + response.size());
		return response;
	}
}
