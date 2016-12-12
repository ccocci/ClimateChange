package com.gwt.climatechange.server;

import org.junit.Test;
import com.google.gwt.junit.client.GWTTestCase;

public class DataServiceImplTest extends GWTTestCase{
	
	@Override
	public String getModuleName() {
		return "com.gwt.climatechange.DataServiceImpl";
	}
	
	@Test
	public void testParsing(){
		DataServiceImpl testData = new DataServiceImpl();
		assertNotNull(testData);
	}
	
}
