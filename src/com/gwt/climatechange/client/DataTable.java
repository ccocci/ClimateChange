package com.gwt.climatechange.client;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.FlexTable;

import com.gwt.climatechange.shared.DataPoint;

/**
* 
* The class DataTable generates the table and fills in the data.
* 
* @author Lina Witzel
* @history  08-11-2016 LW First version
 * 			09-11-2016 LL Constructor corrected
 * 			12-11-2016 LW Insertion test data
 * 			13-11-2016 LW Change test data
 * 			26-11-2016 LW Add AsyncCallback and data import
 * 			09-12-2016 CC Data Import
* @version  13-11-2016 LW Version 1
* 			09-12-2016 CC Version 2
* @responsabilities SetUp of dataFlexTable and filling in data
*
*/

public class DataTable {
    FlexTable dataFlexTable = new FlexTable();
    
    /**
	 * Method sets up table and styles it
	 * @pre 	dataFlexTable != null
	 * @post 	table is visible and styled && i=4
	 */
    
    public void setUpMeasurementTable(){
    	
    	 // Create table for data
 		dataFlexTable.setText(0, 0, "Date");
 		dataFlexTable.setText(0, 1, "Average Temperature");
 		dataFlexTable.setText(0, 2, "Average Temperature Uncertainty");
 		dataFlexTable.setText(0, 3, "City");
 		dataFlexTable.setText(0, 4, "Country");
 		
 		// Add styles to elements in the dataTable.
 		dataFlexTable.getRowFormatter().addStyleName(0, "tableHeader");
		dataFlexTable.addStyleName("table");
 		for (int i=0; i<5; i++) {
 			dataFlexTable.getCellFormatter().addStyleName(0, i, "tableNumericColumn");
 		}
    }
    
    /**
	 * Method clears the DataTable.
	 * @pre 	dataFlexTable != null
	 * @post 	i = measurementRowCount - 1
	 */
    
    public void clearMeasurementTable(){
	      int measurementRowCount = dataFlexTable.getRowCount()-1;
	 	  for(int i = 1; i < measurementRowCount; ){
	 		 dataFlexTable.removeRow(i);
	      }
    }
    
    /**
	 * Getter for table
	 */
    
    public FlexTable getMeasurementTable(){
    	return this.dataFlexTable;
    }
    
    /**
	 * Adds the data to the table, rounds avgtemperature and uncertainty.
	 * @pre		dataFlexTable != null && dataPoint != null
	 * @post	i=4
	 */
    
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
