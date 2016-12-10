package com.gwt.climatechange.client;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.dom.client.Style;
import com.google.gwt.i18n.client.DateTimeFormat;
//import com.googlecode.gwt.charts.client.*;
import com.googlecode.gwt.charts.client.ChartLoader;
import com.googlecode.gwt.charts.client.ChartPackage;
import com.googlecode.gwt.charts.client.ColumnType;
import com.googlecode.gwt.charts.client.DataTable;
import com.googlecode.gwt.charts.client.geochart.GeoChart;
import com.googlecode.gwt.charts.client.options.DisplayMode;

//import com.gwt.climatechangeapp.client.WorldMapView.CityMean;

import com.googlecode.gwt.charts.client.geochart.GeoChartColorAxis;
import com.googlecode.gwt.charts.client.geochart.GeoChartOptions;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DockLayoutPanel;

import com.gwt.climatechange.shared.DataPoint;
	
/**
 * 
 * The class WorldMapView generates the world map and points on the map.
 * 
 * @author Carla Coccia
 * @history 10-11-2016 CC First version
 * 			10-11-2016 LL Initialization 
 * 			11-11-2016 LL Color of markers
 * 			12-11-2016 CC Initialization corrected
 * @version 12-11-2016 CC Version 1
 * @responsabilities This class initializes the world map, draws the map, adds data and draws markers.
 *
 */
	
public class WorldMapView extends DockLayoutPanel{

	private GeoChart geoChart;
	private Integer INITIAL_YEAR=2013;
	private double maxTemperature=40;
	private double minTemperature=-30;
	private double uncertainty=15;
	//all the cities and countries will be shown
	private String city="city";
	private String country="country";
	private DataServiceAsync dataService = GWT.create(DataService.class);
	private ArrayList<DataPoint> cityAtYearData= new ArrayList<DataPoint>();
	private final Date INITIAL_DATE = DateTimeFormat.getFormat("dd/MM/yyyy").parse("01/01/"+INITIAL_YEAR.toString());
	
	// Create the MapView
	public WorldMapView() {
		super(Style.Unit.PX);
		initialize();
	}

	/**
	 * 
	 * Initializes the map, runs and draws it.
	 * @pre		geoChart is instantiated, mainPanel is instantiated
	 * @post	map is loaded and drawn
	 * 
	 */
		
	private void initialize() {
		ChartLoader chartLoader = new ChartLoader(ChartPackage.GEOCHART);
		chartLoader.loadApi(new Runnable() {

			@Override
			public void run() {
				// Create and attach the chart
				geoChart = new GeoChart();
				add(geoChart);
				updateGeoChart(INITIAL_DATE);
			}
		});
	}

	/**
	 * 
	 * Draws the map and markers (currently with test data).
	 * @pre		geoChart is instantiated, mainPanel is instantiated
	 * @post	map is loaded and drawn
	 * 
	 */
		
	private void draw() {
		// Prepare the data 
		// TODO: Function, which gets the right data form the list.
		DataTable dataTable = DataTable.create();
		dataTable.addColumn(ColumnType.STRING, "City");
		dataTable.addColumn(ColumnType.NUMBER, "Temperature");
		dataTable.addColumn(ColumnType.NUMBER,"Uncertainty");
		
	/*	dataTable.addRows(1);
		dataTable.setValue(0, 0, "Buenos Aires");
		dataTable.setValue(0, 0,13);
		dataTable.setValue(0, 0,2);
		*/

		dataTable.addRows(cityAtYearData.size());
		for (int i = 0; i < cityAtYearData.size(); i++) {
			dataTable.setValue(i, 0, cityAtYearData.get(i).getCity());
			dataTable.setValue(i, 1, cityAtYearData.get(i).getTemperature());
			dataTable.setValue(i, 2, cityAtYearData.get(i).getUncertainty());
		}

			
		// set geochart options
		GeoChartOptions options = GeoChartOptions.create();
		options.setDisplayMode(DisplayMode.MARKERS);
		GeoChartColorAxis geoChartColorAxis = GeoChartColorAxis.create();
		geoChartColorAxis.setColors(getNativeArray());
		options.setColorAxis(geoChartColorAxis);
		options.setDatalessRegionColor("gray");
			
			
		geoChart.draw(dataTable, options);
		
	}

/**	private DataTable prepareData() {
		DataTable dataTable = DataTable.create();
		dataTable.addColumn(ColumnType.STRING, "City");
		dataTable.addColumn(ColumnType.NUMBER, "Temperature");
		dataTable.addColumn(ColumnType.NUMBER,"Uncertainty");
		
	/*	dataTable.addRows(1);
		dataTable.setValue(0, 0, "Buenos Aires");
		dataTable.setValue(0, 0,13);
		dataTable.setValue(0, 0,2);
		*/

