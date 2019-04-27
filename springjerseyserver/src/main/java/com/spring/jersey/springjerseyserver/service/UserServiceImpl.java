package com.spring.jersey.springjerseyserver.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.jersey.springjerseyserver.model.User;
import com.spring.jersey.springjerseyserver.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public List<User> getAllUsers() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	public User save(User user) {
		user.setCreatedDate(new Date());
		return userRepository.save(user);
	}

	@Override
	public User findById(Long userId) {
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			return user.get();
		} else {
			return null;
		}
	}

	@Override
	public User update(User user) {
		user.setUpdatedDate(new Date());
		return userRepository.save(user);
	}

	@Override
	public void deleteuser(Long userId) {
		userRepository.deleteById(userId);
	}

}
