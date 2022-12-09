package com.oficinabr.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.oficinabr.model.Book;
import com.oficinabr.proxy.CambioProxy;
import com.oficinabr.repository.BookRepository;
import com.oficinabr.response.Cambio;

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
		
//		HashMap<String, String> params = new HashMap<>();
//		params.put("amount", book.getPrice().toString());
//		params.put("from" , "USD");
//		params.put("to", currency);
//		
		
//		var response = new RestTemplate()
//				.getForEntity("http://localhost:8000/cambio-service/"
//						+ "{amount}/{from}/{to}", 
//						Cambio.class,
//				params);
//		
//		var cambio = response.getBody();
//		
//	
//		var price = cambio.getConvertedValue();
		
		var cambio = cambioProxy.getCambio(book.getPrice(), "USD", "BRL");
		
		var price = cambio.getConvertedValue();

		book.setPrice(price);
		
		book.setEnvironment(port);
		
		return book;

	}
}
