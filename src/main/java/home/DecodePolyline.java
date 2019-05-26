package home;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.scoutant.polyline.Point;
import org.scoutant.polyline.PolylineDecoder;

import it.rambow.master.javautils.PolylineEncoder;

public class DecodePolyline {
	
	protected static final PolylineDecoder decoder = new PolylineDecoder();
	protected static final PolylineEncoder encoder = new PolylineEncoder();

	
	private static String getDeviceType(String city, String line, int deviceType) {
		if(city.equalsIgnoreCase("iasi")) {
			if(line.equalsIgnoreCase("5")) {
				return "B";
			}
			if(line.equalsIgnoreCase("11")) {
				return "T";
			}
		}
		switch (deviceType) {
		case 0:
			return "T";
			
		case 1:
			return "M";
			
		case 2:
			return "TR";
		case 3:
			return "B";
			
		default:
			break;
		}
		return "B";
	}
	
	private static String getColorForLine(String line) {
		// only for iasi
		if("1".equalsIgnoreCase(line)) {
			return "ec008c";
		}else if("3".equalsIgnoreCase(line)) {
			return "00a650";
		}else if("6".equalsIgnoreCase(line)) {
			return "f9c0c1";
		}else if("7".equalsIgnoreCase(line)) {
			return "2e3092";
		}else if("8".equalsIgnoreCase(line)) {
			return "d2e288";
		}else if("9".equalsIgnoreCase(line)) {
			return "4ea391";
		}else if("11".equalsIgnoreCase(line)) {
			return "f05b72";
		}else if("13".equalsIgnoreCase(line)) {
			return "00d1ff";
		}else {
			return "8f4099";
		}
	}
	
	private static String getCityAcronim(String city) {
		if("alba".equalsIgnoreCase(city)) {
			return "AB";
		}else if("arad".equalsIgnoreCase(city)) {
			return "AR";
		}else if("brasov".equalsIgnoreCase(city)) {
			return "BV";
		}else if("bucuresti".equalsIgnoreCase(city)) {
			return "B";
		}else if("cluj".equalsIgnoreCase(city)) {
			return "CJ";
		}else if("constanta".equalsIgnoreCase(city)) {
			return "CT";
		}else if("craiova".equalsIgnoreCase(city)) {
			return "CV";
		}else if("iasi".equalsIgnoreCase(city)) {
			return "IS";
		}else if("oradea".equalsIgnoreCase(city)) {
			return "OR";
		}else if("ploiesti".equalsIgnoreCase(city)) {
			return "PL";
		}else if("sibiu".equalsIgnoreCase(city)) {
			return "SB";
		}else if("timisoara".equalsIgnoreCase(city)) {
			return "TM";
		}else {
			return "IS";
		}
	}
	
