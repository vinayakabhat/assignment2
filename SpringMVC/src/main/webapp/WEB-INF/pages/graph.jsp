<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
 
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="refresh" content="10" >
<script type="text/javascript">

window.onload = function() { 
 
var dataPoints = [];
 
var chart = new CanvasJS.Chart("chartContainer", {
	animationEnabled: true,
	theme: "light2",
 	zoomEnabled: true,
	title: {
		text: "BitCoin Statistics "
	},
	axisY: {
		title: "BitCoin Rate (in USD)",
		titleFontSize: 24,
		includeZero: false
	},
	axisX: {
		title: "price variation over last X minutes",
		titleFontSize: 20,
		includeZero: false
	},
	data: [{
		type: "line",
		yValueFormatString: "#,##0.0000## USD",
		xValueType: "time",
		dataPoints: dataPoints
	}]
});
 
function addData(data) {
	for (var i = 0; i < data.length; i++) {
		dataPoints.push({
			x: data[i].lastMinute,
			y: data[i].price
		});
	}
	chart.render();
}
 
$.getJSON("http://localhost:8080/SpringMVC/firstPath/getTimeRateForGraph/34.json", addData);
 
}
</script>
</head>
<body>

<div id="chartContainer" style="height: 370px; width: 100%;"></div>
<script src="https://canvasjs.com/assets/script/jquery-1.11.1.min.js"></script>
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
</body>
</html>          