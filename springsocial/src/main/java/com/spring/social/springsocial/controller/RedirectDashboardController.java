package com.spring.social.springsocial.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;

import com.spring.social.springsocial.model.UserInfo;
import com.spring.social.springsocial.securityservice.UserService;

@Controller
public class RedirectDashboardController {
	
	@Autowired private UserService userService;
	
	@GetMapping(value="/redirectdashboard")
	public String redirectdashboard(Principal principal) {
		String redirecturl = "";
		UserInfo dbUser = userService.findByEmail(principal.getName());
		if(dbUser!=null && StringUtils.hasText(dbUser.getRole())) {
			if(dbUser.getRole().equalsIgnoreCase("ADMIN")) {
				redirecturl = "redirect:/admin/dashboard";
			} else if(dbUser.getRole().equalsIgnoreCase("USER")) {
				redirecturl = "redirect:/user/dashboard";
			}
			
		}
		return redirecturl;
		
	}
}
