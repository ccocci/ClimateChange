package com.gwt.climatechange.client;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.FlexTable;

import com.gwt.climatechange.shared.DataPoint;

public class MeasurementTable {
    FlexTable dataFlexTable = new FlexTable();
    
    public void setUpMeasurementTable(){
    	 // Create table for measurement data.
 		dataFlexTable.setText(0, 0, "Date");
 		dataFlexTable.setText(0, 1, "Average Temperature");
 		dataFlexTable.setText(0, 2, "Average Temperature Uncertainty");
 		dataFlexTable.setText(0, 3, "City");
 		dataFlexTable.setText(0, 4, "Country");
 		//dataFlexTable.setText(0, 5, "Latitude");
 		//dataFlexTable.setText(0, 6, "Longitude");
 		
 		// Add styles to elements in the measurement table.
 		dataFlexTable.getRowFormatter().addStyleName(0, "tableHeader");
		dataFlexTable.addStyleName("table");
 		for (int i=0; i<7; i++) {
 			dataFlexTable.getCellFormatter().addStyleName(0, i, "tableNumericColumn");
 		}
    }
    
    public void clearMeasurementTable(){
	      int measurementRowCount = dataFlexTable.getRowCount()-1;
	 	  for(int i = 1; i < measurementRowCount; ){
	 		 dataFlexTable.removeRow(i);
	      }
    }
    
    public FlexTable getMeasurementTable(){
    	return this.dataFlexTable;
    }
    
    public void fillTable(DataPoint dataPoint){
		final int measurementNumberOfColumns = 7;
		int row = dataFlexTable.getRowCount();
		
		Float avgTemperature = new Float(Math.round(dataPoint.getTemperature()));
		Float uncertainty = new Float(Math.round(dataPoint.getUncertainty()));
		//Float latitude = new Float(Math.round(dataPoint.getLatitude()));
		//Float longitude = new Float(Math.round(dataPoint.getLongitude()));
		
		dataFlexTable.setText(row, 0, DateTimeFormat.getFormat("dd/MM/yyyy").format(dataPoint.getDate()));
		dataFlexTable.setText(row, 1, avgTemperature.toString());
		dataFlexTable.setText(row, 2, uncertainty.toString());
		dataFlexTable.setText(row, 3, dataPoint.getCity());
		dataFlexTable.setText(row, 4, dataPoint.getCountry());
		//dataFlexTable.setText(row, 5, latitude.toString());
		//dataFlexTable.setText(row, 6, longitude.toString());
		
		for(int i = 0; i < measurementNumberOfColumns; i++){
			dataFlexTable.getCellFormatter().addStyleName(row, i, "filterTableColumn");
		}
    }
}
