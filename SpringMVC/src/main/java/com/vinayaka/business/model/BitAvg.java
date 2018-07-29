package com.vinayaka.business.model;

public class BitAvg {
	private Double average;

	public Double getAverage() {
		return average;
	}

	public void setAverage(Double average) {
		this.average = average;
	}
	@Override
	public String toString() {
		
		return "average :"+this.average;
	}

}
