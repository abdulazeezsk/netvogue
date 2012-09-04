package org.netvogue.server.webmvc.controllers;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;


import org.netvogue.server.neo4japi.common.ResultStatus;
import org.netvogue.server.neo4japi.domain.Boutique;
import org.netvogue.server.neo4japi.domain.Brand;
import org.netvogue.server.neo4japi.service.BoutiqueService;
import org.netvogue.server.neo4japi.service.BrandService;
import org.netvogue.server.webmvc.domain.BoutiqueNew;
import org.netvogue.server.webmvc.domain.BrandNew;
import org.netvogue.server.webmvc.domain.JsonResponse;
import org.netvogue.server.webmvc.domain.UsersAvailable;
import org.netvogue.server.webmvc.security.NetvogueUserDetailsService;

@Controller
@SessionAttributes("brandNew")
public class BrandNewController {

	@Autowired NetvogueUserDetailsService 	userDetailsService;
	@Autowired BrandService 		brandService;
	@Autowired BoutiqueService		boutiqueService;
	@Autowired ConversionService	conversionService;
	  
	@RequestMapping(value ="brand_Registration.html", method = RequestMethod.GET)
    public String showForm() {
		return "brand_Registration";
    }

	@RequestMapping(value ="/brand/doregistration",method=RequestMethod.POST)
	public @ResponseBody JsonResponse DoRegistration(@RequestBody BoutiqueNew boutiqueNew) throws Exception {
		
		System.out.println("Brand_Registration--------------"+boutiqueNew.toString());
		JsonResponse status = new JsonResponse();
		if(	(ResultStatus.SUCCESS == boutiqueService.ValidateEmail(boutiqueNew.getEmail())) &&
			(ResultStatus.SUCCESS == boutiqueService.ValidateUsername(boutiqueNew.getUsername()))	) 		{
				Brand user = conversionService.convert(boutiqueNew, Brand.class);
				String error = new String();
				if(ResultStatus.SUCCESS == brandService.AddNewBrand(user, error))
					status.setStatus(true);
				else
					status.setError(error);
		} else {
			status.setError("Kindly give other username or email.");
		}
		/*if(!result.hasErrors()){
			System.out.println("Brand_Registration--Validation is successful for "+boutiqueNew.toString());
			if(ResultStatus.SUCCESS == boutiqueService.ValidateEmail(boutiqueNew.getEmail())) {
				Boutique user = conversionService.convert(boutiqueNew, Boutique.class);
        		boutiqueService.AddNewBoutique(user);
        		userDetailsService.setUserInSession((User)user);
        		status.setStatus(true);
			}
		}else{
			List<FieldError> allErrors = result.getFieldErrors();
			String errorMessage = new String();
			for (FieldError objectError : allErrors) {
				errorMessage +=  objectError.getField() + "  " + objectError.getDefaultMessage();
				errorMessage +=  "\n";
			}
			status.setError(errorMessage);
		}*/
		return status;
	}	
	
	@RequestMapping(value="/brand/emailavailability",method=RequestMethod.GET)
	public @ResponseBody boolean EmailAvailabilityStatus(@RequestParam String email){
		System.out.println("email id  -------------- "+ email);
		boolean emailAvailable = true;
		if(ResultStatus.USER_EXISTS  == boutiqueService.ValidateEmail(email)) {
			emailAvailable = false;
		}
		return emailAvailable;
	}
	
	@RequestMapping(value="/brand/usernameavailability",method=RequestMethod.GET)
	public @ResponseBody boolean UsernameAvailabilityStatus(@RequestParam String username){
		System.out.println("username  -------------- "+ username);
		boolean usernameAvailable = true;
		if(ResultStatus.USER_EXISTS  == boutiqueService.ValidateUsername(username)) {
			usernameAvailable = false;
		}
		return usernameAvailable;
	}
	
	//This search is for brands
		@RequestMapping(value="/brand/usersavailable",method=RequestMethod.GET)
		public @ResponseBody Set<UsersAvailable> UsersAvailable(@RequestParam String username){
			System.out.println("username entered -------------- "+ username);
			Set<UsersAvailable> usersavailable = new HashSet<UsersAvailable>();
			Iterable<Boutique> boutiques = boutiqueService.GetBoutique(username);
			Iterator<Boutique> first = boutiques.iterator();
			while ( first.hasNext() ){
			      Boutique b = first.next() ;
			      UsersAvailable newUser = new UsersAvailable(b.getName(), b.getUsername());
			      usersavailable.add(newUser);
			}
			return usersavailable;
		}
}
