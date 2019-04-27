package com.demo.spring.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.springsecurity.domain.Response;
import com.demo.spring.springsecurity.model.User;
import com.demo.spring.springsecurity.service.UserService;

@RestController
public class PreLoginController {
	
	@Autowired private UserService userService;
	
	@PostMapping(value="/registration")
	public ResponseEntity<Response> registration(@RequestBody User user) {
		User dbUser = userService.save(user);
		if(dbUser!=null) {
			return new ResponseEntity<>(new Response("User is saved successfully"), HttpStatus.OK);
		}
		
		return null;
		
		
	}

}
