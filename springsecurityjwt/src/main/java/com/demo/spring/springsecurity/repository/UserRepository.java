package com.demo.spring.springsecurity.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.spring.springsecurity.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	User findByEmailIgnoreCase(String username);

}
