package com.ak.demo.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ak.demo.model.Book;
import com.ak.demo.repository.BookRepository;

@Service
public class BookService {
	
	 @Autowired
	 private BookRepository bookRepository;
	 
	 public Book save(Book book) {
		 if(book.getName()!=null) {
			Book bookAvailable = bookRepository.findByName(book.getName());
			if(bookAvailable!=null && bookAvailable.getId()!=null) {
				return bookAvailable;
			}
		 }
		 book.setCreationDate(new Date());
		return bookRepository.save(book);
	 }


}
