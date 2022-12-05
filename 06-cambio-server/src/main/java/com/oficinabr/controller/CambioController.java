package com.oficinabr.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oficinabr.model.Cambio;
import com.oficinabr.repository.CambioRepository;

@RestController
@RequestMapping("/cambio-service")
public class CambioController {

	@Autowired
	private Environment environment;
	
	@Autowired
	private CambioRepository cambioRepository;
	
	@GetMapping(value = "{amount}/{from}/{to}")
	public Cambio getCambio(@PathVariable("amount") BigDecimal amount,
			@PathVariable("from") String from,
			@PathVariable("to") String to) {
		
		
		var cambio = cambioRepository.findByFromAndTo(from, to);
		
		if(cambio == null) {
			throw new RuntimeException("Currency unsupported");
		}
		
		var port = environment.getProperty("local.server.port");
		
		cambio.setEnvironment(port);
		
		BigDecimal conversionFactor = cambio.getConversionFactor();
		
		BigDecimal convertedValue = amount.multiply(conversionFactor);
		
		cambio.setConvertedValue(convertedValue.setScale(2, RoundingMode.CEILING));
		
		return cambio;
	}

}
