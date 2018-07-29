# REST implementation using Spring

This application interacts with CoinDesk(https://api.coindesk.com/v1/bpi/currentprice.json) and process the latest bitcoin price(in USD) data and provides the price statistics.
This application every minute interacts with the coindesk and store the data it the cache. when ever request comes to our server it returns the processed data cached in our server.  


### Prerequisites

-JDK 8.
-Maven (Apache Maven 3.2.1 used by me)-for building.
-Apache Tomcat(apache-tomcat-8.0.53 used by me) - to deploy the application.
-PostMan to test the APIS.

### Installing and Deploying the application

-open the command promt for the project folder containing pom.xml
-run the command 'mvn install'
-once installation done go to target folder and get the SpringMVC.war
-deploy the same war file in the tomcat server


## Running the tests

Once the Apllication is deployed sucessfully we can check the exposed APIs using PostMan Application. 
Leave some minutes for the application after deployment to get and store the data in its cache. 


-open Postman

- Paste the URL -  http://localhost:8080/SpringMVC/firstPath/fullDetails and hit Send (note:port number may differ based on your server configuration).
- we will get the full details about BitCoins which are stored in server from server starting time
 
 
- Paste the URL - http://localhost:8080/SpringMVC/firstPath/getTimeRateMap and hit Send (note:port number may differ based on your server configuration).
- we will get the  details about BitCoins(in USD) which are stored in server from server starting time. 
- record at the top or with key "1" will be the latest minute record .


- Paste the URL - http://localhost:8080/SpringMVC/firstPath/getTimeRateMap/5 and hit Send (note:port number may differ based on your server configuration).
- we will get the latest details about BitCoins(in USD) which are stored in server from last 5 minutes.
- change the last number parameter which specifies the minutes count.
 
	eg : http://localhost:8080/SpringMVC/firstPath/getTimeRateMap/6
		 http://localhost:8080/SpringMVC/firstPath/getTimeRateMap/7
		 http://localhost:8080/SpringMVC/firstPath/getTimeRateMap/8
		 .
		 .
		 http://localhost:8080/SpringMVC/firstPath/getTimeRateMap/n
	
- record at the top or with key "1" will be the latest minute record .
 
- Paste the URL -  http://localhost:8080/SpringMVC/firstPath/getAvg/5 and hit Send (note:port number may differ based on your server configuration).
- we will get the latest average details about BitCoin price which are stored in server from last 5 min.
- change the last number parameter which specifies the minutes count.
	
	eg:
	  http://localhost:8080/SpringMVC/firstPath/getAvg/5 
	  http://localhost:8080/SpringMVC/firstPath/getAvg/6 
	  http://localhost:8080/SpringMVC/firstPath/getAvg/7
		.
		.
	http://localhost:8080/SpringMVC/firstPath/getAvg/n
	
	
- Paste the URL -  http://localhost:8080/SpringMVC/firstPath/getMed/5 and hit Send (note:port number may differ based on your server configuration).
- we will get the latest Median details about BitCoin price which are stored in server from last 5 min.
- change the last number parameter which specifies the minutes count.
	
	eg:
	  http://localhost:8080/SpringMVC/firstPath/getMed/5 
	  http://localhost:8080/SpringMVC/firstPath/getMed/6 
	  http://localhost:8080/SpringMVC/firstPath/getMed/7
		.
		.
	http://localhost:8080/SpringMVC/firstPath/getMed/n
	

- Paste the URL -  http://localhost:8080/SpringMVC/firstPath/getMaxMin/5 and hit Send (note:port number may differ based on your server configuration).
- we will get the latest Maximun and minimum price details about BitCoin price which are stored in server from last 5 min.
- change the last number parameter which specifies the minutes count.
	
	eg:
	 http://localhost:8080/SpringMVC/firstPath/getMaxMin/5 
	 http://localhost:8080/SpringMVC/firstPath/getMaxMin/6
	 http://localhost:8080/SpringMVC/firstPath/getMaxMin/8 
		.
		.
	http://localhost:8080/SpringMVC/firstPath/getMaxMin/note

- Paste the URL -  http://localhost:8080/SpringMVC/firstPath/getGraph  and hit Send (note:port number may differ based on your server configuration).
- It will show the graph of price variation (third party API used to plot the graph)



