package com.springboot.mail.springmail.service;

import com.springboot.mail.springmail.dto.EmailDto;

public interface SpringMailServcie {

	boolean sendEmail(EmailDto email);

}
