package com.gwt.climatechange.client;

import java.util.ArrayList;
import java.util.Date;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import com.gwt.climatechange.shared.DataPoint;

@RemoteServiceRelativePath("data")
public interface DataService extends RemoteService{
	ArrayList<DataPoint> temperatureMeasurements(String city, Date sdate, Date edate);
	ArrayList<DataPoint> temperatureMeasurements(String city);
	ArrayList<DataPoint> temperatureMeasurementsYears(int syear, int eyear);
	ArrayList<DataPoint> temperatureMeasurementsCountry(String country, Date sdate, Date edate);
	ArrayList<DataPoint> temperatureMeasurementsCountry(String country);
	ArrayList<DataPoint> temperatureMeasurementsCityCountry(String country, String city);
	ArrayList<DataPoint> temperatureMeasurementsCityCountry(String country, String city, Date sdate, Date edate);
	ArrayList<DataPoint> clearMeasurements();
	ArrayList<String> getCities();
	ArrayList<String> getCountries();
	ArrayList<DataPoint> temperatureMeasurementsOfAllCitiesAtDate(Date date);
	ArrayList<DataPoint> temperatureMeasurementsOfAllCitiesAtYear(Date date);
	ArrayList<DataPoint> removeCity(String city);
	ArrayList<DataPoint> removeCountry(String country);
}