package com.spring.jersey.springjerseyserver;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.spring.jersey.springjerseyserver.controller.JsonCrudController;
import com.spring.jersey.springjerseyserver.controller.XmlCrudController;

@Component
public class EmployeeConfiguration extends ResourceConfig {
	
	public EmployeeConfiguration() {
		register(XmlCrudController.class);
		register(JsonCrudController.class);
	}
}
