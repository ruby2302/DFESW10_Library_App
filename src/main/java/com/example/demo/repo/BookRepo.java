package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, Long>,
	QueryByExampleExecutor<Book> {
	
	public List<Book> findBookByoutOf10GreaterThan(Integer outOf10);
}


