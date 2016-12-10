package com.gwt.climatechange.client;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.googlecode.gwt.charts.client.geochart.GeoChart;
import com.gwt.climatechange.client.slider.Slider;
import com.gwt.climatechange.shared.DataPoint;

import com.gwt.climatechange.client.slider.SliderEvent;
import com.gwt.climatechange.client.slider.SliderListener;

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


public class WorldMapMenu extends DockLayoutPanel implements SliderListener {
	// create panels
	

	
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

	private VerticalPanel sliderPanel= new VerticalPanel();
	private HorizontalPanel horizontalSliderPanel= new HorizontalPanel();
	private Label yearLabel= new Label("Year");
	private Slider slider= new Slider("Slider",1880,2013,2013);
	private Label sliderValue= new Label(INITIAL_YEAR.toString());

	
	
	
	public WorldMapMenu(){
		super(Style.Unit.PX);
		initialize();
		
		
		//handleEvents();
		}

		/**
		 * 
		 * Initializes the map, runs and draws it.
		 * @pre		geoChart is instantiated, mainPanel is instantiated
		 * @post	map is loaded and drawn
		 * 
		 */
			
		private void initialize() {
			slider.setWidth("200px");
			slider.setStyleName("slider");
			yearLabel.setStyleName("yearLabel");
			horizontalSliderPanel.add(yearLabel);
			horizontalSliderPanel.add(sliderValue);
			sliderPanel.add(horizontalSliderPanel);
			sliderPanel.add(slider);
			sliderPanel.setSize("100%", "100%");
			slider.addListener(this);
			addSouth(sliderPanel,50);
			
			ChartLoader chartLoader = new ChartLoader(ChartPackage.GEOCHART);
			chartLoader.loadApi(new Runnable() {

				@Override
				public void run() {
					// Create and attach the chart
					geoChart = new GeoChart();
					add(geoChart);
					//updateGeoChart(INITIAL_DATE);
					draw();
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

			dataService.temperatureMeasurementsOfAllCitiesAtDate(date, callback);

		}

		private native JsArrayString getNativeArray() /*-{
			return [ "0000FF", "5858FA", "A9A9F5", "F7819F", "FE2E64", "FF0040" ];
		}-*/;
		
	
		//returns ArrayList of average temperatures of each and all cities
			public ArrayList<DataPoint> generateCityAverageList(ArrayList<DataPoint> temperaturesAtMonths) {
				// TODO Auto-generated method stub
				ArrayList<DataPoint> averageTemperatures = new ArrayList<DataPoint>();
				double average;
				double sum;
				int i=0;
				
				while(i < temperaturesAtMonths.size()){
					int numberOfTemperatures=1;
					sum=temperaturesAtMonths.get(i).getTemperature();
					average=sum/numberOfTemperatures;
					
					while(temperaturesAtMonths.get(i+1)!=null && temperaturesAtMonths.get(i).getCity()==temperaturesAtMonths.get(i+1).getCity()){
						numberOfTemperatures++;
						sum+=temperaturesAtMonths.get(i+1).getTemperature();
						average=sum/numberOfTemperatures;
						i++;
					}
					temperaturesAtMonths.get(i).setTemperature(average);
					averageTemperatures.add(temperaturesAtMonths.get(i));
					i++;
				}
				
				
				return averageTemperatures;
				
			}

	private void fillPanels(){
		//styleSlider();
		//Add everything in slidePanel
		slider.setWidth("200px");
		slider.setStyleName("slider");
		yearLabel.setStyleName("yearLabel");
		sliderPanel.add(yearLabel);
		sliderPanel.add(slider);
		
	}
	
	@Override
    public boolean onSlide(SliderEvent e) {
    	// update slider value
    	String year = "" + e.getValues()[0];
        sliderValue.setText(year);
        return true;
    }

    @Override
    public void onStart(SliderEvent e) {
    }

    @Override
    public void onStop(SliderEvent e) {
    	//update geochart
    	String year = "" + e.getValues()[0];
    	Date date = DateTimeFormat.getFormat("dd/MM/yyyy").parse("01/01/" + year);
    	updateGeoChart(date);
    }

    @Override
    public void onChange(SliderEvent e) {
    	// Not used so far
    }

	

}