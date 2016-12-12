package com.gwt.climatechange.client;

import java.util.ArrayList;
import org.junit.Test;
import com.google.gwt.junit.client.GWTTestCase;
import com.gwt.climatechange.shared.DataPoint;

public class WorldMapMenuTest extends GWTTestCase{

	private final ArrayList<DataPoint> list = new ArrayList<DataPoint>();
	
	@Override
	public String getModuleName() {
		return "com.gwt.climatechange.WorldMapMenu";
	}
	
	@Test
	public void testWorldMapMenuSetUp(){
		WorldMapMenu worldMap = new WorldMapMenu();
		assertNotNull(worldMap);
	}
	
	@Test
	public void testGenerateAverageCityList(){
		WorldMapMenu worldMap = new WorldMapMenu();
		assertNotNull(worldMap.generateCityAverageList(list));
	}
}
