package com.springboot.file.springreadfile.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.springboot.file.springreadfile.model.User;

public interface UserService {

	boolean saveDataFromUploadFile(MultipartFile file);
	List<User> findByFileType(String filetype);

}
