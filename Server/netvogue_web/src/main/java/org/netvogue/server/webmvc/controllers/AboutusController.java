package org.netvogue.server.webmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("about_Us.htm")
public class AboutusController {
	
	@RequestMapping(method=RequestMethod.GET)
	public String ShowAboutus(Model model) throws Exception {
		return "about_Us";
	}
}