package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Book;
import com.example.demo.entity.Cat;
import com.example.demo.repo.BookRepo;

@Service
public class BookService {
	
	private BookRepo repo;

	public BookService(BookRepo repo) {
		super();
		this.repo = repo;
	}
	
	public Book addBook(Book book) {
		return this.repo.save(book);	
	}
	
	public List<Book> getAll() {
		return this.repo.findAll();
	}
	
	public Book findbyId(Long id) {
		Optional<Book> book = this.repo.findById(id);
		return book.get();
	}
	
	public List<Book> search(Example<Book> book) {
		return this.repo.findAll(book, Sort.unsorted());
		
	}
	
	public Book update(Long id, Book updatedbook) {
		Book existingBook = this.repo.findById(id).orElseThrow(EntityNotFoundException::new);
		
		existingBook.setTitle(updatedbook.getTitle());
		existingBook.setAuthor(updatedbook.getAuthor());
		existingBook.setGenre(updatedbook.getGenre());
		existingBook.setCheckedOut(updatedbook.getCheckedOut());
		existingBook.setReturnDate(updatedbook.getReturnDate());
		
		return this.repo.save(existingBook);
		
	}
	
	public Boolean delete(Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}
	
}
