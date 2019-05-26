package home;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class TransportRestClient {
	
	public static void processCity(String city) {
		///
		JSONParser parser = new JSONParser();
		
        try {
            Object obj = parser.parse(new FileReader(
                    "D:\\workspace_transport_geofire\\home\\src\\main\\java\\home\\"+city+".json"));
		    BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\workspace_transport_geofire\\home\\src\\main\\java\\home\\"+city+"_stations.json"));

 
            JSONObject jsonObject = (JSONObject) obj;
            
            JSONObject output = new JSONObject();
            
            JSONArray outputJSONArray = new JSONArray();
 
           
            JSONArray companyList = (JSONArray) jsonObject.get("data");
            
            System.out.println("\nLines List:" + city);
            Iterator<JSONObject> iterator = companyList.iterator();
            while (iterator.hasNext()) {
            	JSONObject innerObj = iterator.next();
            	String id = (String) innerObj.get("id");
                String short_name = (String) innerObj.get("short_name");
                String name = (String) innerObj.get("name");
                String type = (String) innerObj.get("type");
                String boolNum = (String) innerObj.get("boolNum");
                System.out.println("stations :" + short_name);
                innerObj.put("stations", callForOneLine(city, short_name));                 
                outputJSONArray.add(innerObj);  
            }
            
            output.put(city+"_lines", outputJSONArray);
		    writer.write(output.toJSONString());
            
            writer.close();
 
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	private static String callForOneLine(String city, String shortName) {
		StringBuffer sb = new StringBuffer();
		try {
			//bucuresti, 1
            URL url = new URL("http://www.transporturban.ro/api/lines-trips-get.php?router="+city+"&line="+shortName);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            
            String output;
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }
            conn.disconnect();
            
        } catch (Exception e) {
            System.out.println("Exception in NetClientGet:- " + e);
        }
		return sb.toString();
	}

}
