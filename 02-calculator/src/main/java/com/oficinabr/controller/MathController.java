package com.oficinabr.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oficinabr.converters.NumberConverter;
import com.oficinabr.exception.DivisionByZeroException;
import com.oficinabr.exception.NegativeNumberException;
import com.oficinabr.exception.UnsupportedMathOperationException;
import com.oficinabr.math.MathService;
 


@RestController
public class MathController {
	

	@Autowired
	private MathService mathService;

	@RequestMapping(value="sum/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double sum(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo ) throws Exception {
		if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}  
		Double num01 = NumberConverter.convertToDouble(numberOne);
		Double num02 = NumberConverter.convertToDouble(numberTwo);

		return mathService.sum(num01, num02);
	}
	
	@RequestMapping(value="/sub/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double sub(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo ) throws Exception {
		if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		Double num01 = NumberConverter.convertToDouble(numberOne);
		Double num02 = NumberConverter.convertToDouble(numberTwo);

		return mathService.sub(num01, num02);
	}
	
	@RequestMapping(value="/mul/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double mul(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo ) throws Exception {
		if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		Double num01 = NumberConverter.convertToDouble(numberOne);
		Double num02 = NumberConverter.convertToDouble(numberTwo);

		return mathService.mul(num01, num02);
	}
	
	@RequestMapping(value="/div/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double div(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo ) throws Exception {
		if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		
		if(NumberConverter.convertToDouble(numberTwo).equals(0D)) {
			throw new DivisionByZeroException("The divider cannot be zero!");
		}
		Double num01 = NumberConverter.convertToDouble(numberOne);
		Double num02 = NumberConverter.convertToDouble(numberTwo);

		return mathService.div(num01, num02);
	}
	
	@RequestMapping(value="/med/{numberOne}/{numberTwo}", method=RequestMethod.GET)
	public Double med(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo ) throws Exception {
		if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		Double num01 = NumberConverter.convertToDouble(numberOne);
		Double num02 = NumberConverter.convertToDouble(numberTwo);

		return mathService.med(num01, num02);
	}
	
	@RequestMapping(value="/sqrt/{numberOne}", method=RequestMethod.GET)
	public Double sqrt(@PathVariable("numberOne") String numberOne ) throws Exception {
		if(!NumberConverter.isNumeric(numberOne) ) {
			throw new UnsupportedMathOperationException("Please set a numeric value!");
		}
		
		if(NumberConverter.convertToDouble(numberOne) < 0D ) {
			throw new NegativeNumberException("Number must be greater or equal to zero!");
		}
		
		Double num01 = NumberConverter.convertToDouble(numberOne);

		return mathService.sqrt(num01);
	}
}