package com.springboot.pdfexcel.pdfexcel.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.pdfexcel.pdfexcel.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
