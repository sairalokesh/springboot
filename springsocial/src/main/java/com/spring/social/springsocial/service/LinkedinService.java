package com.spring.social.springsocial.service;

import org.springframework.social.linkedin.api.LinkedInProfileFull;

public interface LinkedinService {

	String linkedinlogin();

	String getLinkedInAccessToken(String code);

	LinkedInProfileFull getLinkedInUserProfile(String accessToken);

}
