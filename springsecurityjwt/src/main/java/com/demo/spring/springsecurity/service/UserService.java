package com.demo.spring.springsecurity.service;

import java.util.List;

import com.demo.spring.springsecurity.model.User;

public interface UserService {

	User save(User user);

	List<User> findAll();

	User getUserByEmail(String email);

}
