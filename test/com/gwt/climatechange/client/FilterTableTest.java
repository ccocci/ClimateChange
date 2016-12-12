package com.gwt.climatechange.client;

import org.junit.Test;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.ui.FlexTable;

public class FilterTableTest extends GWTTestCase{
	
	FlexTable testFlexTable = new FlexTable();
	private final String TEST_CITY = "Berlin";
	private final String TEST_COUNTRY = "Germany";
	
	@Override
	public String getModuleName() {
		return "com.gwt.climatechange.FilterTable";
	}
	
	@Test
	public void testSetUp(){
		FilterTable table = new FilterTable();
		assertNotNull(table);
	}
	
	@Test
	public void testGetFilterTable(){
		FilterTable table = new FilterTable();
		assertNotNull(table.getFilterTable());
	}
	
	@Test
	public void testGetCurrentRow(){
		FilterTable table = new FilterTable();
		assertNotNull(table.getCurrentRow(TEST_CITY));
	}
	
	@Test
	public void testGetCurrentRowCountry(){
		FilterTable table = new FilterTable();
		assertNotNull(table.getCurrentRowCountry(TEST_COUNTRY));
	}
	
	@Test
	public void testGetCurrentCities(){
		FilterTable table = new FilterTable();
		assertNotNull(table.getCurrentCities());
	}
	
	@Test
	public void testGetCurrentCountries(){
		FilterTable table = new FilterTable();
		assertNotNull(table.getCurrentCountries());
	}
}
