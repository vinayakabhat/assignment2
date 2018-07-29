package com.vinayaka.business.manager;

import java.util.Map;
import java.util.Set;

import com.vinayaka.business.model.BitAvg;
import com.vinayaka.business.model.BitCoinDetails;
import com.vinayaka.business.model.BitMax;
import com.vinayaka.business.model.BitMed;
import com.vinayaka.business.model.CoinDetail;
import com.vinayaka.exception.MyException;

/**
 * @author vinayakakr
 *
 */
public interface DataProcessManager {
	
	/* Not Using the below method
	 * 
	 * */
	public void startProcess() throws MyException;
	
	/*it will give the full BitCoin price details
	 * 
	 * @return Map<Long,BitCoinDetails>
	 * @throws MyException
	 * */
	public Map<Long,BitCoinDetails> getMap() throws MyException;
	
	
	/*it will give the  server stored BitCoin price(in USD) details from server starting time
	 * @return Map<Long,Double>
	 * @throws MyException
	 *
	 * */
	public Map<Long,Double> getTimeRateMap() throws MyException;
	
	
	/*
	 * it will give the  server stored BitCoin price(in USD) details over last X minutes
	 * @param minutes - time in minutes
	 * @return Map<Long,Double>
	 * @throws MyException
	 * */
	public Map<Long,Double> getTimeRateMap(Long minutes) throws MyException;

	
	/*it will give the  server stored BitCoin price(in USD) details from server starting time
	 * @return Map<Long,Double>
	 * @throws MyException
	 * */	
	public Set<CoinDetail> getTimeRateForGraph() throws MyException;
	
	
	/*
	 * it will give the  Average BitCoin price(in USD) details over last X minutes
	 * @param min - time in minutes
	 * @return Map<BitAvg,Set<CoinDetail>>
	 * @throws MyException
	 * */	
	public Map<BitAvg,Set<CoinDetail>> getAverege(Long min) throws MyException;

	/*
	 * it will give the  Median of BitCoin price(in USD) details over last X minutes
	 * @param minutes - time in minutes
	 * @return Map<BitMed, Set<CoinDetail>>
	 * @throws MyException
	 * */
	Map<BitMed, Set<CoinDetail>> getMedian(Long minutes) throws MyException;

	/*
	 * it will give the  Maximum and Minimum of BitCoin price(in USD) details over last X minutes
	 * @param minutes - time in minutes
	 * @return Map<BitMax, Set<CoinDetail>>
	 * @throws MyException
	 * */
	Map<BitMax, Set<CoinDetail>> getMax(Long minutes) throws MyException;

}
