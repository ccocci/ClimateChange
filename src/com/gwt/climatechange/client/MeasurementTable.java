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
 		
 		// Add styles to elements in the measurement table.
 		dataFlexTable.getRowFormatter().addStyleName(0, "tableHeader");
		dataFlexTable.addStyleName("table");
 		for (int i=0; i<5; i++) {
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
		final int measurementNumberOfColumns = 5;
		int row = dataFlexTable.getRowCount();
		
		Float avgTemperature = new Float(Math.round(dataPoint.getTemperature()));
		Float uncertainty = new Float(Math.round(dataPoint.getUncertainty()));
		
		dataFlexTable.setText(row, 0, DateTimeFormat.getFormat("dd/MM/yyyy").format(dataPoint.getDate()));
		dataFlexTable.setText(row, 1, avgTemperature.toString());
		dataFlexTable.setText(row, 2, uncertainty.toString());
		dataFlexTable.setText(row, 3, dataPoint.getCity());
		dataFlexTable.setText(row, 4, dataPoint.getCountry());
		
		for(int i = 0; i < measurementNumberOfColumns; i++){
			dataFlexTable.getCellFormatter().addStyleName(row, i, "filterTableColumn");
		}
    }
}
