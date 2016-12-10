package com.gwt.climatechange.shared;

import java.io.Serializable;
import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

public class DataPoint implements Serializable{
	
	
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
		
		this.city=city;
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
	public double getLatitude() {
		return latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public double getTemperature() {
		return temperature;
	}
	
	public void setTemperature(double temperature){
		this.temperature = temperature;
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
}