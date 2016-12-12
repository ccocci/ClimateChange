package com.gwt.climatechange.client.slider;

import org.junit.Test;
import com.google.gwt.junit.client.GWTTestCase;

public class SliderTest extends GWTTestCase{

	private final String ID = "test";
	private final int MIN = 0;
	private final int MAX = 10;
	private final int DEFAULT_VALUE = 8;
	private final int[] DEFAULT_VALUES = {2,3,4};
	private final int INDEX = 1;
	
	@Override
	public String getModuleName() {
		return "com.gwt.climatechange.Slider";
	}
	
	@Test
	public void testSliderAll(){
		Slider slider = new Slider(ID, MIN, MAX, DEFAULT_VALUE);
		assertNotNull(slider);
	}
	
	@Test
	public void testSliderAllValues(){
		Slider slider = new Slider(ID, MIN, MAX, DEFAULT_VALUES);
		assertNotNull(slider);
	}
	
	@Test
	public void testSliderId(){
		Slider slider = new Slider(ID);
		assertNotNull(slider);
	}
	
	public void testOnLoad(){
		assertTrue(true);
	}
	
	public void testOnUnload(){
		assertTrue(true);
	}
	
	public void testGetMinimum(){
		Slider slider = new Slider(ID, MIN, MAX, DEFAULT_VALUES);
		assertEquals(MIN, slider.getMinimum());
	}
	
	public void testGetMaximum(){
		Slider slider = new Slider(ID, MIN, MAX, DEFAULT_VALUES);
		assertEquals(MAX, slider.getMaximum());
	}
	
	public void testGetValue(){
		Slider slider = new Slider(ID, MIN, MAX, DEFAULT_VALUE);
		assertEquals(DEFAULT_VALUE, slider.getValue());
	}
	
	public void testGetValueAtIndex(){
		Slider slider = new Slider(ID, MIN, MAX, DEFAULT_VALUES);
		assertEquals(DEFAULT_VALUES[INDEX], slider.getValueAtIndex(INDEX));
	}
}
