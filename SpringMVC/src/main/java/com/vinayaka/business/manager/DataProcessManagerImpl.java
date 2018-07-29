package com.vinayaka.business.manager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import com.vinayaka.business.model.BPI;
import com.vinayaka.business.model.BitAvg;
import com.vinayaka.business.model.BitCoinDetails;
import com.vinayaka.business.model.BitMax;
import com.vinayaka.business.model.BitMed;
import com.vinayaka.business.model.CoinDetail;
import com.vinayaka.exception.MyException;

public class DataProcessManagerImpl implements DataProcessManager {
	
	public static Map<Long,BitCoinDetails> cachedMap = new TreeMap<Long,BitCoinDetails>(Collections.reverseOrder());
	@Override
	public void startProcess() throws MyException {}
	@Override
	public Map<Long,BitCoinDetails> getMap() throws MyException {
		
		
		
		Map<Long,BitCoinDetails> rateMap = new TreeMap<Long,BitCoinDetails>();
		Map<Long,BitCoinDetails> finalMap = new TreeMap<Long, BitCoinDetails>();
		finalMap.putAll(cachedMap);
		long size = finalMap.size();
		for (Map.Entry<Long,BitCoinDetails> entry : finalMap.entrySet()) {
			
			rateMap.put(size-entry.getKey(), entry.getValue());
		}
		
		return rateMap;
	}
	
	
	@Override
	public Map<Long, Double> getTimeRateMap() throws MyException {
		Map<Long, Double> rateMap = new TreeMap<Long, Double>();
		
		Map<Long,BitCoinDetails> finalMap = new TreeMap<Long, BitCoinDetails>();
		finalMap.putAll(cachedMap);
		long size = finalMap.size();
		for (Map.Entry<Long,BitCoinDetails> entry : finalMap.entrySet()) {
			
			Map<String,BPI> bpis = entry.getValue().getBpi();
			BPI bpi = bpis.get("USD");
			rateMap.put(size-entry.getKey(), bpi.getRate_float());
		}
		
		return rateMap;
	}
	@Override
	public Map<Long, Double> getTimeRateMap(Long minutes) throws MyException {

		Map<Long, Double> rateMap = new TreeMap<Long, Double>();
		
		Map<Long,BitCoinDetails> finalMap = new TreeMap<Long, BitCoinDetails>();
		finalMap.putAll(cachedMap);
		long size = finalMap.size();
		long count =0;
		if(size>minutes)
		{
			minutes = size-minutes;
		}else
		{
			minutes = 0l;
		}
		
		for (Map.Entry<Long,BitCoinDetails> entry : finalMap.entrySet()) {
			
			count++;
			if(count<=minutes) continue;
			
			Map<String,BPI> bpis = entry.getValue().getBpi();
			BPI bpi = bpis.get("USD");
			rateMap.put(size-entry.getKey(), bpi.getRate_float());
			
		}
		
		return rateMap;
	
	}
	
	/*@Override
	public Set<CoinDetail> getTimeRateForGraph(Long minutes) throws MyException {

		Set<CoinDetail> details = new TreeSet();
		CoinDetail coinDetail = null;
		Map<Long,BitCoinDetails> finalMap = new TreeMap<Long, BitCoinDetails>();
		finalMap.putAll(cachedMap);
		long size = finalMap.size();
		long count =0;
		if(size>minutes)
		{
			minutes = size-minutes;
		}else
		{
			minutes = 0l;
		}
		
		for (Map.Entry<Long,BitCoinDetails> entry : finalMap.entrySet()) {
			
			count++;
			if(count<=minutes) continue;
			
			Map<String,BPI> bpis = entry.getValue().getBpi();
			BPI bpi = bpis.get("USD");
			coinDetail= new CoinDetail();
			coinDetail.setLastMinute(size-entry.getKey());
			coinDetail.setPrice(bpi.getRate_float());
			details.add(coinDetail);
			
		}
		
		return details;
	
	}
	*/
	
