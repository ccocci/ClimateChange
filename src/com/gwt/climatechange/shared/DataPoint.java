package com.gwt.climatechange.shared;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

public class DataPoint implements IsSerializable{
	
	
	private String city;
	private String country;
	private double latitude;
	private double longitude;
	private double temperature;
	private double uncertainty;
	private Date date;
	
/*Empty constructor needed for serialization*/
public DataPoint(){}

public DataPoint(double temperature,double uncertainty,Date date, String city, String country, double latitude, double longitude) {
		
		this.setCity(city);
		this.country = country;
		this.latitude = latitude;
		this.longitude = longitude;
		this.temperature = temperature;
		this.uncertainty = uncertainty;
		this.date= date;
	}


	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getTemperature() {
		return temperature;
	}
	public void setAverageTemperature(double temperature) {
		this.temperature = temperature;
	}
	public double getUncertainty() {
		return uncertainty;
	}
	public void setUncertainty(double uncertainty) {
		this.uncertainty = uncertainty;
	}
	public Date getDate() {
		return date;
	}
	public int getYear(){
		return date.getYear();
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setCity(String city) {
		this.city= city;
	}
	public String getCity(){
		return city;
	}
}