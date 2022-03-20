package edu.arapahoe.csc245;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.*;
import com.google.gson.reflect.*;

////////////////////////////////////////////////////////////////////////////////////
//
// This program was created for Arapahoe Community College's CSC-245 course and
// identifies the current temperature for a location using the Open Weather Map API.
//
// The use of the API (openweathermap.org) was applied for and access granted 202010321
// The key comes with several technical constraints regarding its usage, including:
//     Hourly forecast: unavailable
//     Daily forecast: unavailable
//     Calls per minute: 60
//     3 hour forecast: 5 days
//
// Details on the use of the API can be found here:
//     https://openweathermap.org/current
//
// The default location is Castle Rock, CO (encoded as Castle Rock, US) but can be
// changed, as required. The GPS coordinates for Castle Rock, CO is
// latitude 39.3722        longitude -104.8561
//
// CSC 245 Secure Software Development
//
// Change log:
//      20210321 API access granted
//      20210322 Initially created (ddl)
//
// Dependencies:
//      gson-2.2.2.jar is needed for correct functioning
//		Oracle JDK 1.8
//
//
//		Derek McPeak
//		03/11/2022
//		Arapahoe Community College CSC245
//
////////////////////////////////////////////////////////////////////////////////////


public class CSC245_Project3_Insecure {
	// Java Maps are used with many API interactions. OpenWeatherMap also uses Java Maps.

	//removed initialization of map since the method is a map and shortened to a single return statement to
	//return that creates the map.
	public static Map<String, Object> jsonToMap(String str) {
		return new Gson().fromJson(
				str, new TypeToken<HashMap<String, Object>>() {}.getType()
				);
	}

	public static String getTempForCity (String cityString, String api_key) {
		String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" +
				cityString + "&appid=" + api_key + "&units=imperial";
		try {
			StringBuilder result = new StringBuilder();
			URL url = new URL(urlString);
			URLConnection conn = url.openConnection();
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null)
				result .append(line);
			System.out.println(result);

			Map<String, Object > respMap = jsonToMap (result.toString());
			Map<String, Object > mainMap = jsonToMap (respMap.get("main").toString());

			return mainMap.get("temp").toString();

		} catch (IOException e){
			System.out.println(e.getMessage());
			return "Temp not available (API problem?)";
		}
	}

	public static void main(String[] args) {
			//issue 3, Changed name from "owm" to API_KEY to better represent what the variable is for
			//issue 2, separated the single variable declaration into two separate declarations for each variable
		String API_KEY = " ";		// Include the API key here
		String LOCATION = "Castle Rock, US";


			//issue 1, commenting out to reduce the scope of this variable. Did not delete in case of future use.
		 //String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + LOCATION + "&appid=" + API_KEY + "&units=imperial";


			//Issue 5, semicolon was used immediately following the "for" statement.
			//Issue 4, added braces to the body of the "for" to create a code block
		/*
		for (int i=0;i<10;i++){
		System.out.println("Current temperature in " + LOCATION +" is: "
				+ getTempForCity(LOCATION,API_KEY) + " degrees.");
				}

		 */

				//removed for loop. Seems unnecessary for it prints the same information ten times.
			System.out.println("Current temperature in " + LOCATION + " is: " + getTempForCity(LOCATION, API_KEY) + " degrees.");


				//issue 7, proper closing of a program.
			Runtime.getRuntime().exit(0);

	}

}
