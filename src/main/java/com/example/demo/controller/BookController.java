package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.Book;
import com.example.demo.service.BookService;

@RestController
public class BookController {
	
	private BookService service;
	
	@Autowired
	public BookController(BookService service) {
		super();
		this.service = service;
	}

	@PostMapping("/addBook")
	public ResponseEntity<Book> addbook(@RequestBody Book book) {
		return new ResponseEntity<>(this.service.addBook(book), HttpStatus.CREATED);
		
	}
	
	@GetMapping("/getAllBooks")
	public ResponseEntity<List<Book>> getall() {
		return new ResponseEntity<>(this.service.getAll(), HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<Book> findbyid(@PathVariable Long id) {
		return new ResponseEntity<>(this.service.findbyId(id), HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/searchLibrary")
	public ResponseEntity<List<Book>> searchlibrary(@RequestBody Book book) {
		Example<Book> examplebook = Example.of(book);
		return new ResponseEntity<>(this.service.search(examplebook), HttpStatus.ACCEPTED);
	}
	
	
	@PutMapping("/updateBook/{id}")
	public ResponseEntity<Book> updatebook(@PathVariable Long id, @RequestBody Book updatedbook) {
		return new ResponseEntity<>(this.service.update(id, updatedbook), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/deleteBook/{id}")
	public ResponseEntity<Boolean> deletebook(@PathVariable Long id) {
		boolean hasDeleted = this.service.delete(id);
		
		if (hasDeleted) {
			return new ResponseEntity<>(hasDeleted, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>(hasDeleted, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
