package home;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class TestDistanceBetweenWayPoints {
	
	public static void main(String[] args) {
		List<String> cities = new ArrayList<String>();
//		cities.add("alba");
//		cities.add("arad");
//		cities.add("brasov");
//		cities.add("bucuresti");
//		cities.add("cluj");
//		cities.add("constanta");
		cities.add("iasi");
//		cities.add("oradea");
//		cities.add("ploiesti");
//		cities.add("sibiu");
//		cities.add("timisoara");
//		cities.add("craiova");
		
		for(String city : cities) {
//			TransportRestClient.processCity(city);
			System.out.println(city + "------------");
			processCityForPolyline(city);
		}
	}
	
	
	
	public static void processCityForPolyline(String city) {
		///
				JSONParser parser = new JSONParser();
				
		        try {
		            String pathPrefix = "D:\\workspace_transport_geofire\\home\\src\\main\\java\\home\\";
					Object obj = parser.parse(new FileReader(
		                    pathPrefix+city+"\\allLines.json"));
					
					 BufferedWriter writer = new BufferedWriter(new FileWriter(pathPrefix+city+"\\allLines2.json"));
		            
		           

				    JSONObject jsonObject = (JSONObject) obj;
		            
		            JSONArray companyList = (JSONArray) jsonObject.get("allLines");
		            
		            System.out.println("\nLines List:" + city);
		            Iterator<JSONObject> iterator = companyList.iterator();
		            while (iterator.hasNext()) {
		            	JSONObject innerObj = iterator.next();
		            	
		            	Object lineNumberObj = innerObj.get("lineNumber");
		           		                
		                JSONObject routeObject = (JSONObject) innerObj.get("routeForward");
		                JSONArray jsonArray = (JSONArray) routeObject.get("routeWayPoints");
		             	System.out.println(lineNumberObj + "----" + "routeForward");
		                checkForRoute(jsonArray);
		                
		                routeObject = (JSONObject) innerObj.get("routeBackward");		                
		                if(routeObject != null) {
		                	jsonArray = (JSONArray) routeObject.get("routeWayPoints");
		                	 System.out.println(lineNumberObj + "----" + "routeBackward");
		                	 checkForRoute(jsonArray);
		                }
 
		            }
		            byte[] ptext = jsonObject.toJSONString().getBytes("UTF-8");
		            String value = new String(ptext, "UTF-8");
				    writer.write(value);
		            
		            writer.close();
		            		 
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
	}

	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}

	private static void checkForRoute(JSONArray jsonArray) {
		double totaldistance = 0;
		int contor = 0;
		for(int i=0; i< jsonArray.size(); i++) {
			if(i==0) {
				JSONObject currentWayPoint = (JSONObject) jsonArray.get(i);
				currentWayPoint.put("distance", Double.valueOf(0));
				continue;
			}
			JSONObject previousWaypoint = (JSONObject) jsonArray.get(i-1);
			JSONObject currentWaypoint = (JSONObject) jsonArray.get(i);
			
			double platDoubleValue = Double.valueOf(previousWaypoint.get("lat").toString()).doubleValue();
			double plngDoubleValue = Double.valueOf(previousWaypoint.get("lng").toString()).doubleValue();
			
			double clatDoubleValue = Double.valueOf(currentWaypoint.get("lat").toString()).doubleValue();
			double clngDoubleValue = Double.valueOf(currentWaypoint.get("lng").toString()).doubleValue();
			
			double distance = distance(platDoubleValue, plngDoubleValue, clatDoubleValue, clngDoubleValue, 'K');
//			System.out.println("dist:"+ distance + "     " + previousWaypoint.toJSONString());
			currentWaypoint.put("distance", round( distance, 3));
			
			totaldistance += distance;
			contor++;
			if(distance > 0.200) {
				System.out.println("Distanta mare: " + distance);
			}
			
		}
		System.out.println("Number of waypoints = " +contor+ " Average distance = " + totaldistance/contor );
	}
	
	private static double distance(double lat1, double lon1, double lat2, double lon2, char unit) {
		if(lat1==lat2 && lon1 == lon2) {
			return 0;
		}
	      double theta = lon1 - lon2;
	      double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
	      dist = Math.acos(dist);
	      dist = rad2deg(dist);
	      dist = dist * 60 * 1.1515;
	      if (unit == 'K') {
	        dist = dist * 1.609344;
	      } else if (unit == 'N') {
	        dist = dist * 0.8684;
	        }
	      return (dist);
	    }

	    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	    /*::  This function converts decimal degrees to radians             :*/
	    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	    private static double deg2rad(double deg) {
	      return (deg * Math.PI / 180.0);
	    }

	    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	    /*::  This function converts radians to decimal degrees             :*/
	    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	    private static double rad2deg(double rad) {
	      return (rad * 180.0 / Math.PI);
	    }

}
