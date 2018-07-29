package com.vinayaka.business.model;

import java.util.Date;

public class CoinDetail implements Comparable<CoinDetail> {
	
	private Long lastMinute;
	private Double price;
	private String time;
	public Long getLastMinute() {
		return lastMinute;
	}
	public void setLastMinute(Long lastMinute) {
		this.lastMinute = lastMinute;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	@Override
	public int compareTo(CoinDetail o) {
		return compare(this.getLastMinute(), o.lastMinute);
	}
	private int compare(Long x, Long y) {
		
		return (x < y) ? -1 : ((x == y) ? 0 : 1);
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

}
