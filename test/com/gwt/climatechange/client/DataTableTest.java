package com.gwt.climatechange.client;

import org.junit.Test;
import com.google.gwt.junit.client.GWTTestCase;

public class DataTableTest extends GWTTestCase{

	@Override
	public String getModuleName() {
		return "com.gwt.climatechange.DataTable";
	}
	
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
