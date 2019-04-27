package com.demo.springboot.angular.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.springboot.angular.model.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

}
