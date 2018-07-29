package com.vinayaka.common.controller;

import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vinayaka.business.manager.DataProcessManager;
import com.vinayaka.business.model.BitAvg;
import com.vinayaka.business.model.BitCoinDetails;
import com.vinayaka.business.model.BitMax;
import com.vinayaka.business.model.BitMed;
import com.vinayaka.business.model.CoinDetail;
import com.vinayaka.exception.MyException;

@Controller
@RequestMapping("/firstPath")
public class MyController {

	private static Logger logger = Logger.getLogger("MyController");

	@Autowired
	DataProcessManager dataProcessManager;

	public DataProcessManager getDataProcessManager() {
		return dataProcessManager;
	}

	public void setDataProcessManager(DataProcessManager dataProcessManager) {
		this.dataProcessManager = dataProcessManager;
	}

	
	/*it will give the full cached BitCoin price details from server starting time*/
	@RequestMapping(value = "/fullDetails", method = RequestMethod.GET)
	public @ResponseBody Map<Long,BitCoinDetails> fullDetails(/* @PathVariable String name, */ ModelMap model) {

		Map<Long,BitCoinDetails> map = null;
		try {
			map = dataProcessManager.getMap();
			

		} catch (MyException e) {
			logger.info(e.getMessage());
		}
		return map;

	}

	/*it will give the  server stored BitCoin price(in USD) details from server starting time*/
	@RequestMapping(value = "/getTimeRateMap", method = RequestMethod.GET)
	public @ResponseBody Map<Long,Double>  getTimeRateMap(/* @PathVariable String name, */ ModelMap model) {

		Map<Long,Double>  map = null;
		try {
			map = dataProcessManager.getTimeRateMap();
			model.addAttribute("movie", map);
			System.out.println(map);

		} catch (MyException e) {
			logger.info(e.getMessage());
		}
		return map;

	}

	/* it will give the server stored BitCoin price(in USD) details over last X minutes*/
	
	@RequestMapping(value = "/getTimeRateMap/{minutes}", method = RequestMethod.GET)
	public @ResponseBody Map<Long,Double>  getTimeRateMapMin(@PathVariable("minutes") String minutes) {

		Map<Long,Double>  map = null;
		try {
			map = dataProcessManager.getTimeRateMap(Long.parseLong(minutes));
			System.out.println(map);

		} catch (MyException e) {
			logger.info(e.getMessage());
		}
		return map;

	}

	@RequestMapping(value = "/getGraph", method = RequestMethod.GET)
	public String getTimeRateForGraphPage() {

		return "index";
	}

	/*
	 * it will give the  server stored BitCoin price(in USD) details from server starting time. This is for plotting graph*/
	@RequestMapping(value = "/getTimeRateForGraph/{minutes}", method = RequestMethod.GET)
	public @ResponseBody Set<CoinDetail> getTimeRateForGraph(@PathVariable("minutes") String minutes, Model model) {

		
		try {
			return dataProcessManager.getTimeRateForGraph();

		} catch (MyException e) {
			logger.info(e.getMessage());
		}

		return null;

	}
	
	
	/*
	 * it will give the  Average BitCoin price(in USD) details over last X minutes
	*/
	@RequestMapping(value = "/getAvg/{minutes}", method = RequestMethod.GET)
	public @ResponseBody Map<BitAvg,Set<CoinDetail>> getAvg(@PathVariable("minutes") String minutes, Model model) {

		
		try {
			return dataProcessManager.getAverege(Long.parseLong(minutes));

		} catch (MyException e) {
			logger.info(e.getMessage());
		}

		return null;

	}
	
	/*
	 * it will give the  Median price(in USD) details over last X minutes
	*/
	@RequestMapping(value = "/getMed/{minutes}", method = RequestMethod.GET)
	public @ResponseBody Map<BitMed,Set<CoinDetail>> getMed(@PathVariable("minutes") String minutes, Model model) {

		
		try {
			return dataProcessManager.getMedian(Long.parseLong(minutes));

		} catch (MyException e) {
			logger.info(e.getMessage());
		}

		return null;

	}
	
	
	/*
	 * it will give the  Max and Min BitCoin price(in USD) details over last X minutes
	*/
	@RequestMapping(value = "/getMaxMin/{minutes}", method = RequestMethod.GET)
	public @ResponseBody Map<BitMax,Set<CoinDetail>> getMax(@PathVariable("minutes") String minutes, Model model) {

		
		try {
			return dataProcessManager.getMax(Long.parseLong(minutes));

		} catch (MyException e) {
			logger.info(e.getMessage());
		}

		return null;

	}
	
	
}