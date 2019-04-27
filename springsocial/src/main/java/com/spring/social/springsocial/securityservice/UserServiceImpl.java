package com.spring.social.springsocial.securityservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.spring.social.springsocial.model.UserInfo;
import com.spring.social.springsocial.repository.UserRepository;
import com.spring.social.springsocial.util.PasswordUtil;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired private UserRepository userRepository;

	@Override
	public UserInfo save(UserInfo userInfo) {
		userInfo.setEnabled(true);
		if(StringUtils.hasText(userInfo.getPassword())) {
			userInfo.setPassword(PasswordUtil.getEncodedPassword(userInfo.getPassword()));
		}
		return userRepository.save(userInfo);
	}

	@Override
	public UserInfo findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public void update(UserInfo dbUser) {
		userRepository.save(dbUser);
		
	}

}
