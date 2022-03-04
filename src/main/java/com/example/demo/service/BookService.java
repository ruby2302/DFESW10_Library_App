package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.repo.BookRepo;

@Service
public class BookService {
	
	private BookRepo repo;

	public BookService(BookRepo repo) {
		super();
		this.repo = repo;
	}
	
	

}
