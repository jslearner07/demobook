package com.ak.demo.repository;

import com.ak.demo.model.Book;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
	Book findByName(String name);
}
