package com.springboot.csvjson.csvjson.service;

import java.util.List;

import javax.servlet.ServletContext;

import com.springboot.csvjson.csvjson.model.User;


public interface UserService {
	List<User> getAllUsers();
	boolean createCsv(List<User> users, ServletContext context);
	boolean createJson(List<User> users, ServletContext context);
}
