package com.spring.jersey.springjerseyserver.service;

import java.util.List;

import com.spring.jersey.springjerseyserver.model.User;


public interface UserService {

	List<User> getAllUsers();
	User save(User user);
	User findById(Long userId);
	User update(User user);
	void deleteuser(Long userId);
}
