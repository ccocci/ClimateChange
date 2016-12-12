package com.gwt.climatechange.client;

import java.util.ArrayList;
import java.util.Date;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.gwt.climatechange.shared.DataPoint;

/**
* 
* The interface DataService belongs to the RPC of Data.
* 
* @author Lina Witzel
* @history  30-11-2016 LW Methods for RPC of Data
* 			09-12-2016 LL Appending methods for filter
* @version  09-12-2016 LL Version 1
* @responsabilities Defines all methods to implement in DataServiceImpl to use for the AsyncCallback.
*
*/

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
	ArrayList<DataPoint> temperatureMeasurementsCitiesAtYear(int year);
	ArrayList<DataPoint> removeCity(String city);
	ArrayList<DataPoint> removeCountry(String country);
	ArrayList<DataPoint> removeYears(int syear, int eyear);
}