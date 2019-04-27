package com.spring.social.springsocial.securityservice;

import javax.servlet.http.HttpServletRequest;

public interface SecurityService {

	void autoLogin(String email, String password, String role, HttpServletRequest request);

}
