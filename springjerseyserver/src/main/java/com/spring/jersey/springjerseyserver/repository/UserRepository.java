package com.spring.jersey.springjerseyserver.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.jersey.springjerseyserver.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
