package com.gwt.climatechange.server;

import org.junit.Test;
import static org.junit.Assert.*;

public class DataServiceImplTest{
	
	@Test
	public void testParsing(){
		DataServiceImpl testData = new DataServiceImpl();
		assertNotNull(testData);
	}
	
}
