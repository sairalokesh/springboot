package com.spring.social.springsocial.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.social.springsocial.model.UserInfo;
import com.spring.social.springsocial.securityservice.UserService;

@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired private UserService userService;
	
	@GetMapping(value="/dashboard")
	public String userdashboard(Principal principal, Model model) {
		UserInfo dbUser = userService.findByEmail(principal.getName());
		model.addAttribute("user", dbUser);
		return "view/userprofile";
		
	}

}
