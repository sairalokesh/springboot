package com.spring.social.springsocial.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.social.springsocial.model.UserInfo;

@Repository
public interface UserRepository extends CrudRepository<UserInfo, Long> {

	UserInfo findByEmailAndEnabled(String email, boolean enabled);
	UserInfo findByEmail(String email);

}
