package com.gwt.climatechange.server;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import org.junit.Test;
import com.google.gwt.junit.client.GWTTestCase;

public class CSVParserTest extends GWTTestCase{
	private CSVParser parser;

	@Override
	public String getModuleName() {
		return "com.gwt.climatechange.CSVParser";
	}
	
	@Test(expected = FileNotFoundException.class)  
	public void testParseCSVFileNotFoundException() throws IOException, ParseException {
		parser.parseCSV("");
	}


}
