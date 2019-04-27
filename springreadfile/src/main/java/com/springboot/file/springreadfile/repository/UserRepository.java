package com.springboot.file.springreadfile.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.file.springreadfile.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	List<User> findByFileType(String filetype);

}
