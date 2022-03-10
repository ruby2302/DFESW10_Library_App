package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import com.example.demo.entity.Book;
import com.example.demo.repo.BookRepo;

@SpringBootTest
public class BookServiceUnitTest {
	
	@Autowired
	private BookService service;
	
	@MockBean
	private BookRepo repo;
	
	@Test
	void testaddBook() {
		//GIVEN
		final Book inputBook = new Book(null, "The Little Book of Hygge", "Meik Wiking", "Lifestyle", false, null, 7);
		final Book returnBook = new Book(1L, "The Little Book of Hygge", "Meik Wiking", "Lifestyle", false, null, 7);
		//WHEN
		Mockito.when(this.repo.save(inputBook)).thenReturn(returnBook);
		//THEN
		Assertions.assertThat(this.service.addBook(inputBook)).isEqualTo(returnBook);
		//VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).save(inputBook);
	}
	
	@Test
	void testgetAll() {
		//GIVEN
		final List<Book> booklist = new ArrayList<Book>();
		//WHEN
		Mockito.when(this.repo.findAll()).thenReturn(booklist);
		//THEN
		Assertions.assertThat(this.service.getAll()).isEqualTo(booklist);
		//VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}
	
	@Test
	void testfindbyId() {
		//GIVEN
		final Long id = 1L;
		final Book foundbook = new Book(1L, "The Little Book of Hygge", "Meik Wiking", "Lifestyle", false, null, 7);
		//WHEN
		Mockito.when(this.repo.findById(id)).thenReturn(Optional.of(foundbook));
		//THEN
		Assertions.assertThat(this.service.findbyId(id)).isEqualTo(foundbook);
		//VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
	}
	
	@Test
	void testsearch() {
		//GIVEN
		final Book book = new Book(null, null, "Meik Wiking", null, null, null, null);
		final Example<Book> searchby = Example.of(book);
		final List<Book> foundbooks = new ArrayList<Book>();
		//WHEN
		Mockito.when(this.repo.findAll(searchby, Sort.unsorted())).thenReturn(foundbooks);
		//THEN
		Assertions.assertThat(this.service.search(searchby)).isEqualTo(foundbooks);
		//VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).findAll(searchby, Sort.unsorted());
		
	}
	
	@Test
	void testupdate() {
		//GIVEN
		Long id = 1L;
		Book preUpdate = new Book(1L, "The Little Book of Hygge", "Meik Wiking", "Lifestyle", false, null, 10);
		Book bookupdate = new Book(1L, "The Little Book of Hygge", "Meik Wiking", "Lifestyle", false, null, 4);
		Book postUpdate = new Book(1L, "The Little Book of Hygge", "Meik Wiking", "Lifestyle", false, null, 4);
		
		//WHEN
		Mockito.when(this.repo.findById(id)).thenReturn(Optional.of(preUpdate));
		Mockito.when(this.repo.save(postUpdate)).thenReturn(postUpdate);
		
		//THEN
		Assertions.assertThat(this.service.update(id, bookupdate)).isEqualTo(postUpdate);
		
		//VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.repo, Mockito.times(1)).save(postUpdate); 
	}
	
	@Test
	void testdelete() {
		//GIVEN
		final Long id = 1L;
		
		//WHEN
		Mockito.when(this.repo.existsById(id)).thenReturn(false);
		
		//THEN
		Assertions.assertThat(this.service.delete(id)).isEqualTo(true);
		
		//VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).existsById(id);
		Mockito.verify(this.repo, Mockito.times(1)).deleteById(Mockito.anyLong());
		
		
	}

}
