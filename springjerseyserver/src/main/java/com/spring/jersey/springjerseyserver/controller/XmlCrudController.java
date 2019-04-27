package com.spring.jersey.springjerseyserver.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.spring.jersey.springjerseyserver.model.User;
import com.spring.jersey.springjerseyserver.service.UserService;

@Component
@Path(value="/restxml")
public class XmlCrudController {
	
	@Autowired UserService userService;
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_XML)
	@CrossOrigin
	public Response home() {
		List<User> users = userService.getAllUsers();
		 GenericEntity<List<User>> list = new GenericEntity<List<User>>(users) {};
		 return Response.ok(list).build();
	}
	
	@GET
	@Path("/getuser/{userId}")
	@Produces(MediaType.APPLICATION_XML)
	@CrossOrigin
	public Response save(@PathParam("userId") Long userId) {
		User user = userService.findById(userId);
		GenericEntity<User> list = new GenericEntity<User>(user) {};
		return Response.ok(list).build();
	}
	
	@POST
	@Path("/save")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	@CrossOrigin
	public Response save(User user) {
		User dbUser = userService.save(user);
		if(dbUser!=null) {
			 return Response.status(200).entity("User is Saved Successfully") .build();
		}else {
			return Response.status(400).entity("User is not Save, Please try again").build();
		}
	}
	
	@PUT
	@Path("/update")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	@CrossOrigin
	public Response update(User user) {
		User dbUser = userService.update(user);
		if(dbUser!=null) {
			 return Response.status(200).entity("User is Updated Successfully").build();
		}else {
			return Response.status(400).entity("User is not update, Please try again").build();
		}
	}
	
	@DELETE
	@Path("/delete/{userId}")
	@Produces(MediaType.APPLICATION_XML)
	@CrossOrigin
	public Response deleteuser(@PathParam("userId")Long userId) {
		userService.deleteuser(userId);
		return Response.status(200).entity("User is Deleted Successfully").build();
		
	}
}
