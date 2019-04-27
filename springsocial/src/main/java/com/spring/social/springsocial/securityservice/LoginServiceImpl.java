package com.spring.social.springsocial.securityservice;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.social.springsocial.model.UserInfo;
import com.spring.social.springsocial.repository.UserRepository;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {
	
	@Autowired private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserInfo user = userRepository.findByEmailAndEnabled(email, true);
		if(user == null) {
			throw new UsernameNotFoundException("Use not found for "+email);
		}
		
		GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+user.getRole());
		UserDetails userDetails = (UserDetails)new User(user.getEmail(), user.getPassword(), Arrays.asList(authority));
		return userDetails;
	}

}
