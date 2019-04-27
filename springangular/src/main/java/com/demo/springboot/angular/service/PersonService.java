package com.demo.springboot.angular.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.springboot.angular.model.Person;
import com.demo.springboot.angular.repository.PersonRepository;

@Service
@Transactional
public class PersonService {
	
	@Autowired PersonRepository personRepository;

	public List<Person> getPersons() {
		return (List<Person>) personRepository.findAll();
	}

	public Person save(Person person) {
		return personRepository.save(person);
	}

	public Person findOne(Long id) {
		return personRepository.findById(id).get();
	}

	public Person update(Person person) {
		return personRepository.save(person);
	}

	public void delete(Long id) {
		personRepository.deleteById(id);
	}

}
