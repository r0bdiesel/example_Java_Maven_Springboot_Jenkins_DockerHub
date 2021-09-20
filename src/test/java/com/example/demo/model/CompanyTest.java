package com.example.demo.model;

import static org.junit.Assert.assertEquals;				

import org.junit.Test;	

public class CompanyTest {
	
	@Test
	public void testGetters() throws Exception {
		Long id = 1L;
		Company toTest = new Company(1L,"companyName");
		assertEquals("companyName", toTest.getName());
		assertEquals(id, toTest.getId());
	}
	
	@Test
	public void testSetters() throws Exception {
		Long id = 2L;
		Company toTest = new Company(1L,"companyName");
		toTest.setId(2L);
		toTest.setName("TEST");
		assertEquals("TEST", toTest.getName());
		assertEquals(id, toTest.getId());
	}

}
