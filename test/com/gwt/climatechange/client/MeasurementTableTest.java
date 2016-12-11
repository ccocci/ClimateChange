package com.gwt.climatechange.client;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class MeasurementTableTest {

	@Test
	public void testMeasurementTableSetUp(){
		MeasurementTable table = new MeasurementTable();
		assertNotNull(table);
	}
	
	@Test
	public void testGetMeasurementTable(){
		MeasurementTable table = new MeasurementTable();
		assertNotNull(table.getMeasurementTable());
	}
}
