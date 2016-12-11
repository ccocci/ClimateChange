package com.gwt.climatechange.shared;

import static org.junit.Assert.*;
import java.util.Date;
import org.junit.Test;

public class DataPointTest {

	private final String TEST_CITY = "Berlin";
	private final String TEST_COUNTRY = "Germany";
	private final Date TEST_DATE = new Date();
	private final double TEST_LATITUDE = 52.24;
	private final double TEST_LONGITUDE = 13.14;
	private final double TEST_TEMPERATURE = 22.031;
	private final double TEST_TEMPERATURE_NEW = 21.034;
	private final double TEST_UNCERTAINTY = 0.345;
	
	@Test
	public void testDataPointEmpty(){
		DataPoint measurement = new DataPoint();
		assertNotNull(measurement);
	}
	
	@Test
	public void testDataPointDefault() {
		DataPoint measurement = new DataPoint(TEST_TEMPERATURE,TEST_UNCERTAINTY, TEST_DATE, TEST_CITY, TEST_COUNTRY, TEST_LATITUDE, TEST_LONGITUDE);
		assertNotNull(measurement);
	}
	
	@Test
	public void testGetTemperature(){
		DataPoint measurement = new DataPoint(TEST_TEMPERATURE,TEST_UNCERTAINTY, TEST_DATE, TEST_CITY, TEST_COUNTRY, TEST_LATITUDE, TEST_LONGITUDE);
		assertEquals(TEST_TEMPERATURE, measurement.getTemperature());
	}
	
	@Test
	public void testGetUncertainty(){
		DataPoint measurement = new DataPoint(TEST_TEMPERATURE,TEST_UNCERTAINTY, TEST_DATE, TEST_CITY, TEST_COUNTRY, TEST_LATITUDE, TEST_LONGITUDE);
		assertEquals(TEST_UNCERTAINTY, measurement.getUncertainty());
	}
	
	@Test
	public void testGetDate(){
		DataPoint measurement = new DataPoint(TEST_TEMPERATURE,TEST_UNCERTAINTY, TEST_DATE, TEST_CITY, TEST_COUNTRY, TEST_LATITUDE, TEST_LONGITUDE);
		assertEquals(TEST_DATE, measurement.getDate());
	}
	
	@Test
	public void testGetCity(){
		DataPoint measurement = new DataPoint(TEST_TEMPERATURE,TEST_UNCERTAINTY, TEST_DATE, TEST_CITY, TEST_COUNTRY, TEST_LATITUDE, TEST_LONGITUDE);
		assertEquals(TEST_CITY, measurement.getCity());
	}
	
	@Test
	public void testGetCountry(){
		DataPoint measurement = new DataPoint(TEST_TEMPERATURE,TEST_UNCERTAINTY, TEST_DATE, TEST_CITY, TEST_COUNTRY, TEST_LATITUDE, TEST_LONGITUDE);
		assertEquals(TEST_COUNTRY, measurement.getCountry());
	}
	
	@Test
	public void testGetYear(){
		DataPoint measurement = new DataPoint(TEST_TEMPERATURE,TEST_UNCERTAINTY, TEST_DATE, TEST_CITY, TEST_COUNTRY, TEST_LATITUDE, TEST_LONGITUDE);
		assertEquals(TEST_DATE.getYear(), measurement.getYear());
	}
	
	@Test
	public void testGetLatitude(){
		DataPoint measurement = new DataPoint(TEST_TEMPERATURE,TEST_UNCERTAINTY, TEST_DATE, TEST_CITY, TEST_COUNTRY, TEST_LATITUDE, TEST_LONGITUDE);
		assertEquals(TEST_LATITUDE, measurement.getLatitude());
	}
	
	@Test
	public void testGetLongitude(){
		DataPoint measurement = new DataPoint(TEST_TEMPERATURE,TEST_UNCERTAINTY, TEST_DATE, TEST_CITY, TEST_COUNTRY, TEST_LATITUDE, TEST_LONGITUDE);
		assertEquals(TEST_LONGITUDE, measurement.getLongitude());
	}
	
	@Test
	public void testSetTemperature(){
		DataPoint measurement = new DataPoint(TEST_TEMPERATURE,TEST_UNCERTAINTY, TEST_DATE, TEST_CITY, TEST_COUNTRY, TEST_LATITUDE, TEST_LONGITUDE);
		measurement.setTemperature(TEST_TEMPERATURE_NEW);
		assertEquals(TEST_TEMPERATURE_NEW, measurement.getTemperature());
	}
}
