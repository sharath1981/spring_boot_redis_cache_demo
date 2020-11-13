package com.redis.cache.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.redis.cache.domain.Person;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PersonService  {
	
	private final Map<Integer, Person> persons = new HashMap<>();
	
	@PostConstruct
	public void init() {
		persons.put(1, new Person(1, "sharath", "bangalore", 38));
		persons.put(2, new Person(2, "vivek", "abhudabi", 37));
		persons.put(3, new Person(3, "gopal", "bangalore", 37));
		persons.put(4, new Person(4, "deb", "singapore", 48));
	}
	
	@Cacheable(value="PERSONS_CACHE")
	public List<Person> getPersons() {
		log.info("[[getPersons() called from PersonService]]");
		return persons.entrySet().stream()
				                 .map(e->e.getValue())
				                 .collect(Collectors.toList());
	}

	@Cacheable("PERSON_CACHE")
	public Person getPerson(int id) {
		log.info("[[getPerson(id) called from PersonService]]");
		return persons.get(id);
	}
	
	@Scheduled(fixedRate = 60_000)
	@CacheEvict(cacheNames = {"PERSON_CACHE", "PERSONS_CACHE"}, allEntries=true) 
	public void clearCache() {
		log.info("All Cache Cleared");
	}

}
