package com.spring.social.springsocial.service;

import org.springframework.social.facebook.api.User;

public interface FacebookService {

	String facebooklogin();
	String getFacebookAccessToken(String code);
	User getFacebookUserProfile(String accessToken);

}
