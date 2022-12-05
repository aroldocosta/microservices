package com.oficinabr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oficinabr.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {}
