package home;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ReplaceRouteNameForAlba {
	
	public static void main(String[] args) {
		processCityForPolyline();
	}
	
	public static void processCityForPolyline() {
		///
				JSONParser parser = new JSONParser();
				
		        try {
		            String pathPrefix = "D:\\workspace_transport_geofire\\home\\src\\main\\java\\home\\";
					Object obj = parser.parse(new FileReader(
		                    pathPrefix+"allLines.json"));
		            
		            File directory = new File(pathPrefix+"alba_2");

		            if(!directory.exists()){

		                        directory.mkdir();
		                       
		           }
		            
		            
				    BufferedWriter writer = new BufferedWriter(new FileWriter(pathPrefix+"alba_2"+"\\allLines.json"));

				    JSONObject jsonObject = (JSONObject) obj;
		            
		            JSONObject output = new JSONObject();
		            
		            JSONArray outputJSONArray = new JSONArray();
		 
		           
		            JSONArray companyList = (JSONArray) jsonObject.get("allLines");
		            
		            System.out.println("\nLines List: alba");
		            Iterator<JSONObject> iterator = companyList.iterator();
		            while (iterator.hasNext()) {
		            	JSONObject innerObj = iterator.next();
		            	JSONObject routeForeward = (JSONObject) innerObj.get("routeForward");
		                JSONArray jsonArray = (JSONArray) routeForeward.get("stations");
		                routeForeward.put("name", ((JSONObject) jsonArray.get(0)).get("stationName") + " - " + ((JSONObject) jsonArray.get(jsonArray.size()-1)).get("stationName"));
		                
		                outputJSONArray.add(innerObj);     
		            }
		            
		            output.put("allLines", outputJSONArray);
				    writer.write(output.toJSONString());
		            
		            writer.close();
		 
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
	}

}