	@Override
	public Set<CoinDetail> getTimeRateForGraph() throws MyException {

		Set<CoinDetail> details = new TreeSet<CoinDetail>();
		CoinDetail coinDetail = null;
		Map<Long,BitCoinDetails> finalMap = new TreeMap<Long, BitCoinDetails>();
		finalMap.putAll(cachedMap);
		
		
		
		for (Map.Entry<Long,BitCoinDetails> entry : finalMap.entrySet()) {
			
			Map<String,BPI> bpis = entry.getValue().getBpi();
			BPI bpi = bpis.get("USD");
			coinDetail= new CoinDetail();
			coinDetail.setLastMinute(entry.getKey());
			coinDetail.setPrice(bpi.getRate_float());
			coinDetail.setTime(new SimpleDateFormat("HH:mm:ss").format(entry.getValue().getTime().getUpdated()));
			details.add(coinDetail);
			
		}
		
		return details;
	
	}
	@Override
	public Map<BitAvg, Set<CoinDetail>> getAverege(Long minutes) throws MyException {

		Map<BitAvg, Set<CoinDetail>> avg = new HashMap<BitAvg, Set<CoinDetail>>();
		Set<CoinDetail> details = new TreeSet<CoinDetail>();
		CoinDetail coinDetail = null;
		Map<Long,BitCoinDetails> finalMap = new TreeMap<Long, BitCoinDetails>();
		finalMap.putAll(cachedMap);
		Double average=0d;
		Long avgCount=0l;
		Double sum = 0d;
		
		long size = finalMap.size();
		long count =0;
		if(size>minutes)
		{
			minutes = size-minutes;
		}else
		{
			minutes = 0l;
		}
		
		
		
		for (Map.Entry<Long,BitCoinDetails> entry : finalMap.entrySet()) {
			
			count++;
			if(count<=minutes) continue;
			
		
			
			Map<String,BPI> bpis = entry.getValue().getBpi();
			BPI bpi = bpis.get("USD");
			coinDetail= new CoinDetail();
			coinDetail.setLastMinute(size-entry.getKey());
			coinDetail.setPrice(bpi.getRate_float());
			coinDetail.setTime(new SimpleDateFormat("HH:mm:ss").format(entry.getValue().getTime().getUpdated()));
			details.add(coinDetail);
			sum = sum+bpi.getRate_float();
			avgCount++;
		}
		BitAvg bitavg = new BitAvg();
		bitavg.setAverage((sum/avgCount));
		avg.put(bitavg, details);
		
		return avg;
	
	
	}
	
	@Override
	public Map<BitMed, Set<CoinDetail>> getMedian(Long minutes) throws MyException {

		Map<BitMed, Set<CoinDetail>> avg = new HashMap<BitMed, Set<CoinDetail>>();
		Set<CoinDetail> details = new TreeSet<CoinDetail>();
		CoinDetail coinDetail = null;
		Map<Long,BitCoinDetails> finalMap = new TreeMap<Long, BitCoinDetails>();
		finalMap.putAll(cachedMap);
		List<Double> prices = new ArrayList<Double>();
		
		long size = finalMap.size();
		long count =0;
		if(size>minutes)
		{
			minutes = size-minutes;
		}else
		{
			minutes = 0l;
		}
		
		
		
		for (Map.Entry<Long,BitCoinDetails> entry : finalMap.entrySet()) {
			
			count++;
			if(count<=minutes) continue;
			
		
			
			Map<String,BPI> bpis = entry.getValue().getBpi();
			BPI bpi = bpis.get("USD");
			coinDetail= new CoinDetail();
			coinDetail.setLastMinute(size-entry.getKey());
			coinDetail.setPrice(bpi.getRate_float());
			coinDetail.setTime(new SimpleDateFormat("HH:mm:ss").format(entry.getValue().getTime().getUpdated()));
			details.add(coinDetail);
			prices.add(bpi.getRate_float());
		}
		BitMed bitMed = new BitMed();
		Collections.sort(prices);
		 if (prices.size() % 2 != 0)
			 bitMed.setMedian(prices.get(prices.size()/2));
		 else
			 bitMed.setMedian((prices.get((prices.size()-1)/2)+prices.get(prices.size()/2))/2);
			 
		avg.put(bitMed, details);
		
		return avg;
	
	
	}
	
	@Override
	public Map<BitMax, Set<CoinDetail>> getMax(Long minutes) throws MyException {

		Map<BitMax, Set<CoinDetail>> avg = new HashMap<BitMax, Set<CoinDetail>>();
		Set<CoinDetail> details = new TreeSet<CoinDetail>();
		CoinDetail coinDetail = null;
		Map<Long,BitCoinDetails> finalMap = new TreeMap<Long, BitCoinDetails>();
		finalMap.putAll(cachedMap);
		List<Double> prices = new ArrayList<Double>();
		
		long size = finalMap.size();
		long count =0;
		if(size>minutes)
		{
			minutes = size-minutes;
		}else
		{
			minutes = 0l;
		}
		
		
		
		for (Map.Entry<Long,BitCoinDetails> entry : finalMap.entrySet()) {
			
			count++;
			if(count<=minutes) continue;
			
		
			
			Map<String,BPI> bpis = entry.getValue().getBpi();
			BPI bpi = bpis.get("USD");
			coinDetail= new CoinDetail();
			coinDetail.setLastMinute(size-entry.getKey());
			coinDetail.setPrice(bpi.getRate_float());
			coinDetail.setTime(new SimpleDateFormat("HH:mm:ss").format(entry.getValue().getTime().getUpdated()));
			details.add(coinDetail);
			prices.add(bpi.getRate_float());
		}
		BitMax bitMax = new BitMax();
		Collections.sort(prices);
		
		bitMax.setMaximum(prices.get(prices.size()-1)); 
		bitMax.setMinimum(prices.get(0)); 
			 
		avg.put(bitMax, details);
		
		return avg;
	
	
	}
	
		
	

}
