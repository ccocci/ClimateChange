package com.gwt.climatechange.client;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class DataTableTest {

	@Test
	public void testMeasurementTableSetUp(){
		DataTable table = new DataTable();
		assertNotNull(table);
	}
	
	@Test
	public void testGetMeasurementTable(){
		DataTable table = new DataTable();
		assertNotNull(table.getMeasurementTable());
	}
}
