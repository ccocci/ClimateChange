package com.gwt.climatechange.client;

import org.junit.Test;
import com.google.gwt.junit.client.GWTTestCase;

public class TablePanelTest extends GWTTestCase{

	@Override
	public String getModuleName() {
		return "com.gwt.climatechange.TablePanel";
	}
	
	@Test
	public void testSetUp(){
		TablePanel table = new TablePanel();
		assertNotNull(table);
	}
}
