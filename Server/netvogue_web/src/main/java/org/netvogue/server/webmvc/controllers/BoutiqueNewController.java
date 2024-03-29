package org.netvogue.server.webmvc.controllers;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.codehaus.jackson.map.ObjectMapper;
import org.netvogue.server.common.RestStatusCodes;
import org.netvogue.server.common.ResultStatus;
import org.netvogue.server.mandrill.util.EmailUtil;
import org.netvogue.server.neo4japi.domain.Boutique;
import org.netvogue.server.neo4japi.domain.Brand;
import org.netvogue.server.neo4japi.domain.User;
import org.netvogue.server.neo4japi.domain.User.Roles;
import org.netvogue.server.neo4japi.service.BoutiqueService;
import org.netvogue.server.neo4japi.service.BrandService;
import org.netvogue.server.neo4japi.service.UserService;
import org.netvogue.server.webmvc.domain.BoutiqueNew;
import org.netvogue.server.webmvc.domain.JsonResponse;
import org.netvogue.server.webmvc.domain.UsersAvailable;
import org.netvogue.server.webmvc.rest.invoker.RestInvoker;
import org.netvogue.server.webmvc.rest.invoker.RestServiceContext;
import org.netvogue.server.webmvc.rest.invoker.UserRepresentation;
import org.netvogue.server.webmvc.security.NetvogueUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.jersey.api.client.ClientResponse;

@Controller
public class BoutiqueNewController {

  @Autowired
  NetvogueUserDetailsService userDetailsService;

  @Autowired
  UserService userService;

  @Autowired
  BoutiqueService boutiqueService;

  @Autowired
  BrandService brandService;

  @Autowired
  ConversionService conversionService;

  @Autowired
  RestInvoker restInvoker;

  @Autowired
  RestServiceContext customerServiceContext;

  @RequestMapping("boutique_Registration.html")
  public String Boutique_RegistrationStep1(final HttpServletRequest request) throws Exception {
    return "boutique_Registration";
  }

/*  @RequestMapping(value = "/boutique/doregistration", method = RequestMethod.POST)
  public @ResponseBody
  JsonResponse DoRegistration(@RequestBody
  @Valid final
  BoutiqueNew boutiqueNew) throws Exception {

    System.out.println("Boutique_RegistrationStep--------------" + boutiqueNew.toString());
    JsonResponse status = new JsonResponse();
    
    if (ResultStatus.SUCCESS == boutiqueService.ValidateEmail(boutiqueNew.getEmail())
            && ResultStatus.SUCCESS == boutiqueService.ValidateUsername(boutiqueNew.getUsername())) {
	    ShaPasswordEncoder encoder = new ShaPasswordEncoder();
	    
	    long salt = System.nanoTime();
	    
	    UserRepresentation rep = new UserRepresentation();
	    rep.setEmail(boutiqueNew.getEmail());
	    rep.setUsername(boutiqueNew.getUsername());
	    rep.setPassword(encoder.encodePassword(boutiqueNew.getPassword(), salt));
	    //rep.setPassword(boutiqueNew.getPassword());
	
	    rep.setMobileNo(Long.valueOf(boutiqueNew.getMobile()));
	    rep.setPrimarycontact(boutiqueNew.getPrimarycontact());
	    rep.setRole(Roles.ROLE_BOUTIQUE.toString());
	    rep.setSubscirptionType("FREE");
	    rep.setSalt(salt);
	    
	    ObjectMapper mapper = new ObjectMapper();
	    StringWriter w = new StringWriter();
	    mapper.writeValue(w, rep);
	
//	    ClientResponse res = restInvoker.invokePUT(customerServiceContext.getBaseUrl(), new HashMap<String, String>(), w.toString());
	
	    if (ResultStatus.SUCCESS == boutiqueService.AddNewBoutique(user, error)) {
	    	System.out.println("Boutique Registration is successful" + boutiqueNew.getUsername());
	        status.setStatus(true);
	        // EmailUtil.sendConfirmationEmail(user);
	        Boutique user = conversionService.convert(boutiqueNew, Boutique.class);
		    String error = new String();
		    if (ResultStatus.SUCCESS == boutiqueService.AddNewBoutique(user, error)) {
		    	status.setStatus(true);
		        EmailUtil.sendConfirmationEmail(user);
		    } else {
		        status.setError(error);
		        // userDetailsService.setUserInSession((User)user);
		    }
	    } else {
	    	System.out.println("Boutique Registration is unsuccessful" + boutiqueNew.getUsername());
	    	status.setStatus(false);
	    }
    } else {
      status.setError("Kindly give other username or email.");
    }
    
     * if(!result.hasErrors()){
     * System.out.println("Boutique_Registration--Validation is successful for "+boutiqueNew.toString());
     * if(ResultStatus.SUCCESS == boutiqueService.ValidateEmail(boutiqueNew.getEmail())) { Boutique user =
     * conversionService.convert(boutiqueNew, Boutique.class); boutiqueService.AddNewBoutique(user);
     * userDetailsService.setUserInSession((User)user); status.setStatus(true); } } else { List<FieldError> allErrors =
     * result.getFieldErrors(); String errorMessage = new String(); for (FieldError objectError : allErrors) {
     * errorMessage += objectError.getField() + "  " + objectError.getDefaultMessage(); errorMessage += "\n"; }
     * status.setError(errorMessage); }
     

    return status;
  }*/
  
