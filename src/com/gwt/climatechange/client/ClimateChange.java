package com.gwt.climatechange.client;

import com.google.gwt.core.client.EntryPoint;
//import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
//import com.google.gwt.event.dom.client.ClickEvent;
//import com.google.gwt.event.dom.client.ClickHandler;
//import com.google.gwt.event.dom.client.KeyCodes;
//import com.google.gwt.event.dom.client.KeyUpEvent;
//import com.google.gwt.event.dom.client.KeyUpHandler;
//import com.google.gwt.user.client.rpc.AsyncCallback;
//import com.google.gwt.user.client.ui.Button;
//import com.google.gwt.user.client.ui.DialogBox;
//import com.google.gwt.user.client.ui.FlexTable;
//import com.google.gwt.user.client.ui.HTML;
//import com.google.gwt.user.client.ui.HorizontalPanel;
//import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
//import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;


//import com.google.gwt.user.client.ui.TextBox;
//import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.DockLayoutPanel;

/**
* 
* The class ClimateChangeApp generates the UI.
* 
* @author Sophy Chhong
* @history 11-11-2016 SC Constructor & onModuleLoad
* 			12-11-2016 SC Constructor corrected
* @version 12-11-2016 SC Version 1
* @responsabilities This class creates tabs including the world map and the data table.
*
*/

public class ClimateChange extends TabLayoutPanel implements EntryPoint {
	
	private WorldMapView worldMapView = new WorldMapView();
	private WorldMapMenu worldMapMenu = new WorldMapMenu();
	private DockLayoutPanel dockLayoutPanel = new DockLayoutPanel(Style.Unit.EM);
	private TablePanel tablePanel = new TablePanel();
	private ScrollPanel scrollPanel= new ScrollPanel();
	
	//private Button exportButton= new Button("Export data");
	//private Data data;
	
	public ClimateChange(){
		
		//Creates TabLayoutPanel
		super(9, Style.Unit.EM);		
		//Creates TabLayoutPanel with two tabs
		
		scrollPanel.add(tablePanel);
		add(dockLayoutPanel, "World Map");
		add(scrollPanel, "Data Table");
		addStyleName("tabs");
		dockLayoutPanel.add(worldMapMenu);
		selectTab(0);
		
		//Add tabPanel to north
		//addSouth(exportButton, 10);
	}
	
	/*
	 * private static final String SERVER_ERROR = "An error occurred while "
	 
			+ "attempting to contact the server. Please check your network " + "connection and try again.";
	 */
	
	/**
	 * 
	 * Loads app.
	 * @pre		-
	 * @post	app is loaded
	 * 
	 */
	
	public void onModuleLoad() {
		ClimateChange app = new ClimateChange();
		RootLayoutPanel.get().add(app);
		
		//data = new Data();
		//data.getData();
	}
	
}