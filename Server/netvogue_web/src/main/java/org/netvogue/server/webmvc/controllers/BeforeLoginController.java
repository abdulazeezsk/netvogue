package org.netvogue.server.webmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BeforeLoginController {
	
	@RequestMapping(value="aboutus.html", method=RequestMethod.GET)
	public String ShowAboutus() throws Exception {
		return "about";
	}
	
	@RequestMapping(value="brands.html", method=RequestMethod.GET)
       public String ShowBrand() throws Exception {
              return "Brand";
       }
	
	@RequestMapping(value="retailers.html", method=RequestMethod.GET)
       public String ShowRetailer() throws Exception {
              return "Retailer";
       }
	
	@RequestMapping(value="apply.html", method=RequestMethod.GET)
       public String ShowApply() throws Exception {
              return "Apply";
       }
	
	@RequestMapping(value="privacy.html", method=RequestMethod.GET)
       public String Showprivacy() throws Exception {
              return "privacy";
       }
	
	@RequestMapping(value="terms.html", method=RequestMethod.GET)
       public String ShowTerms() throws Exception {
              return "terms";
       }
}