package com.springboot.security.SpringSecurity.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;

import com.springboot.security.SpringSecurity.model.UserInfo;
import com.springboot.security.SpringSecurity.service.UserService;

@Controller
public class RedirectDashboardController {
	
	@Autowired private UserService userService;
	
	@GetMapping(value="/redirectdashboard")
	public String redirectDashboard(Principal principal, Model model) {
		UserInfo userInfo = userService.findByEmail(principal.getName());
		if(userInfo!=null && StringUtils.hasText(userInfo.getRole())) {
			if(userInfo.getRole().equalsIgnoreCase("USER")) {
				return "redirect:/users/dashboard";
			}
			if(userInfo.getRole().equalsIgnoreCase("ADMIN")) {
				
				return "redirect:/admin/dashboard";
				
			}
			if(userInfo.getRole().equalsIgnoreCase("SUPERADMIN")) {
				return "redirect:/superadmin/dashboard";
			}
		}
		return "redirect:/login";
		
	}

}
