package com.oficinabr.controllers;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oficinabr.configuration.GreetingConfiguration;
import com.oficinabr.model.Greeting;

@RestController
public class GreetingController {
	
	private static final String template = "%s, %s!";
	private final AtomicLong counter = new AtomicLong();
	
	@Autowired
	private GreetingConfiguration configuration;

	@RequestMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "") String name ) {
		
		String greeting = configuration.getGreeting();
		
		if(name.isEmpty()) {
			name = configuration.getDefaultValue();
		}
		return new Greeting(counter.incrementAndGet(), String.format(template, greeting, name));
	}
}