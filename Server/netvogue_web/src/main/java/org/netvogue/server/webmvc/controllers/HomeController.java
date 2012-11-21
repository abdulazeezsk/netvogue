package org.netvogue.server.webmvc.controllers;

import org.netvogue.server.webmvc.security.NetvogueUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;

import org.netvogue.server.common.USER_TYPE;
import org.netvogue.server.neo4japi.domain.User;

@Controller
@RequestMapping("home.htm")
public class HomeController {
	@Autowired NetvogueUserDetailsService userDetailsService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String ShowHome(Model model) throws Exception {
		final User user = userDetailsService.getUserFromSession();
		model.addAttribute("firsttimelogin", user.isFirstTimeLogin());
        model.addAttribute("isbrand", (user.getUserType() == USER_TYPE.BRAND)?true:false);
        model.addAttribute("brandname", user.getName());
        return "index";
	}

}
