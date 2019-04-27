package com.springboot.pdfexcel.pdfexcel.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.springboot.pdfexcel.pdfexcel.model.User;
import com.springboot.pdfexcel.pdfexcel.service.UserService;

@Controller
public class PdfExcelController {
	
@Autowired UserService userService;
@Autowired ServletContext context;
	
	@GetMapping("/")
	public String home(Model model) {
		List<User> users = userService.getAllUsers();
		model.addAttribute("users", users);
		return "view/user";
	}
	
	@GetMapping("/createPdf")
	public void createPdf(HttpServletRequest request, HttpServletResponse response) {
		List<User> users = userService.getAllUsers();	
		boolean isFlag = userService.createPdf(users, context, request, response);
		if(isFlag) {
			String fullPath = request.getServletContext().getRealPath("/resources/reports/"+"users.pdf");
			filedownload(fullPath, response, "users.pdf");
		}
	}
	
	@GetMapping("/createExcel")
	public void createExcel(HttpServletRequest request, HttpServletResponse response) {
		List<User> users = userService.getAllUsers();	
		boolean isFlag = userService.createExcel(users, context, request, response);
		if(isFlag) {
			String fullPath = request.getServletContext().getRealPath("/resources/reports/"+"users.xls");
			filedownload(fullPath, response, "users.xls");
		}
	}
	
	private void filedownload(String fullPath, HttpServletResponse response, String fileName) {
		File file = new File(fullPath);
		final int BUFFER_SIZE = 4096;
		if(file.exists()) {
			 try {
				FileInputStream inputStream = new FileInputStream(file);
				String mimeType = context.getMimeType(fullPath);
	            System.out.println("MIME type: " + mimeType);
	            response.setContentType(mimeType);
	            response.setHeader("Content-disposition", "attachment; filename="+fileName);
	            OutputStream outStream = response.getOutputStream();
	            byte[] buffer = new byte[BUFFER_SIZE];
	            int bytesRead = -1;
	            while ((bytesRead = inputStream.read(buffer)) != -1) {
	                outStream.write(buffer, 0, bytesRead);
	            }

	            inputStream.close();
	            outStream.close();
	            file.delete();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
