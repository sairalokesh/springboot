package com.springboot.mail.springmail.service;

import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.mail.springmail.dto.EmailDto;

@Service
public class EmailSender {
	
	@Autowired
    private JavaMailSender javaMailSender;
	
	public Boolean sendEmail(List<EmailDto> emailDTOs, String body) {
        try {
            for (EmailDto emailDTO : emailDTOs) {
                MimeMessage mail = javaMailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(mail, true, "UTF-8");
                helper.setFrom(emailDTO.getFromEmail());
                helper.setTo(emailDTO.getToEmail());
                if (StringUtils.hasText(emailDTO.getCcEmail()) && !emailDTO.getCcEmail().startsWith("@")) {
                    helper.setCc(emailDTO.getCcEmail());
                }
                if (StringUtils.hasText(emailDTO.getBccEmail()) && !emailDTO.getBccEmail().startsWith("@")) {
                    helper.setBcc(emailDTO.getBccEmail());
                }
                
                helper.setSubject(emailDTO.getEmailSubject());
                helper.setText(body, true);
                
                if(emailDTO.getFiles()!=null && emailDTO.getFiles().size()>0) {
                	for(MultipartFile file : emailDTO.getFiles()) {
                		if (file!=null && StringUtils.hasText(file.getOriginalFilename())) {
                			final InputStreamSource imageSource = new ByteArrayResource(file.getBytes());
            				helper.addAttachment(file.getOriginalFilename(), imageSource, file.getContentType());
                        }
                	}
                }
                
                if(emailDTO.getInlineFile()!=null && StringUtils.hasText(emailDTO.getInlineFile().getOriginalFilename())) {
        			final InputStreamSource imageSource = new ByteArrayResource(emailDTO.getInlineFile().getBytes());
    				helper.addInline("image", imageSource, emailDTO.getInlineFile().getContentType());
                }
                
                javaMailSender.send(mail);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
