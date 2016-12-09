package com.gwt.climatechange.client;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

import com.gwt.climatechange.shared.DataPoint;

public interface DataServiceAsync {
	void temperatureMeasurements(String city, Date sdate, Date edate,
			AsyncCallback<ArrayList<DataPoint>> callback);
	void temperatureMeasurements(String city, AsyncCallback<ArrayList<DataPoint>> callback);
	void temperatureMeasurementsYears(int syear, int eyear, AsyncCallback<ArrayList<DataPoint>> callback);
	void temperatureMeasurementsCountry(String country, Date sdate, Date edate,
			AsyncCallback<ArrayList<DataPoint>> callback);
	void temperatureMeasurementsCountry(String country, AsyncCallback<ArrayList<DataPoint>> callback);
	void temperatureMeasurementsCityCountry(String country, String city, 
			AsyncCallback<ArrayList<DataPoint>> callback);
	void temperatureMeasurementsCityCountry(String country, String city, Date sdate, Date edate,
			AsyncCallback<ArrayList<DataPoint>> callback);
	void clearMeasurements(AsyncCallback<ArrayList<DataPoint>> callback);
	void temperatureMeasurementsOfAllCitiesAtDate(Date date,
			AsyncCallback<ArrayList<DataPoint>> callback);
	void temperatureMeasurementsOfAllCitiesAtYear(Date date,
			AsyncCallback<ArrayList<DataPoint>> callback);
	void getCities(AsyncCallback<ArrayList<String>> callback);
	void getCountries(AsyncCallback<ArrayList<String>> callback);
	void removeCity(String city, AsyncCallback<ArrayList<DataPoint>> callback);
	void removeCountry(String country, AsyncCallback<ArrayList<DataPoint>> callback);
	void removeYears(int syear, int eyear,AsyncCallback<ArrayList<DataPoint>> callback);
}