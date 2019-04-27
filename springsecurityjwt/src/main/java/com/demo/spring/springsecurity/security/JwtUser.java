package com.demo.spring.springsecurity.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.demo.spring.springsecurity.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class JwtUser implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2408311976893713775L;
	
	private final Long id;
	private final String username;
	private final String password;
	private final User user;
	private final Collection<? extends GrantedAuthority> authorities;
	private final boolean enabled;
	
	public JwtUser(Long long1, String username, String password, User user,
			Collection<? extends GrantedAuthority> authorities, boolean enabled) {
		super();
		this.id = long1;
		this.username = username;
		this.password = password;
		this.user = user;
		this.authorities = authorities;
		this.enabled = enabled;
	}

	@JsonIgnore
	public Long getId() {
		return id;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@JsonIgnore
	@Override
	public String getPassword() {
		return password;
	}

	public User getUser() {
		return user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	
	

}
