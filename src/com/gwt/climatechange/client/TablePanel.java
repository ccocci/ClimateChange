package com.gwt.climatechange.client;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import com.gwt.climatechange.shared.DataPoint;

/**
 * This class manages the user interface for the table view.
 * @author		Lina Witzel
 * @history 	2016-25-11 JL First version
 * @version 	2016-25-11 JL 0.1.0
 * @responsibilities
 * 				Keeps track of all panels, widgets and of the functionality of the table view.
 */
public class TablePanel extends VerticalPanel{
	private FilterTable filterTable = new FilterTable();
	private MeasurementTable measurementTable = new MeasurementTable();
	private VerticalPanel addPanel = new VerticalPanel();
	private VerticalPanel filterPanel= new VerticalPanel();
	private HorizontalPanel startYearPanel = new HorizontalPanel();
	private HorizontalPanel endYearPanel = new HorizontalPanel();
	private DisclosurePanel discPanel= new DisclosurePanel("Filter data");
	private SuggestBox newSuggestBoxCity = new SuggestBox();
	private SuggestBox newSuggestBoxCountry = new SuggestBox();
	private IntegerBox integerBoxStartYear = new IntegerBox();
	private IntegerBox integerBoxEndYear = new IntegerBox();
	private ListBox startMonth = new ListBox();
	private ListBox endMonth = new ListBox();
	private Button addFilterButton = new Button("Add");
	private DataServiceAsync dataSvc = GWT.create(DataService.class);
	MultiWordSuggestOracle cityNames = new MultiWordSuggestOracle();
	MultiWordSuggestOracle countryNames = new MultiWordSuggestOracle();
	
	private final String[] MONTHS = {"January","February","March","April","May","June",
	                           "July","August","September","October","November","December"};

	
	public TablePanel() {
		initialize();
		//filterTable.addFilterToTable("", "", new Date(113, 0, 1), new Date(113, 11, 1));
		//refreshMeasurementTable("", "", new Date(113, 0, 1), new Date(113, 11, 1));
	}
	

	
	public void initialize() {
		filterTable.setUpFilterTable();
		measurementTable.setUpMeasurementTable();
		
		//Clear previous filtered data
		if(dataSvc == null){
			dataSvc = GWT.create(DataService.class);
		}
		AsyncCallback<ArrayList<DataPoint>> clearCallback = new AsyncCallback<ArrayList<DataPoint>>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(ArrayList<DataPoint> result) {
				// TODO Auto-generated method stub
				updateMeasurementTable(result);
			}
		};
		dataSvc.clearMeasurements(clearCallback);
		
		
		// Add city names to the suggestBox
		if(dataSvc == null)
		{
			dataSvc = GWT.create(DataService.class);
		}
		
		AsyncCallback<ArrayList<String>> callback = new AsyncCallback<ArrayList<String>>(){
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onSuccess(ArrayList<String> result) {
				addCityNames(result);
			}	
		};
		dataSvc.getCities(callback);
		newSuggestBoxCity = new SuggestBox(cityNames);
		
		AsyncCallback<ArrayList<String>> callbackCountry = new AsyncCallback<ArrayList<String>>(){
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub	
			}

