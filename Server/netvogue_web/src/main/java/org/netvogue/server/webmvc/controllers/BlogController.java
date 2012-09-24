package org.netvogue.server.webmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("blog.html")
public class BlogController {
	
	@RequestMapping(method=RequestMethod.GET)
	public String ShowBlog(Model model) throws Exception {
		return "blog";
	}
}