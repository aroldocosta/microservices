package com.oficinabr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oficinabr.model.Cambio;

public interface CambioRepository extends JpaRepository<Cambio, Long> {
	
	Cambio findByFromAndTo(String from, String to);

}
