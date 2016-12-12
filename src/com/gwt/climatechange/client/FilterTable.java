package com.gwt.climatechange.client;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.FlexTable;

/**
* 
* The class FilterTable manages the table with the chosen filter(s).
* 
* @author Lucien Lotmar
* @history	09-12-2016 LL SetUp, Filling, Removing Table
* 			10-12-2016 SC Corrections
* @version 	10-12-2016 SC Version 1
* @responsabilities This class creates a new Row for the FilterTable.
*
*/

public class FilterTable {
	private FlexTable filterFlexTable = new FlexTable();
	private ArrayList<FilterRow> filterRows = new ArrayList<FilterRow>();
	
	/**
	 * Method that sets up the table containing the filters as well as the header, and applying the styles.
	 * 
	 * @pre 	filterFlexTable != null
	 * @post 	filterTable is visible
	 */
	
	public void setUpFilterTable(){
		
		// Create table for filters.
		filterFlexTable.setText(0, 0, "Country");
		filterFlexTable.setText(0, 1, "City");
		filterFlexTable.setText(0, 2, "Start Date");
		filterFlexTable.setText(0, 3, "End Date");
		filterFlexTable.setText(0, 4, "Delete");
		
		// Add styles to elements in the filter table.
		filterFlexTable.setCellPadding(6);
		
		// Add styles to elements in the filter list table.
		filterFlexTable.getRowFormatter().addStyleName(0, "tableHeader");
		filterFlexTable.addStyleName("table");
		for (int i=0; i<5; i++) {
			filterFlexTable.getCellFormatter().addStyleName(0, i, "tableNumericColumn");
		}
	}

	/**
	 * Getter for the filterTable
	 */
	
	public FlexTable getFilterTable(){
		return this.filterFlexTable;
	}
	
	/**
	 * Method that adds the applied filter to the table.
	 * 
	 * @pre 	filterFlexTable != null && country != null && city != null && sdate != null && edate!= null
	 * @param	a new FilterRow with applied filter data will be added
	 * @post 	filterTable is filled with applied filter data
	 */
	
	public void addFilterToTable(String country, String city, Date sdate, Date edate){
	      int row = filterFlexTable.getRowCount();
	      
	      FilterRow currentFilterRow = new FilterRow(country, city, sdate, edate);
	      
	      filterRows.add(currentFilterRow);
	      
	      if(country != ""){
	    	  filterFlexTable.setText(row, 0, country);
	      }else{
	    	  filterFlexTable.setText(row, 0, "All");
	      }
	      if(city != ""){
		      filterFlexTable.setText(row, 1, city); 
	      }else{
	    	  filterFlexTable.setText(row, 1, "All");
	      }
	      if(sdate != null && edate != null){
	    	  filterFlexTable.setText(row, 2, DateTimeFormat.getFormat("dd/MM/yyyy").format(sdate));
	    	  filterFlexTable.setText(row, 3, DateTimeFormat.getFormat("dd/MM/yyyy").format(edate));
	      }
	      if(sdate == null && edate == null){
	    	  filterFlexTable.setText(row, 2, "All");
	    	  filterFlexTable.setText(row, 3, "All");
	      }
	      filterFlexTable.setWidget(row, 4, currentFilterRow.getRemoveButton());	     
	     

	      filterFlexTable.getCellFormatter().addStyleName(row, 0, "filterTableColumn");
	      filterFlexTable.getCellFormatter().addStyleName(row, 1, "filterTableColumn");
	      filterFlexTable.getCellFormatter().addStyleName(row, 2, "filterTableColumn");
	      filterFlexTable.getCellFormatter().addStyleName(row, 3, "filterTableColumn");
	      currentFilterRow.getRemoveButton().addStyleDependentName("remove");
	}
	
	/**
	 * Methods to remove filters from table.
	 */
	
	public void removeFilterFromTable(String city){
		for(FilterRow filterRow : filterRows){
			if(filterRow.getCity().equals(city)){
				filterFlexTable.removeRow(filterRows.indexOf(filterRow)+1);
				filterRows.remove(filterRow);
			}
		}
	}
	
	public void removeCountryFilterFromTable(String country){
		for(FilterRow filterRow : filterRows){
			if(filterRow.getCountry().equals(country)){
				filterFlexTable.removeRow(filterRows.indexOf(filterRow)+1);
				filterRows.remove(filterRow);
			}
		}
	}
	
	public void removeYearFilterFromTable(int syear, int eyear){
		for(FilterRow filterRow : filterRows){
			if(filterRow.getStartDate().getYear()+1900>=syear && filterRow.getEndDate().getYear()<=eyear+1900){
				filterFlexTable.removeRow(filterRows.indexOf(filterRow)+1);
				filterRows.remove(filterRow);
			}
		}
	}
	
	/**
	 * Getter for filterRows and cities/countries
	 */
	
	public FilterRow getCurrentRow(String city){
		FilterRow currentFilterRow = null;
		for(FilterRow filterRow:filterRows){
			if(filterRow.getCity().equals(city)){
				currentFilterRow = filterRow;
			}
		}
		return currentFilterRow;
	}
	
	public FilterRow getCurrentRowCountry(String country){
		FilterRow currentFilterRow = null;
		for(FilterRow filterRow:filterRows){
			if(filterRow.getCountry().equals(country)){
				currentFilterRow = filterRow;
			}
		}
		return currentFilterRow;
	}
	
	public ArrayList<String> getCurrentCities(){
		ArrayList<String> cities = new ArrayList<String>();
		for(FilterRow filterRow : filterRows){
			cities.add(filterRow.getCity());
		}
		return cities;
	}
	
	public ArrayList<String> getCurrentCountries(){
		ArrayList<String> countries = new ArrayList<String>();
		for(FilterRow filterRow : filterRows){
			if(filterRow.getCity() == ""){
				countries.add(filterRow.getCountry());
			}
		}
		return countries;
	}
}