	/**	dataTable.addRows(cityAtYearData.size());
		for (int i = 0; i < cityAtYearData.size(); i++) {
			dataTable.setValue(i, 0, cityAtYearData.get(i).getCity());
			dataTable.setValue(i, 1, cityAtYearData.get(i).getTemperature());
			dataTable.setValue(i, 2, cityAtYearData.get(i).getUncertainty());
		}
		
		return dataTable;
	}*/
	/**
	 * 
	 * Sets the colors for markers.
	 * @pre		-
	 * @post	-
	 * @return	returns array with colors for markers
	 * 
	 */
	private void updateGeoChart(Date date) {
		if(dataService == null){
			dataService = GWT.create(DataService.class);
		}
		
		AsyncCallback<ArrayList<DataPoint>> callback = new AsyncCallback<ArrayList<DataPoint>>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(ArrayList<DataPoint> result) {
				if(result.isEmpty()) Window.alert("No data recieved from server...");
				cityAtYearData=result;
				draw();
			}
		};

		dataService.temperatureMeasurementsOfAllCitiesAtYear(INITIAL_YEAR, callback);

	}

	private native JsArrayString getNativeArray() /*-{
		return [ "0000FF", "5858FA", "A9A9F5", "F7819F", "FE2E64", "FF0040" ];
	}-*/;
	
/**	private ArrayList<CityMean> generateCityMeans(ArrayList<DataPoint> tempMeasurs) {
		ArrayList<CityMean> cityMeans = new ArrayList<CityMean>();
		NavigableMap<String, List<DataPoint>> tempMeasursByCity = new TreeMap<String, List<DataPoint>>();
		// Sort TemperatureMeasurements by country in tempMeasursByCountry
		for (DataPoint tempMeasur : tempMeasurs) {
			List<DataPoint> cityList = tempMeasursByCity.get(tempMeasur.getCity());
			if (cityList == null) {
				tempMeasursByCity.put(tempMeasur.getCity(),
						cityList = new ArrayList<DataPoint>());
			}
			cityList.add(tempMeasur);
		}

		// Generate CityyMeans
		for (Map.Entry<String, List<DataPoint>> entry : tempMeasursByCity.entrySet()) {
			cityMeans.add(new CityMean(entry.getKey(), entry.getValue()));
		}

		return cityMeans;

	}*/
	
	//returns ArrayList of average temperatures of each and all cities
/*	public ArrayList<DataPoint> generateCityAverageList(ArrayList<DataPoint> temperaturesAtMonths) {
		// TODO Auto-generated method stub
		ArrayList<DataPoint> averageTemperatures = new ArrayList<DataPoint>();
		double average;
		int numberOfTemperatures=1;
		double sum;
		int i=0;
		
		while(i < temperaturesAtMonths.size()){
			DataPoint currentDataPoint=temperaturesAtMonths.get(i);
			DataPoint nextDataPoint=temperaturesAtMonths.get(i+1);
			sum=currentDataPoint.getTemperature();
			average=sum/numberOfTemperatures;
			
			while(nextDataPoint!=null && currentDataPoint.getCity()==nextDataPoint.getCity()){
				numberOfTemperatures++;
				sum+=nextDataPoint.getTemperature();
				average=sum/numberOfTemperatures;
				i++;
			}
			currentDataPoint.setAverageTemperature(average);
			averageTemperatures.add(currentDataPoint);
			i++;
		}
		
		
		return averageTemperatures;
		
	}*/
	
/**	class CityMean {
		private String city;
		private Double tempMean;
		private Double uncertaintyMean;

		public CityMean(String name, List<DataPoint> tempMeasur) {
			this.city = name;
			setMeans(tempMeasur);
		}

		/**
		 * @return the city
		 */
/**		public String getCity() {
			return city;
		}

		/**
		 * @return the tempMean
		 */
/**		public Double getTemperatureMean() {
			return tempMean;
		}

		/**
		 * @return the uncertaintyMean
		 */
/**		public Double getUncertaintyMean() {
			return uncertaintyMean;
		}

		/**
		 * Calculates and sets the means of the Temperatures and the
		 * Uncertainties of the list of TemperatureMeasurements. (Scientificly
		 * incorrect method.)
		 * 
		 * @pre tempMeasur != null && tempMeasur.size() != 0
		 * @post -
		 * @param tempMeasurs
		 *            ArrayList of TemperatureMeasurements
		 * @return -
		 */
/**		private void setMeans(List<DataPoint> tempMeasurs) {
			ArrayList<Double> temps = new ArrayList<Double>();
			ArrayList<Double> uncerts = new ArrayList<Double>();

			for (DataPoint tm : tempMeasurs) {
				temps.add(tm.getTemperature());
				uncerts.add(tm.getUncertainty());
			}
			double sum1=0;
			for(int i=0;i<temps.size();i++){sum1+=temps.get(i);}
			tempMean=sum1/(temps.size());
			
			double sum2=0;
			for(int j=0;j<temps.size();j++){sum2+=temps.get(j);}
			tempMean=sum2/(temps.size());;

		}

	}*/
	
}