  @RequestMapping(value = "/boutique/doregistration", method = RequestMethod.POST)
  public @ResponseBody
  JsonResponse DoRegistration(@RequestBody
  @Valid final
  BoutiqueNew boutiqueNew) throws Exception {

    System.out.println("Boutique_RegistrationStep--------------" + boutiqueNew.toString());
    JsonResponse status = new JsonResponse();

    if (ResultStatus.SUCCESS == boutiqueService.ValidateEmail(boutiqueNew.getEmail())
        && ResultStatus.SUCCESS == boutiqueService.ValidateUsername(boutiqueNew.getUsername())) {
      Boutique user = conversionService.convert(boutiqueNew, Boutique.class);
      String error = new String();
      if (ResultStatus.SUCCESS == boutiqueService.AddNewBoutique(user, error)) {
        status.setStatus(true);
        EmailUtil.sendConfirmationEmail(user);
      } else {
        status.setError(error);
        // userDetailsService.setUserInSession((User)user);
      }
    } else {
      status.setError("Kindly give other username or email.");
    }
    /*
     * if(!result.hasErrors()){
     * System.out.println("Boutique_Registration--Validation is successful for "+boutiqueNew.toString());
     * if(ResultStatus.SUCCESS == boutiqueService.ValidateEmail(boutiqueNew.getEmail())) { Boutique user =
     * conversionService.convert(boutiqueNew, Boutique.class); boutiqueService.AddNewBoutique(user);
     * userDetailsService.setUserInSession((User)user); status.setStatus(true); } } else { List<FieldError> allErrors =
     * result.getFieldErrors(); String errorMessage = new String(); for (FieldError objectError : allErrors) {
     * errorMessage += objectError.getField() + "  " + objectError.getDefaultMessage(); errorMessage += "\n"; }
     * status.setError(errorMessage); }
     */

    return status;
  }

  @RequestMapping(value = "/boutique/emailavailability", method = RequestMethod.GET)
  public @ResponseBody
  boolean EmailAvailabilityStatus(@RequestParam final
  String email) {
    System.out.println("email id  -------------- " + email);
    boolean emailAvailable = true;
    if (ResultStatus.USER_EXISTS == boutiqueService.ValidateEmail(email)) {
      emailAvailable = false;
    }
    return emailAvailable;
  }

  @RequestMapping(value = "/boutique/usernameavailability", method = RequestMethod.GET)
  public @ResponseBody
  boolean UsernameAvailabilityStatus(@RequestParam final
  String username) {
    System.out.println("username  -------------- " + username);
    boolean usernameAvailable = true;
    if (ResultStatus.USER_EXISTS == boutiqueService.ValidateUsername(username)) {
      usernameAvailable = false;
    }
    return usernameAvailable;
  }

  // This search is for brands
  @RequestMapping(value = "/boutique/usersavailable", method = RequestMethod.GET)
  public @ResponseBody
  Set<UsersAvailable> UsersAvailable(@RequestParam final
  String username) {
    System.out.println("username entered -------------- " + username);
    Set<UsersAvailable> usersavailable = new HashSet<UsersAvailable>();
    Iterable<Brand> brands = brandService.GetBrand(username);
    Iterator<Brand> first = brands.iterator();
    while (first.hasNext()) {
      Brand b = first.next();
      UsersAvailable newUser = new UsersAvailable(b.getName(), b.getUsername());
      usersavailable.add(newUser);
    }
    return usersavailable;
  }

  @RequestMapping(value = "/confirmRegisteration/{userId}", method = RequestMethod.GET)
  public String confirmRegisteration(@PathVariable final
  String userId) {
    User user = null;
    String redirectPage = "redirect:/Netvogue.html";
    StringBuffer error = null;
    System.out.println("userId entered -------------- " + userId);
    if (null != userId) {
      user = userService.getUserByUserId(userId);
      if (null != user) {
        user.setAccountEnabled(true);
        error = new StringBuffer();
        if (ResultStatus.SUCCESS != userService.SaveUser(user, error)) {
          redirectPage = "Error";
        }
      } else {
        redirectPage = "Error";
      }
    } else {
      redirectPage = "Error";
    }
    return redirectPage;
  }

}
