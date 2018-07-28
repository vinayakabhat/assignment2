package com.vinayaka.business.manager;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import com.vinayaka.business.model.BPI;
import com.vinayaka.business.model.BitCoinDetails;
import com.vinayaka.business.model.CoinDetail;
import com.vinayaka.exception.MyException;

public class DataProcessManagerImpl implements DataProcessManager {
	
	public static Map<Long,BitCoinDetails> cachedMap = new TreeMap<Long,BitCoinDetails>(Collections.reverseOrder());
	@Override
	public void startProcess() throws MyException {}
	@Override
	public Map getMap() throws MyException {
		
		
		
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

		Set<CoinDetail> details = new TreeSet();
		CoinDetail coinDetail = null;
		Map<Long,BitCoinDetails> finalMap = new TreeMap<Long, BitCoinDetails>();
		finalMap.putAll(cachedMap);
		
		
		
		for (Map.Entry<Long,BitCoinDetails> entry : finalMap.entrySet()) {
			
			Map<String,BPI> bpis = entry.getValue().getBpi();
			BPI bpi = bpis.get("USD");
			coinDetail= new CoinDetail();
			coinDetail.setLastMinute(entry.getKey());
			coinDetail.setPrice(bpi.getRate_float());
			details.add(coinDetail);
			
		}
		
		return details;
	
	}
	
		
	

}
