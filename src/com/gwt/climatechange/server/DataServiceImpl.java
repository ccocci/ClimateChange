package com.gwt.climatechange.server;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.gwt.climatechange.client.DataService;
import com.gwt.climatechange.shared.DataPoint;

/**
 * 
 * The class DataServiceImpl manages the import and filtering of the data.
 * 
 * @author Lina Witzel
 * @history	30-11-2016 LW Methods for RPC of Data
 * 			09-12-2016 LL Appending methods for filter
 * @version 09-12-2016 LL Version 1
 * @responsibilities Splits data according to filter.
 * 
 */

public class DataServiceImpl extends RemoteServiceServlet implements DataService {
	
	private String CSVFileName = "GlobalLandTemperaturesByMajorCity_v1.csv";
	private CSVParser parser = new CSVParser();
	private ArrayList<DataPoint> data;
	private ArrayList<DataPoint> filteredData = new ArrayList<DataPoint>();
	private ArrayList<DataPoint> sliderData = new ArrayList<DataPoint>();
	private final int DAY_IN_MILLISECONDS = 1000*60*60*24;
	
	public DataServiceImpl() {
		try {
			data = parser.parseCSV(CSVFileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * Filters data with respect to city, sdate, edate.
	 * 
	 * @pre 	filteredData != null && city != null && sdate != null && edate != null && data != null
	 * @param 	city, sdate, edate are the values the filter should be applied for
	 * @post 	filteredData contains (appending the previous filtered data) the asked data (according to city between sdate and edate)
	 * @return 	ArrayList containing DataPoints with all the previous data plus the newly asked data.
	 * 
	 */
	
	@Override
	public ArrayList<DataPoint> temperatureMeasurements(String city, Date sdate, Date edate) {
		for(DataPoint Measurement:this.data){
			if(	Measurement.getCity().equals(city) && 
				Measurement.getDate().getTime()>=sdate.getTime()-DAY_IN_MILLISECONDS && 
				Measurement.getDate().getTime()<=edate.getTime()+DAY_IN_MILLISECONDS &&
				!filteredData.contains(Measurement)){
					this.filteredData.add(Measurement);
			}
		}
		return this.filteredData;
	}
	
	/**
	 * 
	 * Filters data with respect to year.
	 * 
	 * @pre 	filteredData != null && city != null && data != null
	 * @param 	year is the value the filter should be applied for
	 * @post 	filteredData contains (appending the previous filtered data) the asked data (according to year)
	 * @return 	ArrayList containing DataPoints with all the previous data plus the newly asked data.
	 * 
	 */
	public ArrayList<DataPoint> temperatureMeasurementsYears(int syear,int eyear) {
		for(DataPoint Measurement:this.data){
			if(	Measurement.getYear()+1900>=syear && Measurement.getYear()+1900<=eyear &&
				!filteredData.contains(Measurement)){
					this.filteredData.add(Measurement);
			}
		}
		return this.filteredData;
	}
	
	/**
	 * 
	 * Filters data with respect to city.
	 * 
	 * @pre 	filteredData != null && city != null && data != null
	 * @param 	city is the value the filter should be applied for
	 * @post 	filteredData contains (appending the previous filtered data) the asked data (according to city)
	 * @return 	ArrayList containing DataPoints with all the previous data plus the newly asked data.
	 * 
	 */
	
	public ArrayList<DataPoint> temperatureMeasurements(String city) {
		for(DataPoint Measurement:this.data){
			if(	Measurement.getCity().equals(city) &&
				!filteredData.contains(Measurement)){
					this.filteredData.add(Measurement);
			}
		}
		return this.filteredData;
	}
	
	/**
	 * 
	 * Filters data with respect to country, sdate, edate.
	 * 
	 * @pre 	filteredData != null && country != null && sdate != null && edate != null && data != null
	 * @param 	country, sdate, edate are the values the filter should be applied for
	 * @post 	filteredData contains (appending to the previous filtered data) the asked data (according to country between startDate and endDate)
	 * @return 	ArrayList containing DataPoints with all the previous data plus the newly asked data.
	 * 
	 */
	public ArrayList<DataPoint> temperatureMeasurementsCountry(String country, Date sdate, Date edate) {
		for(DataPoint Measurement:this.data){
			if(	Measurement.getCountry().equals(country) && 
				Measurement.getDate().getTime()>=sdate.getTime()-DAY_IN_MILLISECONDS && 
				Measurement.getDate().getTime()<=edate.getTime()+DAY_IN_MILLISECONDS &&
				!filteredData.contains(Measurement)){
					this.filteredData.add(Measurement);
			}
		}
		return this.filteredData;
	}
	
	/**
	 * 
	 * Filters data with respect to country.
	 * 
	 * @pre 	filteredData != null && country != null && data != null
	 * @param 	country is the value the filter should be applied for
	 * @post 	filteredData contains (appending to the previous filtered data) the asked data (according to one country)
	 * @return 	ArrayList containing DataPoints with all the previous data plus the newly asked data.
	 * 
	 */
	public ArrayList<DataPoint> temperatureMeasurementsCountry(String country) {
		for(DataPoint Measurement:this.data){
			if(	Measurement.getCountry().equals(country) &&
				!filteredData.contains(Measurement)){
					this.filteredData.add(Measurement);
			}
		}
		return this.filteredData;
	}
	
	/**
	 * 
	 * Filters data dependent on city and country between sdate and edate.
	 * 
	 * @pre		filteredData != null && data != null && country != null && city != null && sdate != null && edate != null
	 * @param	defines data for city in country between startDate and endDate
	 * @post	filteredData contains data for a specific city in a specific country between startDate and EndDate
	 * @return	ArrayList filteredData containing the above defined DataPoints.
	 * 
	 */
	
	public ArrayList<DataPoint> temperatureMeasurementsCityCountry(String country, String city, Date sdate, Date edate){
		for(DataPoint Measurement:this.data){
			if(	Measurement.getCity().equals(city) &&
				Measurement.getCountry().equals(country) &&
				Measurement.getDate().getTime() >= sdate.getTime()-DAY_IN_MILLISECONDS &&
				Measurement.getDate().getTime() <= edate.getTime()+DAY_IN_MILLISECONDS){
					this.filteredData.add(Measurement);
			}
		}
		return this.filteredData;
	}
	
	/**
	 * 
	 * Filters data dependent on city and country.
	 * 
	 * @pre		filteredData != null && data != null && country != null && city != null
	 * @param	defines data for city in country
	 * @post	filteredData contains data for a specific city in a specific country
	 * @return	ArrayList filteredData containing the above defined DataPoints.
	 * 
	 */
	
	public ArrayList<DataPoint> temperatureMeasurementsCityCountry(String country, String city){
		for(DataPoint Measurement:this.data){
			if(	Measurement.getCity().equals(city) &&
				Measurement.getCountry().equals(country) &&
				!filteredData.contains(Measurement)){
					this.filteredData.add(Measurement);
			}
		}
		return this.filteredData;
	}
	
	/**
	 * 
	 * Clears the ArrayList filteredData which is used to send the data to the client.
	 * 
	 * @pre 	filteredData != null
	 * @post 	ArrayList is empty
	 * @return 	Empty ArrayList of DataPoints
	 * 
	 */
	
	public ArrayList<DataPoint> clearMeasurements(){
		this.filteredData.clear();
		return this.filteredData;
	}
	
	/**
	 * 
	 * Gives all the names of the cities stored in an ArrayList.
	 * 
	 * @pre		data != null
	 * @post	ArrayList cities contains all cities from the data as Strings
	 * @return	ArrayList with all names of the cities
	 * 
	 */
	
	public ArrayList<String> getCities(){
		ArrayList<String> cities = new ArrayList<String>();
		for(DataPoint Measurement:this.data){
			if(!cities.contains(Measurement.getCity())){
				cities.add(Measurement.getCity());
			}
		}
		return cities;
	}
	
	/**
	 * 
	 * Gives all the names the countries stored in an ArrayList.
	 * @pre		data != null
	 * @post	ArrayList countries contains all countries from the data as Strings
	 * @return	ArrayList with all names of the countries
	 * 
	 */
	
	public ArrayList<String> getCountries(){
		ArrayList<String> countries = new ArrayList<String>();
		for(DataPoint Measurement:this.data){
			if(!countries.contains(Measurement.getCountry())){
				countries.add(Measurement.getCountry());
			}
		}
		return countries;
	}
	
	/**
	 * 
	 * Filters data for all city at a specific time.
	 * 
	 * @pre 	data != null && filteredData != null && date != null
	 * @param	sliderData contains all cities for which the data should be extracted
	 * 			date is the date for all the measurements
	 * @post	the asked data is stored in an ArrayList
	 * @return	ArrayList sliderData contains all DataPoints for all cities at a specific time.
	 * 
	 */
	
	public ArrayList<DataPoint> temperatureMeasurementsOfAllCitiesAtDate(Date date){
		sliderData.clear();
		for(DataPoint Measurement:this.data){
			if(Measurement.getDate().getTime() >= date.getTime()-DAY_IN_MILLISECONDS &&
				Measurement.getDate().getTime() <= date.getTime()+DAY_IN_MILLISECONDS){
				this.sliderData.add(Measurement);
			}
		}
		return this.sliderData;
	}
	
	/**
	 * 
	 * Filters data for all city at a specific year.
	 * 
	 * @pre 	data != null && filteredData != null && date != null
	 * @param	sliderData contains all cities for which the data should be extracted
	 * 			date is the date for all the measurements
	 * @post	the asked data is stored in an ArrayList
	 * @return	ArrayList sliderData contains all DataPoints for all cities at a specific year.
	 * 
	 */
	
	public ArrayList<DataPoint> temperatureMeasurementsCitiesAtYear(int year){
		sliderData.clear();
		for(DataPoint Measurement:this.data){
			if(Measurement.getYear()+ 1900 == year){
				this.sliderData.add(Measurement);
			}
		}
		return this.sliderData;
	}
	
	
	
	/**
	 * 
	 * Removes all DataPoints of one specific city.
	 * 
	 * @pre		data != null && filteredData != null && city != null
	 * @param	city for which the DataPoints should be removed
	 * @post	filteredData does not contain measurements of the city anymore
	 * @return	ArrayList filteredData with DataPoints of city removed
	 * 
	 */
	
	public ArrayList<DataPoint> removeCity(String city){
		for(DataPoint Measurement:this.data){
			if(Measurement.getCity().equals(city)){
				this.filteredData.remove(Measurement);
			}
		}
		return this.filteredData;
	}
	
	/**
	 * 
	 * Removes all DataPoints of one specific country.
	 * 
	 * @pre		data != null && filteredData != null && country != null
	 * @param	country for which the DataPoints should be removed
	 * @post	filteredData does not contain measurements of the country anymore
	 * @return	ArrayList filteredData with DataPoints of country removed
	 * 
	 */
	
	public ArrayList<DataPoint> removeCountry(String country){
		for(DataPoint Measurement:this.data){
			if(Measurement.getCountry().equals(country)){
				this.filteredData.remove(Measurement);
			}
		}
		return this.filteredData;
	}
	
	/**
	 * 
	 * Removes all DataPoints between syear and eyear.
	 * 
	 * @pre		syear != null && filteredData != null && eyear != null
	 * @param	syear and eyear between which the DataPoints should be removed
	 * @post	filteredData does not contain DataPoints in timespan anymore
	 * @return	ArrayList filteredData with DataPoints in timespan removed
	 * 
	 */
	
	public ArrayList<DataPoint> removeYears(int syear, int eyear){
		for(DataPoint Measurement:this.data){
			if(	Measurement.getYear()+1900>=syear && Measurement.getYear()+1900<=eyear){
						this.filteredData.remove(Measurement);
				}
		}
		return this.filteredData;
	}
}