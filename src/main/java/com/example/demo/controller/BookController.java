package com.example.demo.controller;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/getAllBooks")
	public ResponseEntity<List<Book>> getall() {
		return new ResponseEntity<>(this.service.getAll(), HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<Book> findybyid(@PathVariable Long id) {
		return new ResponseEntity<>(this.service.findbyId(id), HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/searchLibrary")
	public ResponseEntity<List<Book>> searchlibrary(@RequestBody Example<Book> book) {
		return new ResponseEntity<>(this.service.search(book), HttpStatus.ACCEPTED);
	}
	
	
	

}