	private static String getLineNumber(String city, String line) {
		if("iasi".equalsIgnoreCase(city)) {
			if("6B".equalsIgnoreCase(line)) {
				return "36";
			}else if("41B".equalsIgnoreCase(line)) {
				return "9";
			}else if("121".equalsIgnoreCase(line)) {
				return "47";
			}
		}
		return line;
	}

	
	public static void processCityForPolyline(String city) {
		///
				JSONParser parser = new JSONParser();
				
		        try {
		            String pathPrefix = "D:\\workspace_transport_geofire\\home\\src\\main\\java\\home\\";
					Object obj = parser.parse(new FileReader(
		                    pathPrefix+city+"_stations.json"));
		            
		            File directory = new File(pathPrefix+city);

		            if(!directory.exists()){

		                        directory.mkdir();
		                       
		           }
		            
		            
				    BufferedWriter writer = new BufferedWriter(new FileWriter(pathPrefix+city+"\\allLines.json"));

				    JSONObject jsonObject = (JSONObject) obj;
		            
		            JSONObject output = new JSONObject();
		            
		            JSONArray outputJSONArray = new JSONArray();
		 
		           
		            JSONArray companyList = (JSONArray) jsonObject.get(city+"_lines");
		            
		            System.out.println("\nLines List:" + city);
		            Iterator<JSONObject> iterator = companyList.iterator();
		            while (iterator.hasNext()) {
		            	JSONObject innerObj = iterator.next();
		            	JSONObject newInnerObj = new JSONObject();
		            	
		            	Object lineNumberObj = innerObj.get("short_name");
		            	String lineNumber = getLineNumber(city, (String) lineNumberObj);
		            	
		            	
						newInnerObj.put("lineNumber", lineNumber);
		            	String deviceType = getDeviceType(city, lineNumber, Integer.valueOf(innerObj.get("type").toString()));
						newInnerObj.put("deviceType", deviceType);
		            	newInnerObj.put("name", innerObj.get("name"));
		                
		                JSONObject stationsObject = (JSONObject) parser.parse((String) innerObj.get("stations"));
		                JSONArray jsonArray = (JSONArray) stationsObject.get("data");
		                
		                boolean onlyOneRoute = !(jsonArray.size()>1);
		                newInnerObj.put("onlyOneRoute", onlyOneRoute);
		                
		                if(city.equalsIgnoreCase("iasi")) {
		        			newInnerObj.put("color", getColorForLine((String) lineNumber));
		        		}else {
		        			newInnerObj.put("color", "8f4099");
		        		}
		            	
		                newInnerObj.put("routeForward", extractRoute(city, lineNumber, deviceType, jsonArray, (JSONObject) jsonArray.get(0), "F"));   
		                if(!("alba" == city)) {
		                	if(jsonArray.size()>1) {
				                newInnerObj.put("routeBackward", extractRoute(city, lineNumber, deviceType, jsonArray, (JSONObject) jsonArray.get(1), "B"));     
			                }
		                }else {
		                	newInnerObj.put("onlyOneRoute", true);
		                }
		                
		                outputJSONArray.add(newInnerObj);     
//		                break;          
		            }
		            
//		            output.put(city+"_lines", outputJSONArray);
		            output.put("allLines", outputJSONArray);
				    writer.write(output.toJSONString());
		            
		            writer.close();
		 
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
	}

	private static JSONObject extractRoute(String city, Object lineNumber,
			String deviceType, JSONArray jsonArray, JSONObject route, String moveDirection) {
		
		
		
		JSONObject newRouteObj = new JSONObject();
		newRouteObj.put("duration", (String) route.get("duration"));
		newRouteObj.put("name", (String) route.get("name"));
		newRouteObj.put("moveDirection", moveDirection);
		String lineName = deviceType+"_"+lineNumber+"_"+moveDirection;
		newRouteObj.put("routeName", lineName);
		
		JSONArray stopsArr = (JSONArray) route.get("stops");
		JSONArray stationsArr = new JSONArray();
		for(int i=0; i<stopsArr.size(); i++) {
			JSONObject stop = (JSONObject) stopsArr.get(i);
			JSONObject stationObj = new JSONObject();
			Object lat = stop.get("latitude");
			Object lng = stop.get("longitude");
			double latDoubleValue = Double.valueOf(lat.toString()).doubleValue();
			double lngDoubleValue = Double.valueOf(lng.toString()).doubleValue();
			stationObj.put("lat", latDoubleValue);
			stationObj.put("lng", lngDoubleValue);
			stationObj.put("stationName", stop.get("name"));			
			stationObj.put("geohash", GeoHashUtils.encode(latDoubleValue, lngDoubleValue, 10));
			stationObj.put("stationId", getCityAcronim(city)+"_"+lineName+"_"+i);
			stationsArr.add(stationObj);
			
		}
		newRouteObj.put("stations", stationsArr);
		
		String polyline = (String) route.get("geometry");
//		newRouteObj.put("geometry", polyline);

		JSONArray arr = new JSONArray();
		arr.addAll(calculatePolylineForOneLine(polyline));
		newRouteObj.put("routeWayPoints", arr);
		
		return newRouteObj;
	}

	
	
	private static List<Point> calculatePolylineForOneLine(String polyline) {
		List<Point> line = decoder.decode(polyline);		
		return line;
	}
	
	public static void main(String[] args) {
		String geometry = "gg|~GevegD??tBqGjBeFh@kAlAiD??hAcDbBcEjE}J??bDqH~CwGpAgDXc@h@i@t@[zDkB??`LoFhAa@`@Gx@C~CVJ@~AN~@?l@OJG??^Yh@w@pCeE^e@b@UXKTCbABl@F~A|@vDfBd@T??lAf@~Bj@xBFdBS`AW??`Bm@z@k@xBaCbA_BpHgL??~E}H`F_J??zEaJvA}Er@sCJ{@Dy@D{L??@aEAqCWoCGk@[oAUu@Ws@u@qAuBkD~AoB??~EeGxC_EzFiIzAiC??nIwNzD_HNW??vc@mu@^RjC_@~AeBvC_DdAe@n@@nD|IzDfKx@bERjC`@dA`A`ArBzBhCdCf@TxAJbAb@tAzAjJpIfDzBbHrBxLb@d@YTgCr@kAbMqChN}CxBe@rAOt@AzCq@AkBN_BVkAG_EvCmMtAeBhCa@lARCW";
		JSONArray arr = new JSONArray();
		arr.addAll(calculatePolylineForOneLine(geometry));
		System.out.println(arr);
	}
	
//	private static List<Point> encodePolylineForOneLine(String polyline) {
//		List<Point> line = encoder.
//		return line;
//	}

}
