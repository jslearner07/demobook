package com.ak.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ak.demo.exception.ResourceNotFoundException;
import com.ak.demo.model.Book;
import com.ak.demo.repository.BookRepository;
import com.ak.demo.service.BookService;

@RestController
public class BookController {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private BookService bookService;

	@RequestMapping(value = "/rest/books/{id}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	Book get(@PathVariable Long id) {
		return findBookOrThrowException(id);
	}

	@RequestMapping(value = "/rest/books", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	Book createOperation(@RequestBody Book book) {
		return bookService.save(book);
	}

	private Book findBookOrThrowException(@PathVariable Long id) {
		return bookRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
	}

}
