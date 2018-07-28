package com.vinayaka.business.manager;

import java.util.Map;
import java.util.Set;

import com.vinayaka.business.model.BitAvg;
import com.vinayaka.business.model.BitMax;
import com.vinayaka.business.model.BitMed;
import com.vinayaka.business.model.CoinDetail;
import com.vinayaka.exception.MyException;

public interface DataProcessManager {
	
	public void startProcess() throws MyException;
	
	public Map getMap() throws MyException;
	public Map<Long,Double> getTimeRateMap() throws MyException;
	public Map<Long,Double> getTimeRateMap(Long minutes) throws MyException;

	public Set<CoinDetail> getTimeRateForGraph() throws MyException;
	public Map<BitAvg,Set<CoinDetail>> getAverege(Long min) throws MyException;

	Map<BitMed, Set<CoinDetail>> getMedian(Long minutes) throws MyException;

	Map<BitMax, Set<CoinDetail>> getMax(Long minutes) throws MyException;

}
