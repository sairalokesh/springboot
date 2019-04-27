package com.demo.springboot.angular.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demo.springboot.angular.domain.Response;
import com.demo.springboot.angular.model.Person;
import com.demo.springboot.angular.service.PersonService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class PersonController {
	
	@Autowired PersonService personService;
	@Autowired ServletContext context;
	
	
	@PostMapping(value="saveUserProfile")
	public ResponseEntity<Response> saveuserprofile(@RequestParam("file") MultipartFile file, @RequestParam("user") String user) throws JsonParseException, JsonMappingException, IOException {
		
		Person person = new ObjectMapper().readValue(user, Person.class);
		person.setLogo(file.getBytes());
		person.setFileName(file.getOriginalFilename());
		person.setCreatedDate(new Date());
		Person dbPerson = personService.save(person);
		if(dbPerson!=null) {
			return new ResponseEntity<Response>(new Response("User is Saved Successfully"), HttpStatus.OK);
		} else {
			return new ResponseEntity<Response>(new Response("User is not saved"), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value="saveUserProfileInServer")
	public ResponseEntity<Response> saveUserProfileInServer(@RequestParam("file") MultipartFile file, @RequestParam("user") String user) throws JsonParseException, JsonMappingException, IOException {
		Person person = new ObjectMapper().readValue(user, Person.class);
		person.setCreatedDate(new Date());
		
		boolean isExist = new File(context.getRealPath("/userprofile/")).exists();
		if(!isExist) {
			new File(context.getRealPath("/userprofile/")).mkdir();
		}
		
		String filename = file.getOriginalFilename();
		String modifiedFileName = FilenameUtils.getBaseName(filename)+"_"+System.currentTimeMillis()+"."+FilenameUtils.getExtension(filename);
		File serverfile = new File(context.getRealPath("/userprofile/"+File.separator+modifiedFileName));
		try {
			FileUtils.writeByteArrayToFile(serverfile, file.getBytes());
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		person.setFileName(modifiedFileName);
		Person dbPerson = personService.save(person);
		if(dbPerson!=null) {
			return new ResponseEntity<Response>(new Response("User is Saved Successfully"), HttpStatus.OK);
		} else {
			return new ResponseEntity<Response>(new Response("User is not saved"), HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	
	
	
	@GetMapping(value = "/persons")
	public ResponseEntity<List<Person>> getPersons(){
		List<Person> persons = personService.getPersons();
		return new ResponseEntity<List<Person>>(persons, HttpStatus.OK);
	}
	
	@PostMapping(value="/save")
	public ResponseEntity<Person> save(@RequestBody Person person) {
		if(person==null) {
			throw new NullPointerException("Person onject cannot be null");
		}
		person.setCreatedDate(new Date());
		Person dbPerson = personService.save(person);
		return new ResponseEntity<Person>(dbPerson, HttpStatus.OK);
		
	}
	
	@GetMapping(value="/getPerson/{id}")
	public ResponseEntity<Person> getPerson(@PathVariable("id") Long id) {
		if(id==null) {
			throw new NullPointerException("Person Id cannot be null");
		}
		Person dbPerson = personService.findOne(id);
		return new ResponseEntity<Person>(dbPerson, HttpStatus.OK);
		
	} 
	
	@PostMapping(value="/update")
	public ResponseEntity<Person> update(@RequestBody Person person) {
		if(person==null) {
			throw new NullPointerException("Person onject cannot be null");
		}
		person.setUpdatedDate(new Date());
		Person dbPerson = personService.update(person);
		return new ResponseEntity<Person>(dbPerson, HttpStatus.OK);
		
	}
	
	@DeleteMapping(value="/delete/{id}")
	public ResponseEntity<Response> delete(@PathVariable("id") Long id) {
		if(id==null) {
			throw new NullPointerException("Person Id cannot be null");
		}
		personService.delete(id);
		return new ResponseEntity<Response>(new Response("Person is deleted"), HttpStatus.OK);
		
	} 
}
