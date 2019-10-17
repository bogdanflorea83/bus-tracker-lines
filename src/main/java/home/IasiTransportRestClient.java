package home;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

public class IasiTransportRestClient {
    public static void main(String[] args) {
        for(int i=44; i<=100; i++){
            JSONParser parser = new JSONParser();
            try{

                Thread.sleep(1000);

                try{
                    String lineAsStr = makeARestCall("https://m-go-iasi.wink.ro/apiPublic/route/byId/"+i);
                    storeToFile("D:\\iasi\\neprocesat\\"+i+".json", lineAsStr);
                    Object obj = parser.parse(lineAsStr);

                    JSONObject jsonObject = (JSONObject) obj;

                    Object route = ((JSONObject)jsonObject.get("data")).get("routeShortname");

                    storeToFile("D:\\iasi\\"+route+".json", lineAsStr);
                }catch(Exception ex1){
                    ex1.printStackTrace();
                }

            }catch(Exception ex){
                ex.printStackTrace();
            }
        }

    }

    private static String makeARestCall(String urlAsString){
        StringBuffer sb = new StringBuffer();
        try {
            //bucuresti, 1
            //"https://info.stbsa.ro/rp/api/lines"
            URL url = new URL(urlAsString);
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

    private static void storeToFile(String path, String content){
        try {

            File file = new File(path);
            file.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));

            writer.write( content);

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
