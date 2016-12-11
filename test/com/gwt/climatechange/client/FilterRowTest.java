package com.gwt.climatechange.client;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;


public class FilterRowTest {
	
	private final String TEST_CITY = "Berlin";
	private final String TEST_COUNTRY = "Germany";
	private final Date START_DATE = new Date(2000, 8, 1);
	private final Date END_DATE = new Date(2000, 9, 1);
	
	@Test
	public void testFilterRowAll(){
		FilterRow row = new FilterRow(TEST_COUNTRY, TEST_CITY, START_DATE, END_DATE);
		assertNotNull(row);
	}
	
	@Test
	public void testFilterRowCity(){
		FilterRow row = new FilterRow(TEST_CITY);
		assertNotNull(row);
	}
	
	@Test
	public void testGetCountry(){
		FilterRow row = new FilterRow(TEST_COUNTRY, TEST_CITY, START_DATE, END_DATE);
		assertEquals(TEST_COUNTRY, row.getCountry());
	}
	
	@Test
	public void testGetCity(){
		FilterRow row = new FilterRow(TEST_COUNTRY, TEST_CITY, START_DATE, END_DATE);
		assertEquals(TEST_CITY, row.getCity());
	}
	
	@Test
	public void testGetStartDae(){
		FilterRow row = new FilterRow(TEST_COUNTRY, TEST_CITY, START_DATE, END_DATE);
		assertEquals(START_DATE, row.getStartDate());
	}
	
	@Test
	public void testGetEndDate(){
		FilterRow row = new FilterRow(TEST_COUNTRY, TEST_CITY, START_DATE, END_DATE);
		assertEquals(END_DATE, row.getEndDate());
	}
	
	@Test
	public void testGetRemoveButton(){
		FilterRow row = new FilterRow(TEST_COUNTRY, TEST_CITY, START_DATE, END_DATE);
		assertNotNull(row.getRemoveButton());
	}
	
	
}
