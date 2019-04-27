package com.springboot.mail.springmail.dto;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class EmailDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5357745276290281989L;
	private String fromEmail;
	private String toEmail;
	private String ccEmail;
	private String bccEmail;
	private String emailSubject;
	private String emailDescription;
	private List<MultipartFile> files;
	private MultipartFile inlineFile;

	public String getFromEmail() {
		return fromEmail;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	public String getToEmail() {
		return toEmail;
	}

	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	public String getEmailDescription() {
		return emailDescription;
	}

	public void setEmailDescription(String emailDescription) {
		this.emailDescription = emailDescription;
	}

	public List<MultipartFile> getFiles() {
		return files;
	}

	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}

	public MultipartFile getInlineFile() {
		return inlineFile;
	}

	public void setInlineFile(MultipartFile inlineFile) {
		this.inlineFile = inlineFile;
	}

	public String getCcEmail() {
		return ccEmail;
	}

	public void setCcEmail(String ccEmail) {
		this.ccEmail = ccEmail;
	}

	public String getBccEmail() {
		return bccEmail;
	}

	public void setBccEmail(String bccEmail) {
		this.bccEmail = bccEmail;
	}

}
