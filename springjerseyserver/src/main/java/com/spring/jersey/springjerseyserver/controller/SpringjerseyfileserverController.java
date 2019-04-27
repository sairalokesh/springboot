package com.spring.jersey.springjerseyserver.controller;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.stereotype.Component;

import com.spring.jersey.springjerseyserver.dto.MessageDto;

@Component
@Path(value="/restfile")
public class SpringjerseyfileserverController {
	
	@POST
	@Path("/fileupload")
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	@Produces({MediaType.APPLICATION_JSON})
	public Response uploadPdfFile(@FormDataParam("uploadFile") InputStream uploadedInputStream,
			@FormDataParam("uploadFile") FormDataContentDisposition fileDetail)  {
		 return Response.status(200).entity(new MessageDto("User is Saved Successfully")).build();
	}

}
