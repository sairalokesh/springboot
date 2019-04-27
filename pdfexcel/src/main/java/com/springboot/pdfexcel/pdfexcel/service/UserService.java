package com.springboot.pdfexcel.pdfexcel.service;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.springboot.pdfexcel.pdfexcel.model.User;


public interface UserService {
	List<User> getAllUsers();
	boolean createPdf(List<User> users, ServletContext context, HttpServletRequest request, HttpServletResponse response);
	boolean createExcel(List<User> users, ServletContext context, HttpServletRequest request, HttpServletResponse response);
}
