package com.gwt.climatechange.client;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.VerticalPanel;

import com.gwt.climatechange.client.slider.Slider;





/**import org.gwtbootstrap3.extras.slider.client.ui.Range;
import org.gwtbootstrap3.extras.slider.client.ui.Slider;
import org.gwtbootstrap3.extras.slider.client.ui.base.event.SlideStopEvent;
import org.gwtbootstrap3.extras.slider.client.ui.base.event.SlideStopHandler;
*/

public class WorldMapMenu extends VerticalPanel {
	// create panels
	
	private VerticalPanel sliderPanel= new VerticalPanel();
	private Label yearLabel= new Label("Year");
	private Slider slider= new Slider("Slider",1880,2013,2013);
	
	
	
	// create sliders and their labels
	
	

	 
	
	
	
	public WorldMapMenu(){
		super();
		fillPanels();
		add(sliderPanel);
		
		//handleEvents();
		}
	
	
	
	
/**	private void handleEvents(){
		
		
		// Action when Country is chosen
		chooseCountry.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent changeEvent) {
				// TODO show data on map (use getSelectedIndex/Value Method)
			}
		});
				
		// Action when City is chosen
		chooseCity.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent changeEvent) {
				// TODO show data on map (use getSelectedIndex/Value Method)
			}
		});	
		
		
		//Action when year is changed
		yearSlider.addSlideStopHandler(new SlideStopHandler<Range>(){
			public void onSlideStop(SlideStopEvent<Range> slideStopEvent){
				// TODO show data on map
			}
			});
		
		//Action when temp is changed
		tempSlider.addSlideStopHandler(new SlideStopHandler<Range>(){
			public void onSlideStop(SlideStopEvent<Range> slideStopEvent){
				// TODO show data on map
			}
			});
		
		
		//Action when tempUncertainty is changed
		tempUncertaintySlider.addSlideStopHandler(new SlideStopHandler<Range>(){
			public void onSlideStop(SlideStopEvent<Range> slideStopEvent){
				// TODO show data on map
			}
			});
		
	}
	*/
	private void fillPanels(){
		//styleSlider();
		//Add everything in slidePanel
		slider.setWidth("200px");
		slider.setStyleName("slider");
		yearLabel.setStyleName("yearLabel");
		sliderPanel.add(yearLabel);
		sliderPanel.add(slider);
		
	
	}
	
	
	
/*	private void styleSlider() {
		// TODO anpassung der Mindest- und Maximaltemperatur.
		yearSlider.setMin(1849);
		yearSlider.setMax(2013);
		yearSlider.setWidth("400px");
		tempSlider.setMin(-5);
		tempSlider.setMax(50);
		tempSlider.setWidth("400px");
		tempUncertaintySlider.setMin(0);
		tempUncertaintySlider.setMax(3);
		tempUncertaintySlider.setWidth("100px");
	}
	
	*/

}