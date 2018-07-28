package com.vinayaka.business.model;

import java.util.Map;

public class BitCoinDetails {
	private Time time;
	private Map<String,BPI> bpi;
	private String chartName;
	private String disclaimer;
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	
	public String getChartName() {
		return chartName;
	}
	public void setChartName(String chartName) {
		this.chartName = chartName;
	}
	public String getDisclaimer() {
		return disclaimer;
	}
	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}
	public Map<String,BPI> getBpi() {
		return bpi;
	}
	public void setBpi(Map<String,BPI> bpi) {
		this.bpi = bpi;
	}
}
