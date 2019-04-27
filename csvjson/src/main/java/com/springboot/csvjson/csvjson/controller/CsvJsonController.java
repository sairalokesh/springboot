package com.springboot.csvjson.csvjson.controller;

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

import com.springboot.csvjson.csvjson.model.User;
import com.springboot.csvjson.csvjson.service.UserService;

@Controller
public class CsvJsonController {
	
	@Autowired UserService userService;
	@Autowired ServletContext context;
		
		@GetMapping("/")
		public String home(Model model) {
			List<User> users = userService.getAllUsers();
			model.addAttribute("users", users);
			return "view/user";
		}
		
		@GetMapping("/createCsv")
		public void createCsv(HttpServletRequest request, HttpServletResponse response) {
			List<User> users = userService.getAllUsers();	
			boolean isFlag = userService.createCsv(users, context);
			if(isFlag) {
				String fullPath = request.getServletContext().getRealPath("/resources/reports/"+"users.csv");
				filedownload(fullPath, response, "users.csv");
			}
		}
		@GetMapping("/createJson")
		public void createJson(HttpServletRequest request, HttpServletResponse response) {
			List<User> users = userService.getAllUsers();	
			boolean isFlag = userService.createJson(users, context);
			if(isFlag) {
				String fullPath = request.getServletContext().getRealPath("/resources/reports/"+"users.json");
				filedownload(fullPath, response, "users.json");
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
