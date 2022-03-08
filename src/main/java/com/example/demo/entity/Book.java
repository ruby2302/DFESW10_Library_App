package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	private String author;
	private String genre;
	private Boolean checkedOut;
	private String returnDate;
	private String rating;
	
	public Book() {}
	
	public Book(Long id, String title, String author, String genre, Boolean checkedOut, String returnDate, String rating) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.checkedOut = checkedOut;
		this.returnDate = returnDate;
		this.rating = rating;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Boolean getCheckedOut() {
		return checkedOut;
	}

	public void setCheckedOut(Boolean checkedOut) {
		this.checkedOut = checkedOut;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", genre=" + genre + ", checkedOut="
				+ checkedOut + ", returnDate=" + returnDate + ", rating=" + rating + "]";
	}
	
	
	
	
	
}
	
	
