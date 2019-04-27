package com.springboot.jersey.springjerseyclient.controller;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.jersey.springjerseyclient.model.User;


@Controller
@RequestMapping(value="/xml")
public class SpringJerseyXmlClientController {
	
	@GetMapping(value="/")
	public String home(Model model) {
		Client client = ClientBuilder.newClient();
		Response response = client.target("http://localhost:1234/restxml/").request().get();
		List<User> users = response.readEntity(new GenericType<List<User>>() {});
		model.addAttribute("users", users);
		model.addAttribute("user", new User());
		model.addAttribute("isAdd", true);
		return "view/xmluser";
	}
	
	@GetMapping("/getuser/{userId}")
	public String save(@PathVariable Long userId, Model model) {
		Client client = ClientBuilder.newClient();
		Response userresponse = client.target("http://localhost:1234/restxml/getuser/"+userId).request().get();
		Response usersresponse = client.target("http://localhost:1234/restxml/").request().get();
		User user = userresponse.readEntity(User.class);
		List<User> users = usersresponse.readEntity(new GenericType<List<User>>() {});
		model.addAttribute("users", users);
		model.addAttribute("user",user);
		model.addAttribute("isAdd", false);
		return "view/xmluser";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute User user, RedirectAttributes attributes, Model model) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:1234/restxml").path("/save");
		 Response response = target.request(MediaType.APPLICATION_XML).post(Entity.entity(user, MediaType.APPLICATION_XML),Response.class);
		 if(response.getStatus() == 200) {
			 String message = response.readEntity(String.class);
			 attributes.addFlashAttribute("successmessage", message);
				return "redirect:/xml/";
		 }else {
			 String message = response.readEntity(String.class);
			 model.addAttribute("errormessage", message);
			 model.addAttribute("user",user);
				return "view/xmluser";
		 }
	}
	
	@PostMapping("/update")
	public String update(@ModelAttribute User user, RedirectAttributes attributes, Model model) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:1234/restxml").path("/update");
		 Response response = target.request(MediaType.APPLICATION_XML).put(Entity.entity(user, MediaType.APPLICATION_XML),Response.class);
		 if(response.getStatus() == 200) {
			 String message = response.readEntity(String.class);
			 attributes.addFlashAttribute("successmessage", message);
				return "redirect:/xml/";
		 }else {
			 String message = response.readEntity(String.class);
			 model.addAttribute("errormessage", message);
			 model.addAttribute("user",user);
				return "view/xmluser";
		 }
	}
	
	@GetMapping("/deleteuser/{userId}")
	public String deleteuser(@PathVariable Long userId,  RedirectAttributes attributes, Model model) {
		Client client = ClientBuilder.newClient();
		Response response = client.target("http://localhost:1234/restxml/delete/"+userId).request().delete();
		if(response.getStatus() == 200) {
			 String message = response.readEntity(String.class);
			 attributes.addFlashAttribute("successmessage", message);
				return "redirect:/xml/";
		 }else {
			 String message = response.readEntity(String.class);
			 model.addAttribute("errormessage", message);
			 return "view/xmluser";
		 }		
	}
}
