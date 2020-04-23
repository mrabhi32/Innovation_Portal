package com.vgil.model;

import java.util.HashSet;
import java.util.Set;

public class Chart {

	private String name;
	
	private String label;
	private Set<ChartData> data = new HashSet<ChartData>();
	
	public void addChartData(ChartData chartData) {
		if (chartData != null){
			if(data.size() < ChartColors.colors.length){
				chartData.setColor(ChartColors.colors[data.size()]);
				//chartData.setHighlight(ChartColors.highlight[data.size()]);
			}
			data.add(chartData);
		}
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Set<ChartData> getData() {
		return data;
	}
}



class ChartColors{
	static String[] colors =  {"#42CA96","#F0AD4E", "#5BC0DE","#F5F5F7","#FC0606","#926D6D","#DC15C5","#BDB0BC","#5F12FA","#777779","#A195BA","#08B2F0","#9BA5A9","#10EEC5","#10EE91","#30EF17","#EFD217"};
	//static String[] highlight =  {"#FF5A5E","#5AD3D1","#FFC870","#A8B3C5","#616774"};
}