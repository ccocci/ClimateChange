package com.gwt.climatechange.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.DockLayoutPanel;

/**
* 
* The class ClimateChangeApp generates the UI.
* 
* @author Sophy Chhong
* @history  11-11-2016 SC Constructor & onModuleLoad
* 			12-11-2016 SC Constructor corrected
* 			07-12-2016 CC Panels actualized
* @version  12-11-2016 SC Version 1
* 			07-12-2016 CC Version 2
* @responsabilities This class creates tabs including the world map and the data table.
*
*/

public class ClimateChange extends TabLayoutPanel implements EntryPoint {
	
	private WorldMapMenu worldMapMenu = new WorldMapMenu();
	private DockLayoutPanel dockLayoutPanel = new DockLayoutPanel(Style.Unit.EM);
	private TablePanel tablePanel = new TablePanel();
	private ScrollPanel scrollPanel= new ScrollPanel();
	
	public ClimateChange(){
		
		//Creates TabLayoutPanel
		super(9, Style.Unit.EM);	
		scrollPanel.add(tablePanel);
		add(dockLayoutPanel, "World Map");
		add(scrollPanel, "Data Table");
		addStyleName("tabs");
		dockLayoutPanel.add(worldMapMenu);
		selectTab(0);
	}
	
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
	}
}