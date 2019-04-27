package com.spring.social.springsocial.controller;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.social.springsocial.model.UserInfo;
import com.spring.social.springsocial.securityservice.SecurityService;
import com.spring.social.springsocial.securityservice.UserService;

@Controller
public class RegistrationController {
	
	@Autowired private UserService userService;
	@Autowired private SecurityService securityService;
	
	@PostMapping(value="/register")
	public String registration(@ModelAttribute UserInfo userInfo, HttpServletRequest request, Model model) {
		String password = userInfo.getPassword();
		userInfo.setRole("ADMIN");
		UserInfo dbUser = userService.save(userInfo);
		securityService.autoLogin(dbUser.getEmail(), password, dbUser.getRole(), request);
		model.addAttribute("user", dbUser);
		
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		Collection<? extends GrantedAuthority> grantedAuthorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		Iterator<? extends GrantedAuthority> iterator = grantedAuthorities.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		System.out.println(name);
		return "redirect:/redirectdashboard";
	}

}
