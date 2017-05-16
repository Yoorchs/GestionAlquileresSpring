/**
 * Script para consumir el WS de WeatherForecast
 */

const
url = "http://api.wunderground.com/api/05724545ab223601/geolookup/conditions/forecast/q/Spain/Bilbao.json";
var weekforecast;
jQuery(document).ready(function($) {
	$.ajax({
		url : url,
		  dataType : "jsonp",
		  success : function(parsed_json) {
			  forecast = parsed_json.response.results;
			  console.log(forecast);
			  $("#weather today location").text(forecast[0].city);
			  $("#weather today country").text(forecast[0].country);
		  }
		  });
		});