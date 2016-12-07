package com.gwt.climatechange.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.gwt.climatechange.shared.DataPoint;

/**
 * This class handles the parsing of the CSV file.
 * @author		Pascal Siemon
 * @history 	2016-08-11 JL First version
 * @version 	2016-08-11 JL 0.1.0
 * @responsibilities
 * 				Read the CSV file and create objects for every measurement which have values
 * 				that can be properly used afterwards.
 */

public class CSVParser{
	
	private ArrayList<DataPoint> Data = new ArrayList<DataPoint>();
	
	/**
	 * Reads the csv file and creates all necessary objects which are afterwards stored in the ArrayList Data
	 * @pre		csvFileName != null && Data != null
	 * @param 	csvFileName is the name of the file that should be read
	 * @post	data from the csv file is stored in the returned ArrayList
	 * @return	ArrayList that contains all the measurements from the csv file
	 * @throws	IOException If an IO error occurs
	 * @throws	ParseException If a parse error occurs (for the parsed Date and float types)
	 */
	public ArrayList<DataPoint> parseCSV(String csvFileName) throws IOException, ParseException {
		
		//initialization
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		
		try {
			br = new BufferedReader(new FileReader(csvFileName));
			while ((line = br.readLine()) != null) {
				
                //use comma as separator
				String[] Measurement = line.split(cvsSplitBy);
                
				//creates temperatureMeasurement objects beginning at the second line of the file
				if(Measurement[0].equals("dt") != true){
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			    	Date date =  df.parse(Measurement[0]);
					double temperature = Double.parseDouble(Measurement[1]);
					double uncertainty = Double.parseDouble(Measurement[2]);
					String city = Measurement[3];
					String country = Measurement[4];
					
					//unifies coordinates (south = -north and west = -east)
					double latitude = Double.parseDouble(Measurement[5].substring(0, Measurement[5].length()-1));
					double longitude = Double.parseDouble(Measurement[6].substring(0, Measurement[6].length()-1));
					if(Measurement[5].charAt(Measurement[5].length()-1) == 'S') latitude = -latitude;
					if(Measurement[6].charAt(Measurement[6].length()-1) == 'W') longitude = -longitude;
				
					
					//creates measurements and adds them to the ArrayList which will be returned at the end
					this.Data.add(new DataPoint(temperature, uncertainty, date, city, country, latitude, longitude)); 
				}
			}
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} catch (ParseException e) {
			throw e;
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					throw e;
				}
			}
		}
		return Data;
	}
}
