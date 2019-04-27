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

import com.spring.jersey.springjerseyserver.dto.MessageDto;
import com.spring.jersey.springjerseyserver.model.User;
import com.spring.jersey.springjerseyserver.service.UserService;

@Component
@Path(value="/restjson")
public class JsonCrudController {
@Autowired UserService userService;
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response home() {
		List<User> users = userService.getAllUsers();
		 GenericEntity<List<User>> list = new GenericEntity<List<User>>(users) {};
		 return Response.ok(list).build();
	}
	
	@GET
	@Path("/getuser/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response save(@PathParam("userId") Long userId) {
		User user = userService.findById(userId);
		GenericEntity<User> list = new GenericEntity<User>(user) {};
		return Response.ok(list).build();
	}
	
	@POST
	@Path("/save")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response save(User user) {
		User dbUser = userService.save(user);
		if(dbUser!=null) {
			 return Response.status(200).entity(new MessageDto("User is Saved Successfully")).build();
		}else {
			return Response.status(400).entity(new MessageDto("User is not Save, Please try again")).build();
		}
	}
	
	@PUT
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response update(User user) {
		User dbUser = userService.update(user);
		if(dbUser!=null) {
			return Response.status(200).entity(new MessageDto("User is Updated Successfully")).build();
		}else {
			return Response.status(400).entity(new MessageDto("User is not update, Please try again")).build();
		}
	}
	
	@DELETE
	@Path("/delete/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response deleteuser(@PathParam("userId")Long userId) {
		userService.deleteuser(userId);
		return Response.status(200).entity(new MessageDto("User is Deleted Successfully")).build();
		
	}	
}
