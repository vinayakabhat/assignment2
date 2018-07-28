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

	

	@RequestMapping(value = "/fullDetails", method = RequestMethod.GET)
	public @ResponseBody Map fullDetails(/* @PathVariable String name, */ ModelMap model) {

		Map map = null;
		try {
			map = dataProcessManager.getMap();
			model.addAttribute("movie", map);
			System.out.println(map);

		} catch (MyException e) {
			logger.info(e.getMessage());
		}
		return map;

	}

	@RequestMapping(value = "/getTimeRateMap", method = RequestMethod.GET)
	public @ResponseBody Map getTimeRateMap(/* @PathVariable String name, */ ModelMap model) {

		Map map = null;
		try {
			map = dataProcessManager.getTimeRateMap();
			model.addAttribute("movie", map);
			System.out.println(map);

		} catch (MyException e) {
			logger.info(e.getMessage());
		}
		return map;

	}

	@RequestMapping(value = "/getTimeRateMap/{minutes}", method = RequestMethod.GET)
	public @ResponseBody Map getTimeRateMapMin(@PathVariable("minutes") String minutes) {

		Map map = null;
		try {
			map = dataProcessManager.getTimeRateMap(Long.parseLong(minutes));
			System.out.println(map);

		} catch (MyException e) {
			logger.info(e.getMessage());
		}
		return map;

	}

	@RequestMapping(value = "/getGraph/{minutes}", method = RequestMethod.GET)
	public String getTimeRateForGraphPAge(@PathVariable("minutes") String minutes, Model model) {

		return "index";
	}

	@RequestMapping(value = "/getTimeRateForGraph/{minutes}", method = RequestMethod.GET)
	public @ResponseBody Set<CoinDetail> getTimeRateForGraph(@PathVariable("minutes") String minutes, Model model) {

		Map map = null;
		try {
			return dataProcessManager.getTimeRateForGraph();

		} catch (MyException e) {
			logger.info(e.getMessage());
		}

		return null;

	}
	
	@RequestMapping(value = "/getAvg/{minutes}", method = RequestMethod.GET)
	public @ResponseBody Map<BitAvg,Set<CoinDetail>> getAvg(@PathVariable("minutes") String minutes, Model model) {

		Map map = null;
		try {
			return dataProcessManager.getAverege(Long.parseLong(minutes));

		} catch (MyException e) {
			logger.info(e.getMessage());
		}

		return null;

	}
	
	@RequestMapping(value = "/getMed/{minutes}", method = RequestMethod.GET)
	public @ResponseBody Map<BitMed,Set<CoinDetail>> getMed(@PathVariable("minutes") String minutes, Model model) {

		Map map = null;
		try {
			return dataProcessManager.getMedian(Long.parseLong(minutes));

		} catch (MyException e) {
			logger.info(e.getMessage());
		}

		return null;

	}
	
	@RequestMapping(value = "/getMaxMin/{minutes}", method = RequestMethod.GET)
	public @ResponseBody Map<BitMax,Set<CoinDetail>> getMax(@PathVariable("minutes") String minutes, Model model) {

		Map map = null;
		try {
			return dataProcessManager.getMax(Long.parseLong(minutes));

		} catch (MyException e) {
			logger.info(e.getMessage());
		}

		return null;

	}
	
	
}