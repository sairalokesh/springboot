package com.spring.social.springsocial.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.spring.social.springsocial.model.UserInfo;

@Controller
public class HomeController {
	
	@GetMapping(value="/")
	public String home(Model model) {
		model.addAttribute("user", new UserInfo());
		model.addAttribute("errormessage", "");
		return "view/home";
	}
	
	
	@GetMapping(value="/loginfailure")
	public String loginfailure(Model model) {
		model.addAttribute("user", new UserInfo());
		model.addAttribute("errormessage", "Please enter correct email & Password");
		return "view/home";
	}
}
