package com.oficinabr.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.oficinabr.model.Person;

@Service
public class PersonService {

	private final AtomicLong counter = new AtomicLong();
	
	public Person findById(String id) {
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Aroldo");
		person.setLastName("Costa");
		person.setAddress("Av Emilia Gonçalves, 633");
		person.setGender("Male");
		return person; 
	}
	
	public List<Person> findAll() {
		List<Person> persons = new ArrayList<>();
		
		for(int i = 0; i< 8; i++) {
			Person person = mockPerson(i);
			persons.add(person);
		}
		return persons;
	}
	
	private Person mockPerson(int id) {
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Person_" + counter);
		person.setLastName("Costa");
		person.setAddress("Av Emilia Gonçalves, 633");
		person.setGender("Male");
		return person; 
	}
	
}
