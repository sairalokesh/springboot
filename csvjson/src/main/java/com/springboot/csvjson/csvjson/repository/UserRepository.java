package com.springboot.csvjson.csvjson.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.csvjson.csvjson.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
