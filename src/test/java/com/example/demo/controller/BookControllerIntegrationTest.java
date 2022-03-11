package com.example.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.Example;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import com.example.demo.entity.Book;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = {"classpath:book-schema.sql", "classpath:book-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@AutoConfigureMockMvc
public class BookControllerIntegrationTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Test
	void addbookTest() throws Exception {
		//Given
		Book newBook = new Book(null, "Let The Great World Spin", "Colum McCann", "Fiction", false, null, 6);
		String newBookJSON = this.mapper.writeValueAsString(newBook);
		
		Book savedBook = new Book(4L,"Let The Great World Spin", "Colum McCann", "Fiction", false, null, 6); 
		String savedBookJSON = this.mapper.writeValueAsString(savedBook);
		
		//When
		RequestBuilder request = post("/addBook").contentType(MediaType.APPLICATION_JSON).content(newBookJSON);
		
		ResultMatcher responseStatus = status().isCreated();
		ResultMatcher responseContent = content().json(savedBookJSON);
		
		//Then
		this.mvc.perform(request).andExpect(responseStatus).andExpect(responseContent);
	}
	
	@Test
	void getallTest() throws Exception {
		//Given
		List<Book> booklist = new ArrayList<Book>();
		booklist.add(new Book(1L, "H is for Hawk", "Helen Macdonald", "Fiction", false, null, 8));
		booklist.add(new Book(2L, "The Last Kings of Sark", "Rosa Rankin-Gee", "Fiction", false, null, 7));
		booklist.add(new Book(3L, "Wise Children", "Angela Carter", "Fiction", false, null, 9));
		
		String booklistJSON = this.mapper.writeValueAsString(booklist);
		
		//When
		RequestBuilder request = get("/getAllBooks");
		
		ResultMatcher responseStatus = status().isAccepted();
		ResultMatcher responseContent = content().json(booklistJSON);
		
		//Then
		this.mvc.perform(request).andExpect(responseStatus).andExpect(responseContent);
	}
	
	@Test
	void findbyidtest() throws Exception {
		//Given
		Book savedBook = new Book(1L, "H is for Hawk", "Helen Macdonald", "Fiction", false, null, 8);
		String savedBookJSON = this.mapper.writeValueAsString(savedBook);
		
		//When
		RequestBuilder request = get("/findById/1");
		
		ResultMatcher responseStatus = status().isAccepted();
		ResultMatcher responseContent = content().json(savedBookJSON);
		
		//Then
		this.mvc.perform(request).andExpect(responseContent).andExpect(responseStatus);
		
	}
	
	@Test
	void findyByRatingTest() throws Exception {
		//Given
		List<Book> foundbooks = new ArrayList<Book>();
		foundbooks.add(new Book(1L, "H is for Hawk", "Helen Macdonald", "Fiction", false, null, 8));
		foundbooks.add(new Book(3L, "Wise Children", "Angela Carter", "Fiction", false, null, 9));
		String foundbooksJSON = this.mapper.writeValueAsString(foundbooks);
		
		//When
		RequestBuilder request = get("/findByRating/7");
		
		ResultMatcher responseStatus = status().isAccepted();
		ResultMatcher responseContent = content().json(foundbooksJSON);
		
		//Then
		this.mvc.perform(request).andExpect(responseStatus).andExpect(responseContent);
		
		
	}
	
	//@Test
	//void SearchbyTest() throws Exception {   
		//Given
		//Book searchvariable = new Book(null, null, "Angela Carter", null, null, null, null);
		//Example<Book> search = Example.of(searchvariable);
		//String searchJSON = this.mapper.writeValueAsString(search);
		
		
		//List<Book> foundbooks = new ArrayList<Book>();
		//foundbooks.add(new Book(3L, "Wise Children", "Angela Carter", "Fiction", false, null, 9));
		//String foundbooksJSON = this.mapper.writeValueAsString(foundbooks);
		
		//When
		//RequestBuilder request = get("/searchLibrary").contentType(MediaType.APPLICATION_JSON).content(searchJSON);
		
		//ResultMatcher responseStatus = status().isAccepted();
		//ResultMatcher responseContent = content().json(foundbooksJSON);
		
		//Then
		//this.mvc.perform(request).andExpect(responseStatus).andExpect(responseContent);				
	//}
	
	@Test
	void updateBookTest() throws Exception {
		//Given
		Book newinfo = new Book(3L, "Wise Children", "Angela Carter", "Fiction", true, LocalDate.of(15, 03, 22), 9);
		String newinfoJSON = this.mapper.writeValueAsString(newinfo);
		
		//When
		RequestBuilder request = put("/updateBook/3").contentType(MediaType.APPLICATION_JSON).content(newinfoJSON);
		
		ResultMatcher responseStatus = status().isAccepted();
		ResultMatcher responseContent = content().json(newinfoJSON);
		
		//Then
		this.mvc.perform(request).andExpect(responseStatus).andExpect(responseContent);			
		
	}   
	
	@Test
	void deletebookTest() throws Exception {
		this.mvc.perform(delete("/deleteBook/3"))
		.andExpect(status().isAccepted());
	}	
	
	
		
	
}
