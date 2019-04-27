package com.springboot.mail.springmail.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.springboot.mail.springmail.dto.EmailDto;


@Service
public class SpringMailServcieImpl implements SpringMailServcie {
	
	@Autowired private EmailSender emailSender;
	@Autowired private SpringTemplateEngine templateEngine;
	private static final String SEND_EMAIL = "email/emailtemplate";
	@Override
	public boolean sendEmail(EmailDto email) {
		Context context = new Context();
        context.setVariable("emailcontent", email.getEmailDescription());
        String body = templateEngine.process(SEND_EMAIL, context);
        List<EmailDto> emailDTOs = new ArrayList<EmailDto>();
        emailDTOs.add(email);
        return emailSender.sendEmail(emailDTOs, body);
	}

}
