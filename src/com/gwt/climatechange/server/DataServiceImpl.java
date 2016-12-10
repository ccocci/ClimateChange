package com.gwt.climatechange.server;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import com.gwt.climatechange.client.DataService;
import com.gwt.climatechange.shared.DataPoint;

/**
 * This class manages the filtering of the data.
 * @author		Carla Coccia
 * @history 	2016-08-11 JL First version
 * @version 	2016-08-11 JL 0.1.0
 * @responsibilities
 * 				Splitting the data in useful subsets which have a size that can be
 * 				sent to the client in a acceptable time.
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * Filters data with respect to city, startDate, endDate
	 * @pre 	filteredData != null && city != null && sdate != null && edate != null && data != null
	 * @param 	city, sdate, edate are the values for which the filter should be applied
	 * @post 	filteredData contains (in addition to the previous filtered data) the asked data (city between startDate and endDate)
	 * 			if the measurements aren't already in the ArrayList
	 * @return 	ArrayList of temperature measurements with all the previous data plus the asked data (without doubled elements)
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
	 * Filters data with respect to year
	 * @pre 	filteredData != null && city != null && data != null
	 * @param 	city is the value for which the filter should be applied
	 * @post 	filteredData contains (in addition to the previous filtered data) the asked data (all measurements of one city)
	 * 			if the measurements aren't already in the ArrayList
	 * @return 	ArrayList of temperature measurements with all the previous data plus the asked data (without doubled elements)
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
	 * Filters data with respect to city
	 * @pre 	filteredData != null && city != null && data != null
	 * @param 	city is the value for which the filter should be applied
	 * @post 	filteredData contains (in addition to the previous filtered data) the asked data (all measurements of one city)
	 * 			if the measurements aren't already in the ArrayList
	 * @return 	ArrayList of temperature measurements with all the previous data plus the asked data (without doubled elements)
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
	 * Filters data with respect to country, startDate, endDate
	 * @pre 	filteredData != null && country != null && sdate != null && edate != null && data != null
	 * @param 	country, sdate, edate are the values for which the filter should be applied
	 * @post 	filteredData contains (in addition to the previous filtered data) the asked data (country between startDate and endDate)
	 * 			if the measurements aren't already in the ArrayList
	 * @return 	ArrayList of temperature measurements with all the previous data plus the asked data (without doubled elements)
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
	 * Filters data with respect to country
	 * @pre 	filteredData != null && country != null && data != null
	 * @param 	country is the value for which the filter should be applied
	 * @post 	filteredData contains (in addition to the previous filtered data) the asked data (all measurements of one country)
	 * 			if the measurements aren't already in the ArrayList
	 * @return 	ArrayList of temperature measurements with all the previous data plus the asked data (without doubled elements)
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
	
	/**Filters data dependent on city and country between sdate and edate
	 * @pre		filteredData != null && data != null && country != null && city != null && sdate != null && edate != null
	 * @param	defines data for city in country between sdate and edate
	 * @post	filteredData contains data for a specific city in a specific country between startDate and EndDate
	 * @return	ArrayList filteredData containing the above defined measurements
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
	
	/**Filters data dependent on city and country
	 * @pre		filteredData != null && data != null && country != null && city != null
	 * @param	defines data for city in country
	 * @post	filteredData contains data for a specific city in a specific country
	 * @return	ArrayList filteredData containing the above defined measurements
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
	 * Clears the ArrayList filteredData which is used to send the data to the client
	 * @pre 	filteredData != null
	 * @post 	ArrayList is empty
	 * @return 	Empty ArrayList of TemperatureMeasurements
	 */
	public ArrayList<DataPoint> clearMeasurements(){
		this.filteredData.clear();
		return this.filteredData;
	}
	
	/**Gives all the names the cities stored in an ArrayList
	 * @pre		data != null
	 * @post	ArrayList cities contains all cities from the data as Strings
	 * @return	ArrayList with all names of the cities (each just once)
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
	
	/**Gives all the names the countries stored in an ArrayList
	 * @pre		data != null
	 * @post	ArrayList countries contains all countries from the data as Strings
	 * @return	ArrayList with all names of the countries (each just once)
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
	
	/**Filters data for all city at a specific time
	 * @pre 	data != null && filteredData != null && date != null
	 * @param	cities contains all cities for which the data should be extracted
	 * 			date is the date for all the measurements
	 * @post	the asked data is stored in an ArrayList
	 * @return	ArrayList sliderData contains all measurements for all cities at a specific time (and nothing else)
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
	
	
	public ArrayList<DataPoint> temperatureMeasurementsOfAllCitiesAtYear(Integer year){
		
		sliderData.clear();
		ArrayList<DataPoint> temperatureMeasurementsOfAllCitiesAtJan = new ArrayList<DataPoint>();
		ArrayList<DataPoint> temperatureMeasurementsOfAllCitiesAtFeb = new ArrayList<DataPoint>();
		ArrayList<DataPoint> temperatureMeasurementsOfAllCitiesAtMar = new ArrayList<DataPoint>();
		ArrayList<DataPoint> temperatureMeasurementsOfAllCitiesAtApr = new ArrayList<DataPoint>();
		ArrayList<DataPoint> temperatureMeasurementsOfAllCitiesAtMay = new ArrayList<DataPoint>();
		ArrayList<DataPoint> temperatureMeasurementsOfAllCitiesAtJun = new ArrayList<DataPoint>();
		ArrayList<DataPoint> temperatureMeasurementsOfAllCitiesAtJul = new ArrayList<DataPoint>();
		ArrayList<DataPoint> temperatureMeasurementsOfAllCitiesAtAug = new ArrayList<DataPoint>();
		ArrayList<DataPoint> temperatureMeasurementsOfAllCitiesAtSep = new ArrayList<DataPoint>();
		ArrayList<DataPoint> temperatureMeasurementsOfAllCitiesAtOct = new ArrayList<DataPoint>();
		ArrayList<DataPoint> temperatureMeasurementsOfAllCitiesAtNov = new ArrayList<DataPoint>();
		ArrayList<DataPoint> temperatureMeasurementsOfAllCitiesAtDec = new ArrayList<DataPoint>();
		
		final Date DATE_JAN = DateTimeFormat.getFormat("dd/MM/yyyy").parse("01/01/"+year.toString());
		final Date DATE_FEB = DateTimeFormat.getFormat("dd/MM/yyyy").parse("01/02/"+year.toString());
		final Date DATE_MAR = DateTimeFormat.getFormat("dd/MM/yyyy").parse("01/03/"+year.toString());
		final Date DATE_APR = DateTimeFormat.getFormat("dd/MM/yyyy").parse("01/04/"+year.toString());
		final Date DATE_MAY = DateTimeFormat.getFormat("dd/MM/yyyy").parse("01/05/"+year.toString());
		final Date DATE_JUN = DateTimeFormat.getFormat("dd/MM/yyyy").parse("01/06/"+year.toString());
		final Date DATE_JUL = DateTimeFormat.getFormat("dd/MM/yyyy").parse("01/07/"+year.toString());
		final Date DATE_AUG = DateTimeFormat.getFormat("dd/MM/yyyy").parse("01/08/"+year.toString());
		final Date DATE_SEP = DateTimeFormat.getFormat("dd/MM/yyyy").parse("01/09/"+year.toString());
		final Date DATE_OCT = DateTimeFormat.getFormat("dd/MM/yyyy").parse("01/10/"+year.toString());
		final Date DATE_NOV = DateTimeFormat.getFormat("dd/MM/yyyy").parse("01/11/"+year.toString());
		final Date DATE_DEC = DateTimeFormat.getFormat("dd/MM/yyyy").parse("01/12/"+year.toString());
		
		temperatureMeasurementsOfAllCitiesAtJan = temperatureMeasurementsOfAllCitiesAtDate(DATE_JAN);
		temperatureMeasurementsOfAllCitiesAtFeb = temperatureMeasurementsOfAllCitiesAtDate(DATE_FEB);
		temperatureMeasurementsOfAllCitiesAtMar = temperatureMeasurementsOfAllCitiesAtDate(DATE_MAR);
		temperatureMeasurementsOfAllCitiesAtApr = temperatureMeasurementsOfAllCitiesAtDate(DATE_APR);
		temperatureMeasurementsOfAllCitiesAtMay = temperatureMeasurementsOfAllCitiesAtDate(DATE_MAY);
		temperatureMeasurementsOfAllCitiesAtJun = temperatureMeasurementsOfAllCitiesAtDate(DATE_JUN);
		temperatureMeasurementsOfAllCitiesAtJul = temperatureMeasurementsOfAllCitiesAtDate(DATE_JUL);
		temperatureMeasurementsOfAllCitiesAtAug = temperatureMeasurementsOfAllCitiesAtDate(DATE_AUG);
		temperatureMeasurementsOfAllCitiesAtSep = temperatureMeasurementsOfAllCitiesAtDate(DATE_SEP);
		temperatureMeasurementsOfAllCitiesAtOct = temperatureMeasurementsOfAllCitiesAtDate(DATE_OCT);
		temperatureMeasurementsOfAllCitiesAtNov = temperatureMeasurementsOfAllCitiesAtDate(DATE_NOV);
		temperatureMeasurementsOfAllCitiesAtDec = temperatureMeasurementsOfAllCitiesAtDate(DATE_DEC);
		
		double average;
		double sum;
		
		for(DataPoint i:temperatureMeasurementsOfAllCitiesAtJan){
			for(DataPoint j:temperatureMeasurementsOfAllCitiesAtFeb){
				for(DataPoint k:temperatureMeasurementsOfAllCitiesAtMar){
					for(DataPoint l:temperatureMeasurementsOfAllCitiesAtApr){
						for(DataPoint m:temperatureMeasurementsOfAllCitiesAtMay){
							for(DataPoint n:temperatureMeasurementsOfAllCitiesAtJun){
								for(DataPoint o:temperatureMeasurementsOfAllCitiesAtJul){
									for(DataPoint p:temperatureMeasurementsOfAllCitiesAtAug){
										for(DataPoint q:temperatureMeasurementsOfAllCitiesAtSep){
											for(DataPoint r:temperatureMeasurementsOfAllCitiesAtOct){
												for(DataPoint s:temperatureMeasurementsOfAllCitiesAtNov){
													for(DataPoint t:temperatureMeasurementsOfAllCitiesAtDec){
														sum = i.getTemperature() + j.getTemperature() + k.getTemperature() +
														l.getTemperature() + m.getTemperature() + n.getTemperature() +
														o.getTemperature() + p.getTemperature() + q.getTemperature() +
														r.getTemperature() + s.getTemperature() + t.getTemperature();
														average = sum/12;
														i.setTemperature(average);
														this.sliderData.add(i);
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		/*
		sliderData.clear();
		for(DataPoint Measurement:this.data){
			if(Measurement.getDate().getTime() >= date.getTime()-DAY_IN_MILLISECONDS && 
					Measurement.getDate().getTime() <= new Date((date.getMonth()+1)+"/"+date.getDate()+"/"+(date.getYear()+1901)).getTime()+DAY_IN_MILLISECONDS){
				this.sliderData.add(Measurement);
			}
		}
		*/
		return this.sliderData;
	}
	
	public ArrayList<DataPoint> temperatureMeasurementsCitiesAtDate(int year, int month){
		sliderData.clear();
		for(DataPoint Measurement:this.data){
			if(Measurement.getYear()+ 1900 == year && 
					Measurement.getDate().getMonth()+1 == month){
				this.sliderData.add(Measurement);
			}
		}
		return this.sliderData;
	}
	
	
	
	/**Removes all measurements of one specific city
	 * @pre		data != null && filteredData != null && city != null
	 * @param	city for which the measurements should be removed
	 * @post	filteredData does not contain measurements of the city anymore
	 * @return	ArrayList filteredData not containing measurements of the city anymore
	 */
	public ArrayList<DataPoint> removeCity(String city){
		for(DataPoint Measurement:this.data){
			if(Measurement.getCity().equals(city)){
				this.filteredData.remove(Measurement);
			}
		}
		return this.filteredData;
	}
	
	/**Removes all measurements of one specific county
	 * @pre		data != null && filteredData != null && country != null
	 * @param	country for which the measurements should be removed
	 * @post	filteredData does not contain measurements of the country anymore
	 * @return	ArrayList filteredData not containing measurements of the country anymore
	 */
	public ArrayList<DataPoint> removeCountry(String country){
		for(DataPoint Measurement:this.data){
			if(Measurement.getCountry().equals(country)){
				this.filteredData.remove(Measurement);
			}
		}
		return this.filteredData;
	}
	
	public ArrayList<DataPoint> removeYears(int syear, int eyear){
		for(DataPoint Measurement:this.data){
			if(	Measurement.getYear()+1900>=syear && Measurement.getYear()+1900<=eyear){
						this.filteredData.remove(Measurement);
				}
		}
		return this.filteredData;
	}
}