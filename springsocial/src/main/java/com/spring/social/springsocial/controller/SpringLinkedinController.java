package com.spring.social.springsocial.controller;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.linkedin.api.LinkedInProfileFull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.spring.social.springsocial.model.UserInfo;
import com.spring.social.springsocial.securityservice.SecurityService;
import com.spring.social.springsocial.securityservice.UserService;
import com.spring.social.springsocial.service.LinkedinService;

@Controller
public class SpringLinkedinController {
	
@Autowired private LinkedinService linkedinService;
@Autowired private UserService userService;
@Autowired private SecurityService securityService;
	
	@GetMapping(value="/linkedinlogin")
	public RedirectView linkedinlogin() {
		RedirectView redirectView = new RedirectView();
		String url = linkedinService.linkedinlogin();
		System.out.println(url);
		redirectView.setUrl(url);
		return redirectView;
	}
	
	@GetMapping(value="/linkedin")
	public String facebook(@RequestParam("code") String code) {
		String accessToken = linkedinService.getLinkedInAccessToken(code);
		return "redirect:/linkedinprofiledata/"+accessToken;
		
	}
	
	@GetMapping(value="/linkedinprofiledata/{accessToken:.+}")
	public String linkedinprofiledata(@PathVariable String accessToken, Model model, HttpServletRequest request) {
		LinkedInProfileFull user = linkedinService.getLinkedInUserProfile(accessToken);
		UserInfo dbUser = userService.findByEmail(user.getEmailAddress());
		String role = "USER";
		if(dbUser!=null) {
			dbUser.setFirstName(user.getFirstName());
			dbUser.setLastName(user.getLastName());
			dbUser.setImageUrl(user.getProfilePictureUrl());
			userService.update(dbUser);
			role = dbUser.getRole();
			model.addAttribute("user", dbUser);
		} else {
			UserInfo userInfo = new UserInfo(user.getFirstName(), user.getLastName(), user.getProfilePictureUrl());
			userInfo.setEmail(user.getEmailAddress());
			userInfo.setEnabled(true);
			userInfo.setRole("USER");
			userService.save(userInfo);
			role = userInfo.getRole();
			model.addAttribute("user", userInfo);
		}
		
		securityService.autoLogin(user.getEmailAddress(), null, role, request);
		
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
