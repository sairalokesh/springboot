package com.spring.social.springsocial.securityservice;

import com.spring.social.springsocial.model.UserInfo;

public interface UserService {

	UserInfo save(UserInfo userInfo);
	UserInfo findByEmail(String email);
	void update(UserInfo dbUser);

}
