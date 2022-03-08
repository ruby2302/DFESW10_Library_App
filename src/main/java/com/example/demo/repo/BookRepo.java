package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.example.demo.entity.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, Long>, QueryByExampleExecutor<Book> {}
	

