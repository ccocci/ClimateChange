package com.gwt.climatechange.server;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.gwt.climatechange.shared.DataPoint;

public class CSVParserTest {
	private final String testFileName = "war/csvParserTest.csv";
	private CSVParser parser;
	

	@Before
	public void setUp() {
		parser = new CSVParser();
	}
	
	@After
	public void tearDown() {
		parser = null;
	}

	@Test
	public void testParseCSVNotNullWithTestFile() throws IOException, ParseException {
		ArrayList<DataPoint> tempMeasurs = parser.parseCSV(testFileName);
		assertNotNull(tempMeasurs);
	}

	@Test
	public void testParseCSVNotEmptyWithTestFile() throws IOException, ParseException {
		ArrayList<DataPoint> tempMeasurs = parser.parseCSV(testFileName);
		assertFalse(tempMeasurs.isEmpty());
	}
	
	@Test(expected = FileNotFoundException.class)  
	public void testParseCSVFileNotFoundException() throws IOException, ParseException {
		parser.parseCSV("");
	}


}