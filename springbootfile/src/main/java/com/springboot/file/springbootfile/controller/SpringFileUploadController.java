package com.springboot.file.springbootfile.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.file.springbootfile.model.User;
import com.springboot.file.springbootfile.model.UserFiles;
import com.springboot.file.springbootfile.service.UserService;

@Controller
public class SpringFileUploadController {
	
@Autowired UserService userService;
	
	@GetMapping("/")
	public String home(Model model) {
		List<User> users = userService.getAllUsers();
		model.addAttribute("users", users);
		model.addAttribute("user", new User());
		model.addAttribute("userfiles", new ArrayList<UserFiles>());
		model.addAttribute("isAdd", true);
		return "view/user";
	}
	
	@PostMapping("/save")
	public String save(@Valid User user, RedirectAttributes attributes, Model model) {
		User dbUser = userService.save(user);
		if(dbUser!=null) {
			attributes.addFlashAttribute("successmessage", "User is Saved Successfully");
			return "redirect:/";
		}else {
			model.addAttribute("errormessage", "User is not Save, Please try again Successfully");
			model.addAttribute("user",user);
			return "view/user";
		}
		
	}
	
	@GetMapping("/getuser/{userId}")
	public String getuser(@PathVariable Long userId, Model model) {
		User user = userService.findById(userId);
		List<UserFiles> userFiles = userService.findFilesByUserId(userId);
		List<User> users = userService.getAllUsers();
		model.addAttribute("users", users);
		model.addAttribute("userfiles", userFiles);
		model.addAttribute("user",user);
		model.addAttribute("isAdd", false);
		return "view/user";
	}
	
	@GetMapping("/viewuser/{userId}")
	public String viewuser(@PathVariable Long userId, Model model) {
		User user = userService.findById(userId);
		List<UserFiles> userFiles = userService.findFilesByUserId(userId);
		List<User> users = userService.getAllUsers();
		model.addAttribute("users", users);
		model.addAttribute("userfiles", userFiles);
		model.addAttribute("user",user);
		model.addAttribute("isAdd", false);
		return "view/viewuser";
	}
	
	@PostMapping("/update")
	public String update(@ModelAttribute User user, RedirectAttributes attributes, Model model) {
		User dbUser = userService.update(user);
		if(dbUser!=null) {
			attributes.addFlashAttribute("successmessage", "User is Updated Successfully");
			return "redirect:/";
		}else {
			model.addAttribute("errormessage", "User is not update, Please try again Successfully");
			model.addAttribute("user",user);
			return "view/user";
		}
	}
	
	@GetMapping("/deleteuser/{userId}")
	public String deleteuser(@PathVariable Long userId,  RedirectAttributes attributes) {
		userService.deleteFilesByUserId(userId);
		userService.deleteuser(userId);
		attributes.addFlashAttribute("successmessage", "User is Updated Successfully");
		return "redirect:/";
		
	}

}