			@Override
			public void onSuccess(ArrayList<String> result) {
				addCountryNames(result);
			}
			
		};
		dataSvc.getCountries(callbackCountry);
		newSuggestBoxCountry = new SuggestBox(countryNames);
		
		// Add months to Month selection dropDown menu
		startMonth.setVisibleItemCount(1);
		endMonth.setVisibleItemCount(1);
		for(int i = 0; i<MONTHS.length; i++){
			startMonth.addItem(MONTHS[i]);
			endMonth.addItem(MONTHS[i]);
		}
		
		// set style
		newSuggestBoxCountry.setStyleName("box");
		newSuggestBoxCity.setStyleName("box");
		integerBoxEndYear.setStyleName("box");
		integerBoxStartYear.setStyleName("box");
		addFilterButton.setStyleName("addButton");
		
		addPanel.add(newSuggestBoxCountry);
	    addPanel.add(newSuggestBoxCity);
	    startYearPanel.add(integerBoxStartYear);
	    startYearPanel.add(startMonth);
	    addPanel.add(startYearPanel);
	    endYearPanel.add(integerBoxEndYear);
	    endYearPanel.add(endMonth);
	    addPanel.add(endYearPanel);
	    addPanel.add(addFilterButton);
	    addPanel.addStyleName("addPanel");
		
	    /*
		// Assemble Add filter panel.
		addPanel.add(newSuggestBoxCountry);
	    addPanel.add(newSuggestBoxCity);
	    addPanel.add(integerBoxStartYear);
	    addPanel.add(startMonth);
	    addPanel.add(integerBoxEndYear);
	    addPanel.add(endMonth);
	    addPanel.add(addFilterButton);
	    addPanel.addStyleName("addPanel");
	    */
		
	    //Assemble filter and disclosure Panel.
	    filterPanel.add(filterTable.getFilterTable());
	    filterPanel.add(addPanel);
	    discPanel.setContent(filterPanel);
	    
	    // Assemble Main panel.
	    add(discPanel);
		add(measurementTable.getMeasurementTable());
		
		
	    // Move cursor focus to the city filter box.
	    newSuggestBoxCountry.setFocus(true);

	    // Listen for keyboard events on cityBox and countryBox and accept only letters
	   	newSuggestBoxCity.addKeyPressHandler(new KeyPressHandler() {
	   		@Override
	   		public void onKeyPress(KeyPressEvent event) {
	   			if (Character.isDigit(event.getCharCode())){
	   				event.getNativeEvent().preventDefault();
	   			}
	   		}
	    });
	   	newSuggestBoxCountry.addKeyPressHandler(new KeyPressHandler(){
	   		@Override
	   		public void onKeyPress(KeyPressEvent event){
		   		if(Character.isDigit(event.getCharCode())){
		   			event.getNativeEvent().preventDefault();
		   		}
	   		}
	   	});
	       
	    
	   // Listen for mouse events on the Add button.
	    addFilterButton.addClickHandler(new ClickHandler() {
	    	public void onClick(ClickEvent event) {
	    		addFilter();
	    	}
	    });
	    
	    
	    // Listen for keyboard events on startYear and endYear boxes, and accept only numbers/backspace
	   	integerBoxStartYear.addKeyPressHandler(new KeyPressHandler() {
	   		@Override
	   		public void onKeyPress(KeyPressEvent event) {
	   			if (!Character.isDigit(event.getCharCode()) && 
	   				event.getNativeEvent().getKeyCode() != KeyCodes.KEY_TAB && 
	   				event.getNativeEvent().getKeyCode() != KeyCodes.KEY_BACKSPACE){
	   					((IntegerBox) event.getSource()).cancelKey();
	   			}
			}
	    });
	   	integerBoxEndYear.addKeyPressHandler(new KeyPressHandler() {
	   		@Override
			public void onKeyPress(KeyPressEvent event) {
				if (!Character.isDigit(event.getCharCode()) && 
					event.getNativeEvent().getKeyCode() != KeyCodes.KEY_TAB && 
					event.getNativeEvent().getKeyCode() != KeyCodes.KEY_BACKSPACE) {
						((IntegerBox) event.getSource()).cancelKey();
				}
			}
	    });
	   	
	   	// Add placeHolders to the text boxes	
	   	newSuggestBoxCountry.getElement().setAttribute("placeHolder", "Enter Country");
	    newSuggestBoxCity.getElement().setAttribute("placeHolder","Enter City");
	    integerBoxStartYear.getElement().setAttribute("placeHolder", "Enter StartYear");
	    integerBoxEndYear.getElement().setAttribute("placeholder", "Enter EndYear");
	   	
	    // Listen for keyboard events in the suggest box for cities.
	    integerBoxEndYear.addKeyDownHandler(new KeyDownHandler() {
	    	public void onKeyDown(KeyDownEvent event) {
	    		if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
	    			addFilter();
	    		}
	    	}
	    });	   
	    integerBoxStartYear.addKeyDownHandler(new KeyDownHandler() {
	    	public void onKeyDown(KeyDownEvent event) {
	    		if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
	    			addFilter();
		        }
	    	}
		});
	    
	    addInitialFilter();
	}
	
	/**
	   * Add filter to FlexTable. Executed when the user clicks the addFilterButton or
	   * presses enter in one of the suggestBoxes.
	   */
	private void addFilter() {
		//Get values from boxes and do capitalization for Strings
		final String country = newSuggestBoxCountry.getText().trim().substring(0, 1).toUpperCase() + newSuggestBoxCountry.getText().trim().substring(1);
		final String city = newSuggestBoxCity.getText().trim().substring(0, 1).toUpperCase() + newSuggestBoxCity.getText().trim().substring(1);
	    final Integer syear = integerBoxStartYear.getValue();
	    final Integer eyear = integerBoxEndYear.getValue();
	    final Date sdate;
	    final Date edate;
		
		// Determine Start Date
	    if(syear != null){
			String sD = startMonth.getSelectedIndex()+1 + "/1/" + syear;
			sdate = new Date(sD);
	    }else{
	    	sdate = null;
	    }
		// Determine End Date
	    if(eyear != null){
			String eD = endMonth.getSelectedIndex()+1 + "/1/" + eyear;
			edate = new Date(eD);
	    }else{
	    	edate = null;
	    }
	    
		newSuggestBoxCountry.setFocus(true);

		// Don't add the filter if it's already in the table.
		for(String s : filterTable.getCurrentCities()){
			if(!s.equals("")){
				if (s.toUpperCase().equals(city.toUpperCase())){
					Window.alert("This city is already selected.");
					return; 
				}
			}
		}
		for(String s : filterTable.getCurrentCountries()){
			if(!s.equals("")){
				if (s.toUpperCase().equals(country.toUpperCase())){
					Window.alert("This country is already selected.");
					return; 
				}
			}
		}
	    
		// Test whether filter inputs are incorrect
		if(sdate != null && edate != null){
			if (eyear.intValue() < syear.intValue() || 
				(eyear.equals(syear) && startMonth.getSelectedIndex() > endMonth.getSelectedIndex())){
					Window.alert("Start date needs to be before end date");
					return;
			}
		}
		
		if(sdate != null && edate != null || sdate == null && edate == null){
				filterTable.addFilterToTable(country, city, sdate, edate);
				newSuggestBoxCountry.setText(null);
				newSuggestBoxCity.setText(null);
				integerBoxStartYear.setValue(null);
				integerBoxEndYear.setValue(null);
				startMonth.setSelectedIndex(0);
				endMonth.setSelectedIndex(0);
		}
 
		// Add a button to remove this filter from the table.
		filterTable.getCurrentRow(city).getRemoveButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if(city != ""){
					removeData(filterTable.getCurrentRow(city).getCity());
				}
			}
		});
		filterTable.getCurrentRowCountry(country).getRemoveButton().addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				if(country != "" && city == ""){
					removeDataCountry(filterTable.getCurrentRowCountry(country).getCountry());
				}
			}
		});
	      
		filterTable.getCurrentRow(city).getRemoveButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				removeYearsFromMeasurementTable(syear,eyear);
				filterTable.removeYearFilterFromTable(syear,eyear);
				measurementTable.clearMeasurementTable();
			}
		});
	  
	    		addData(city, sdate, edate);
	    		        
	}
	
	private void addInitialFilter() {
		//Get values from boxes and do capitalization for Strings
		final String country = "";
		final String city = "";
	    final Integer syear = 2013;
	    final Integer eyear = 2013;
	    final Date sdate;
	    final Date edate;
		
		// Determine Start Date
			String sD ="1/1/" + syear;
			sdate = new Date(sD);
	    
		// Determine End Date
			String eD = "12/1/" + eyear;
			edate = new Date(eD);
		
		if(sdate != null && edate != null || sdate == null && edate == null){
				filterTable.addFilterToTable(country, city, sdate, edate);
		}
 
		// Add a button to remove this filter from the table.
		filterTable.getCurrentRow(city).getRemoveButton().addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				removeYearsFromMeasurementTable(2013,2013);
				filterTable.removeFilterFromTable(city);
				measurementTable.clearMeasurementTable();
			}
		});
		
	/*	filterTable.getCurrentRowCountry(country).getRemoveButton().addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event){
				//removeCountryFromMeasurementTable(country);
				filterTable.removeFilterFromTable(city);
				measurementTable.clearMeasurementTable();
			}
		});
	      */
	  
	    		addData(city, sdate, edate);
	    		        
	}
	
	/**
	 * Refreshed the flex Table containing the measurements
	 * @pre -
	 * @post -
	 * @param -
	 * @return -
	 */
	protected void refreshMeasurementTable(String country, String city, Date sdate, Date edate) {
		if (dataSvc == null) {
	    	dataSvc = GWT.create(DataService.class);
	    }
		AsyncCallback<ArrayList<DataPoint>> callback = new AsyncCallback<ArrayList<DataPoint>>() {
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub	
			}

			@Override
			public void onSuccess(ArrayList<DataPoint> result) {
				updateMeasurementTable(result);	
			}
		};
		if(sdate != null && edate != null){
			if(country == "" && city != ""){
				dataSvc.temperatureMeasurements(city, sdate, edate, callback);
			}
			if(country != "" && city == ""){
				dataSvc.temperatureMeasurementsCountry(country, sdate, edate, callback);
			}
			if(country != "" && city != ""){
				dataSvc.temperatureMeasurementsCityCountry(country, city, sdate, edate, callback);
			}
			if(country == "" && city == ""){
				dataSvc.temperatureMeasurementsYears(sdate.getYear()+1900, edate.getYear()+1900, callback);
			}
		}
		if(sdate == null && edate == null){
			if(country == "" && city != ""){
				dataSvc.temperatureMeasurements(city, callback);
			}
			if(country != "" && city == ""){
				dataSvc.temperatureMeasurementsCountry(country, callback);
			}
			if(country != "" && city != ""){
				dataSvc.temperatureMeasurementsCityCountry(country, city, callback);
			}
		}
	}
	
	
	protected void updateMeasurementTable(ArrayList<DataPoint> temperatureMeasurements) {
		for (DataPoint temperatureMeasurement : temperatureMeasurements) {
			updateMeasurementTable(temperatureMeasurement);
		}
	}

	private void updateMeasurementTable(DataPoint temperatureMeasurement) {
		measurementTable.fillTable(temperatureMeasurement);
	}
	
	protected void removeFromMeasurementTable(String city){
		if (dataSvc == null) {
			dataSvc = GWT.create(DataService.class);
	    }
		AsyncCallback<ArrayList<DataPoint>> callback = new AsyncCallback<ArrayList<DataPoint>>() {
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onSuccess(ArrayList<DataPoint> result) {
				updateMeasurementTable(result);
			}
		};
		dataSvc.removeCity(city, callback);
	}
	
	protected void removeCountryFromMeasurementTable(String country){
		if(dataSvc == null){
			dataSvc = GWT.create(DataService.class);
		}
		AsyncCallback<ArrayList<DataPoint>> callback = new AsyncCallback<ArrayList<DataPoint>>() {
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub	
			}
			
			@Override
			public void onSuccess(ArrayList<DataPoint> result) {
				updateMeasurementTable(result);
			}
		};
		dataSvc.removeCountry(country, callback);
	}

	protected void removeYearsFromMeasurementTable(int syear, int eyear){
		if(dataSvc == null){
			dataSvc = GWT.create(DataService.class);
		}
		AsyncCallback<ArrayList<DataPoint>> callback = new AsyncCallback<ArrayList<DataPoint>>() {
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub	
			}
			
			@Override
			public void onSuccess(ArrayList<DataPoint> result) {
				updateMeasurementTable(result);
			}
		};
		dataSvc.removeYears(syear,eyear, callback);
	}
	/**
	 * Adds all city names in the given ArrayList to the suggestion box
	 * @pre -
	 * @post -
	 * @param ArrayList<String> names: the list of names to add
	 * @return -
	 */
	protected void addCityNames(ArrayList<String> names){
		cityNames.addAll(names);
	}	
	
	protected void addCountryNames(ArrayList<String> names){
		countryNames.addAll(names);
	}
	
	public void addData(String city, Date sdate, Date edate){
		FilterRow currentRow = filterTable.getCurrentRow(city);
		refreshMeasurementTable(currentRow.getCountry(),currentRow.getCity(),currentRow.getStartDate(),currentRow.getEndDate());
		//measurementTable.clearMeasurementTable();
	}
	
	public void removeData(String city){
		removeFromMeasurementTable(city);
		filterTable.removeFilterFromTable(city);
		measurementTable.clearMeasurementTable();
	}
	
	public void removeDataCountry(String country){
		removeCountryFromMeasurementTable(country);
		filterTable.removeCountryFilterFromTable(country);
		measurementTable.clearMeasurementTable();
	}
	
	public void removeDataYears(int syear, int eyear){
		removeYearsFromMeasurementTable(syear,eyear);
		filterTable.removeYearFilterFromTable(syear,eyear);
		measurementTable.clearMeasurementTable();
	}
}
