package com.vinayaka.backgroundwork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinayaka.business.manager.DataProcessManagerImpl;
import com.vinayaka.business.model.BitCoinDetails;

class myTask extends TimerTask
{
	private static Logger logger = Logger.getLogger("myTask");
	private static Long minutesKey = 0l;
	private static Long cachSize = 1440L;
	
	@Override
	public void run() {
		startProcess();
		
	}

	/*This process runs every minute and read the data from coindesk. 
	 * Inserts the data in our server cache DataProcessManagerImpl.cachedMap which is a treeMap
	*/
	private void startProcess() {

		
		
		try {

			URL url = new URL("https://api.coindesk.com/v1/bpi/currentprice.json");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.29 Safari/537.36");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

			StringBuilder output = new StringBuilder();
			
			int cp;
			
			while ((cp=br.read()) != -1) {
				output.append((char)cp);
				
			}
			
			ObjectMapper obm = new ObjectMapper();
			BitCoinDetails b = obm.readValue(output.toString(),BitCoinDetails.class);
			
			DataProcessManagerImpl.cachedMap.put(minutesKey++, b);
			if(DataProcessManagerImpl.cachedMap.size()>cachSize)
			{
				DataProcessManagerImpl.cachedMap.clear();
				minutesKey=0l;
			}
			conn.disconnect();

		  } catch (MalformedURLException e) {

			  logger.info(e.getMessage());

		  } catch (IOException e) {

			 logger.info(e.getMessage());

		  }

		
		
	}
	
}

public class RestBackgroundJob {
	public RestBackgroundJob() {
		
		
		Timer timer = new Timer();
		myTask mt = new myTask();
		
		/*This task runs every minutes*/
		timer.scheduleAtFixedRate(mt, 0, 60000);
		
	}

}
