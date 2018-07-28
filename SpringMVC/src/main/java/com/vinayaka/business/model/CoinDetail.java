package com.vinayaka.business.model;

public class CoinDetail implements Comparable<CoinDetail> {
	
	Long lastMinute;
	Double price;
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

}
