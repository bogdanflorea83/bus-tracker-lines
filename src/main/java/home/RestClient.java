package home;

import java.util.ArrayList;
import java.util.List;

public class RestClient {
	
	
	public static void main(String[] args) {
		
		List<String> cities = new ArrayList<String>();
		cities.add("alba");
		cities.add("arad");
		cities.add("brasov");
		cities.add("bucuresti");
		cities.add("cluj");
		cities.add("constanta");
		cities.add("iasi");
		cities.add("oradea");
		cities.add("ploiesti");
		cities.add("sibiu");
		cities.add("timisoara");
		cities.add("craiova");
		System.out.println("------");
//		
		for(String city : cities) {
//			TransportRestClient.processCity(city);
			System.out.println(city);
			//DecodePolyline.processCityForPolyline(city);
			//Minify.processCityForPolyline(city);
			//Encrypt.processCityForPolyline(city);
			CityEncodePolyline.processCityForPolyline(city);
		}
    }
	
	
	
	

}
