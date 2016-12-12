package com.gwt.climatechange.shared;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * This class manages all values for a DataPoint to use for the data import 
 * and filling in the data in the worldMap and dataTable
 * 
 * @author Lina Witzel
 * @history 30-11-2016 LW First version
 * @version 30-11-2016 LW Version 1
 * @responsibilities Manages all data.
 * 
 */

public class DataPoint implements Serializable{
	
	private String city;
	private String country;
	private double latitude;
	private double longitude;
	private double temperature;
	private double uncertainty;
	private Date date;
	
/*Empty constructor for serialization*/
public DataPoint(){}

/**
 * Constructor to set all data for one DataPoint
 */

	public DataPoint(double temperature,double uncertainty,Date date, String city, String country, double latitude, double longitude) {
		
		this.city=city;
		this.country = country;
		this.latitude = latitude;
		this.longitude = longitude;
		this.temperature = temperature;
		this.uncertainty = uncertainty;
		this.date = date;
	}
	
	/**
	 * Getters for city, country, latitude, longitude, temperature, uncertainty and date
	 * Setter for temperature (to set average temperature seperately)
	 */
	
	public String getCountry() {
		return country;
	}
	public double getLatitude() {
		return latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public double getTemperature() {
		return temperature;
	}
	public double getUncertainty() {
		return uncertainty;
	}
	public Date getDate() {
		return date;
	}
	public int getYear(){
		return date.getYear();
	}
	public String getCity(){
		return city;
	}
	public void setTemperature(double temperature){
		this.temperature=temperature;
	}
}