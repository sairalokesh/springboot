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

import com.springboot.jersey.springjerseyclient.model.Message;
import com.springboot.jersey.springjerseyclient.model.User;


@Controller
@RequestMapping(value="/json")
public class SpringJerseyJsonClientController {
	
	@GetMapping(value="/")
	public String home(Model model) {
		Client client = ClientBuilder.newClient();
		Response response = client.target("http://localhost:1234/restjson/").request().get();
		List<User> users = response.readEntity(new GenericType<List<User>>() {});
		model.addAttribute("users", users);
		model.addAttribute("user", new User());
		model.addAttribute("isAdd", true);
		return "view/jsonuser";
	}
	
	@GetMapping("/getuser/{userId}")
	public String save(@PathVariable Long userId, Model model) {
		Client client = ClientBuilder.newClient();
		Response userresponse = client.target("http://localhost:1234/restjson/getuser/"+userId).request().get();
		Response usersresponse = client.target("http://localhost:1234/restjson/").request().get();
		User user = userresponse.readEntity(User.class);
		List<User> users = usersresponse.readEntity(new GenericType<List<User>>() {});
		model.addAttribute("users", users);
		model.addAttribute("user",user);
		model.addAttribute("isAdd", false);
		return "view/jsonuser";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute User user, RedirectAttributes attributes, Model model) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:1234/restjson").path("/save");
		 Response response = target.request(MediaType.APPLICATION_JSON).post(Entity.entity(user, MediaType.APPLICATION_JSON),Response.class);
		 if(response.getStatus() == 200) {
			 Message message = response.readEntity(Message.class);
			 attributes.addFlashAttribute("successmessage", message.getMessage());
				return "redirect:/json/";
		 }else {
			 Message message = response.readEntity(Message.class);
			 model.addAttribute("errormessage", message.getMessage());
			 model.addAttribute("user",user);
				return "view/jsonuser";
		 }
	}
	
	@PostMapping("/update")
	public String update(@ModelAttribute User user, RedirectAttributes attributes, Model model) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:1234/restjson").path("/update");
		 Response response = target.request(MediaType.APPLICATION_JSON).put(Entity.entity(user, MediaType.APPLICATION_JSON),Response.class);
		 if(response.getStatus() == 200) {
			 Message message = response.readEntity(Message.class);
			 attributes.addFlashAttribute("successmessage", message.getMessage());
				return "redirect:/json/";
		 }else {
			 Message message = response.readEntity(Message.class);
			 model.addAttribute("errormessage", message.getMessage());
			 model.addAttribute("user",user);
				return "view/jsonuser";
		 }
	}
	
	@GetMapping("/deleteuser/{userId}")
	public String deleteuser(@PathVariable Long userId,  RedirectAttributes attributes, Model model) {
		Client client = ClientBuilder.newClient();
		Response response = client.target("http://localhost:1234/restjson/delete/"+userId).request().delete();
		if(response.getStatus() == 200) {
			Message message = response.readEntity(Message.class);
			 attributes.addFlashAttribute("successmessage", message.getMessage());
				return "redirect:/json/";
		 }else {
			 Message message = response.readEntity(Message.class);
			 model.addAttribute("errormessage", message.getMessage());
			 return "view/jsonuser";
		 }		
	}
}
