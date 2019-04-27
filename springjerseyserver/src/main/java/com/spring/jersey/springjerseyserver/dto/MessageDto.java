package com.spring.jersey.springjerseyserver.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MessageDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5216511804763820355L;
	private String message;

	public MessageDto(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
