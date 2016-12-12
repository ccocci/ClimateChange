package com.gwt.climatechange.client;

import java.util.Date;
import com.google.gwt.user.client.ui.Button;

/**
* 
* The class FilterRow manages the categories one filters for.
* 
* @author Sophy Chhong
* @history  09-12-2016 SC Constructors and Getters
* @version  09-12-2016 SC Version 1
* @responsabilities This class creates a new Row for the FilterTable.
*
*/

public class FilterRow {
	private String country;
	private String city;
	private Date sdate;
	private Date edate;
    private Button removeFilterButton;
	
    /**
	 * Constructor that sets values country, city, sdate, edate and the removeButton.
	 * 
	 * @pre 	city != null && country != null && sdate != null && edate != null
	 * @post 	removeFilterButton set to "x"
	 */
    
	public FilterRow(String country, String city, Date sdate, Date edate){
		this.country = country;
		this.city = city;
		this.sdate = sdate;
		this.edate = edate;
		this.removeFilterButton = new Button("x");
	}
	
	/**
	 * Constructor that sets the value city and the removeButton.
	 * 
	 * @pre 	city != null
	 * @post 	removeFilterButton set to "x"
	 */
	
	public FilterRow(String city){
		this.city = city;
		this.removeFilterButton = new Button("x");
	}
	
	/**
	 * Getters for Country, City, EndDate, StartDate and the RemoveButton
	 */
	
	public String getCountry(){
		return this.country;
	}
	
	public String getCity(){
		return this.city;
	}
	
	public Date getStartDate(){
		return this.sdate;
	}
	
	public Date getEndDate(){
		return this.edate;
	}
	
	public Button getRemoveButton(){
		return this.removeFilterButton;
	}
	
}
