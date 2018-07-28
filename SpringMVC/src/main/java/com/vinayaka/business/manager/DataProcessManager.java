package com.vinayaka.business.manager;

import java.util.Map;
import java.util.Set;

import com.vinayaka.business.model.CoinDetail;
import com.vinayaka.exception.MyException;

public interface DataProcessManager {
	
	public void startProcess() throws MyException;
	
	public Map getMap() throws MyException;
	public Map<Long,Double> getTimeRateMap() throws MyException;
	public Map<Long,Double> getTimeRateMap(Long minutes) throws MyException;

	public Set<CoinDetail> getTimeRateForGraph() throws MyException;

}
