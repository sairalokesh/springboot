package com.springboot.security.SpringSecurity.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.security.SpringSecurity.UserRepository;
import com.springboot.security.SpringSecurity.model.UserInfo;

@Service
@Transactional
public class LoginServcieImpl implements LoginService {
	
	@Autowired private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfo userInfo = userRepository.findEmailIgnoreCase(username, true);
		if(userInfo == null) {
			throw new UsernameNotFoundException("Username not found for "+username);
		}
		
		GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+userInfo.getRole());
		UserDetails userDetails = (UserDetails)new User(userInfo.getEmail(), userInfo.getPassword(), Arrays.asList(authority));
		return userDetails;
	}

}
