package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Book;
import com.example.demo.service.BookService;

@RestController
public class BookController {
	
	private BookService service;
	
	@PostMapping("/addBook")
	public ResponseEntity<Book> addbook(@RequestBody Book book) {
		return new ResponseEntity<>(this.service.addBook(book), HttpStatus.CREATED);
		
	}
	
	
	
	

}
