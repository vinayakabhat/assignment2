package com.vinayaka.business.model;

public class BitMed {
	private Double median;

	
	@Override
	public String toString() {
		
		return "median :"+this.median;
	}


	public Double getMedian() {
		return median;
	}


	public void setMedian(Double median) {
		this.median = median;
	}

}
