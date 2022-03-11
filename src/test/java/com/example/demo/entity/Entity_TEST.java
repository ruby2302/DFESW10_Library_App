package com.example.demo.entity;


import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

public class Entity_TEST {
	@Test
	public void toString_TEST() {
		Book book = new Book();
	    assertTrue(book.toString() instanceof String);
	}

}
