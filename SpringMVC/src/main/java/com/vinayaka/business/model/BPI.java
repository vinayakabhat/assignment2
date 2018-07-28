package com.vinayaka.business.model;



public class BPI {
	
	private String code;
	private String symbol;
	private String rate;
	private double rate_float;
	private String description;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public double getRate_float() {
		return rate_float;
	}
	public void setRate_float(double rate_float) {
		this.rate_float = rate_float;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	
	

}

