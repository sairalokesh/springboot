package com.springboot.file.springbootfile.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.file.springbootfile.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
