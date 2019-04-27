package com.demo.spring.springsecurity.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.springsecurity.model.User;
import com.demo.spring.springsecurity.service.UserService;

@RestController
public class UserController {
	
	@Autowired UserService userService;
	
	@GetMapping(value="/users")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<User>> getUsers(){
		List<User> users = userService.findAll();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@GetMapping(value="/getUser")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<User> getUser(Principal principal){
		User user = userService.getUserByEmail(principal.getName());
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

}
