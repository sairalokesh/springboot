package com.springboot.mail.springmail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.mail.springmail.dto.EmailDto;
import com.springboot.mail.springmail.service.SpringMailServcie;

@Controller
public class SpringMailController {
	
	@Autowired private SpringMailServcie springMailServcie;
	
	@Value("${spring.mail.from}")
	public String fromEmail;
	
	@GetMapping(value="/")
	public String emailPage(Model model) {
		EmailDto  email = new EmailDto();
		email.setFromEmail(fromEmail);
		model.addAttribute("mail", email);
		return "view/mailpage";
	}
	
	@PostMapping(value="/sendemail")
	public String sendemail(@ModelAttribute EmailDto email, RedirectAttributes attributes, Model model) {
		System.out.println(email.getToEmail()+" "+email.getEmailSubject());
		boolean isFlag = springMailServcie.sendEmail(email);
		if(isFlag) {
			attributes.addFlashAttribute("successmessage", "Email Send Successfully");
			return "redirect:/";
		}else {
			model.addAttribute("errormessage", "Email not Send, Please try again");
			model.addAttribute("mail", email);
			return "view/mailpage";
		}
		
	}

}
