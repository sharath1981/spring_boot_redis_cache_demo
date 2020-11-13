package com.redis.cache.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redis.cache.domain.Person;
import com.redis.cache.service.PersonService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("api/v1/persons")
public class PersonController {
	
	private final PersonService personService;
	
	@GetMapping
	public List<Person> getPersons() {
		log.info("[getPersons() called from PersonController]");
		return personService.getPersons();
	}
	
	@GetMapping("{id}")
	public Person getPerson(@PathVariable int id) {
		log.info("[getPerson(id) called from PersonController]");
		return personService.getPerson(id);
	}

}
