package com.gwt.climatechange.client;

import static org.junit.Assert.assertNotNull;
import java.util.ArrayList;
import org.junit.Test;
import com.gwt.climatechange.shared.DataPoint;

public class WorldMapMenuTest {

	private final ArrayList<DataPoint> list = new ArrayList<DataPoint>();
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
