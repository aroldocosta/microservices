package com.oficinabr.services;

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
	
}
