package com.springboot.csvjson.csvjson.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVWriter;
import com.springboot.csvjson.csvjson.model.User;
import com.springboot.csvjson.csvjson.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public List<User> getAllUsers() {
		return (List<User>) userRepository.findAll();
	}
	
	@Override
	public boolean createCsv(List<User> users, ServletContext context) {
		String filePath=context.getRealPath("/resources/reports");
		boolean exists = new File(filePath).exists();
		if(!exists){
			new File(filePath).mkdirs();
		}
		File file = new File(filePath+"/"+File.separator+"users.csv");
		try {
			FileWriter fileWriter = new FileWriter(file);
			CSVWriter writer = new CSVWriter(fileWriter);
			List<String[]> data = new ArrayList<String[]>();
			data.add(new String[] { "First Name", "Last Name", "Email", "Phone Number" });
			for(User user : users) {
				data.add(new String[] { user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhoneNumber()});
			}
			writer.writeAll(data);
			writer.close();
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	@Override
	public boolean createJson(List<User> users, ServletContext context) {
		String filePath=context.getRealPath("/resources/reports");
		boolean exists = new File(filePath).exists();
		if(!exists){
			new File(filePath).mkdirs();
		}
		File file = new File(filePath+"/"+File.separator+"users.json");
		ObjectMapper mapper = new ObjectMapper();
		 try {
			mapper.writeValue(file, users);
			return true;
		} catch (IOException  e) {
			return false;
		}
		
	}
}
