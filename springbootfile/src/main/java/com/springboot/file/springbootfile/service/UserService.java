package com.springboot.file.springbootfile.service;

import java.util.List;
import com.springboot.file.springbootfile.model.User;
import com.springboot.file.springbootfile.model.UserFiles;

public interface UserService {

	List<User> getAllUsers();
	User save(User user);
	User findById(Long userId);
	User update(User user);
	void deleteuser(Long userId);
	List<UserFiles> findFilesByUserId(Long userId);
	void deleteFilesByUserId(Long userId);

}
