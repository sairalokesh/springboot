package com.demo.spring.springsecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.spring.springsecurity.model.User;
import com.demo.spring.springsecurity.repository.UserRepository;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmailIgnoreCase(username);
		if(user == null) {
			throw new UsernameNotFoundException(String.format("No User found with username '%s'.", username));
		} else {
			return JwtuserFactory.create(user);
		}
		
	}

}
