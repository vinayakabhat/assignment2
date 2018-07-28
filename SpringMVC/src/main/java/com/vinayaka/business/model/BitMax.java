package com.vinayaka.business.model;

public class BitMax {
	private Double maximum;
	private Double minimum;

	
	@Override
	public String toString() {
		
		return " maximum :"+this.maximum+" minimum :"+this.minimum;
	}


	
	public Double getMaximum() {
		return maximum;
	}


	public void setMaximum(Double maximum) {
		this.maximum = maximum;
	}


	public Double getMinimum() {
		return minimum;
	}


	public void setMinimum(Double minimum) {
		this.minimum = minimum;
	}


	

}
