package com.oficinabr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oficinabr.model.Book;
import com.oficinabr.proxy.CambioProxy;
import com.oficinabr.repository.BookRepository;

@RestController
@RequestMapping("book-service")
public class BookController {

	@Autowired
	private Environment environment;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private CambioProxy cambioProxy;
	
	@GetMapping(value = "/{id}/{currency}")
	public Book findByIdAndCurrency(
			@PathVariable("id") Long id,
			@PathVariable("currency") String currency
			) {
		
		String port = environment.getProperty("local.server.port");
		
		var book = bookRepository.findById(id).get();
		
		if( book == null ) throw new RuntimeException("Book not found");
		
		var cambio = cambioProxy.getCambio(book.getPrice(), "USD", currency);
		
		var price = cambio.getConvertedValue();

		book.setPrice(price);
		
		book.setEnvironment(
				"Book port: " + port + 
				" Cambio port: " + cambio.getEnvironment());
		
		return book;

	}
}
