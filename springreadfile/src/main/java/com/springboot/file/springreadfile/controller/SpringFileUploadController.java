package com.springboot.file.springreadfile.controller;

import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.file.springreadfile.model.User;
import com.springboot.file.springreadfile.service.UserService;

@Controller
public class SpringFileUploadController {
	
@Autowired UserService userService;

@GetMapping("/")
public String home(Model model) {
	model.addAttribute("user", new User());
	return "view/user";
}


@PostMapping("/fileupload")
public String save(@ModelAttribute User user, RedirectAttributes attributes, Model model) {
	if(user!=null && user.getFile()!=null && StringUtils.hasText(user.getFile().getOriginalFilename())) {
		boolean isFlag = userService.saveDataFromUploadFile(user.getFile());
		if(isFlag) {
			return "redirect:/getfiledetails/"+FilenameUtils.getExtension(user.getFile().getOriginalFilename());
		}
	}
	return "redirect:/";
}

@GetMapping("/getfiledetails/{filetype}")
public String home(@PathVariable String filetype, Model model) {
	List<User> users = userService.findByFileType(filetype);
	model.addAttribute("users", users);
	model.addAttribute("user", new User());
	return "view/user";
}
}
