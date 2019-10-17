package home;

import it.rambow.master.javautils.Trackpoint;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

public class BucharestTransportRestClient {

    public static void main(String[] args) {
        String bucLines = callBucharest();
        JSONParser parser = new JSONParser();
        try{
            Object obj = parser.parse(bucLines);

            JSONObject jsonObject = (JSONObject) obj;

            JSONArray linesList = (JSONArray) jsonObject.get("lines");
            Iterator<JSONObject> iterator = linesList.iterator();

            while (iterator.hasNext()) {
                Thread.sleep(3000);
                JSONObject innerObj = iterator.next();

                Object id = innerObj.get("id");
                Object name = innerObj.get("name");

                try{
                    String lineAsStr = makeARestCall("https://info.stbsa.ro/rp/api/lines/"+id);
                    storeToFile("D:\\bucharest\\"+name+".json", lineAsStr);
                }catch(Exception ex1){
                    ex1.printStackTrace();
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
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

    private static String callBucharest() {
        String bucurestiLines = "";
        try {
            bucurestiLines = makeARestCall("https://info.stbsa.ro/rp/api/lines");
            storeToFile("D:\\bucharest\\allLines.json", bucurestiLines);

        } catch (Exception e) {
            System.out.println("Exception in NetClientGet:- " + e);
        }
        return bucurestiLines;
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
