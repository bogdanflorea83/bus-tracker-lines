package home;

import it.rambow.master.javautils.PolylineEncoder;
import it.rambow.master.javautils.Track;
import it.rambow.master.javautils.Trackpoint;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.Iterator;
import java.util.Map;

public class CityEncodePolyline {

    protected static final PolylineEncoder encoder = new PolylineEncoder();
    private static final Minify minify = new Minify();

    public static void processCityForPolyline(String city) {
        System.out.println("\nLines List:" + city);

        try {
            JSONParser parser = new JSONParser();

            String pathPrefix = new File(".").getCanonicalPath()  + "//src//main//java//home//";
            String outPathPrefix = pathPrefix+"encoded//";

            File directory = new File(outPathPrefix+city);

            if(!directory.exists()){

                directory.mkdir();

            }

            File outFile = new File(outPathPrefix+city+"//1//allLines.json");

            if(!outFile.exists()){
                outFile.createNewFile();

            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(outPathPrefix+city+"//1//allLines.json"));

            Object obj = parser.parse(new FileReader(
                    pathPrefix+city+"//allLines.json"));

            JSONObject output = new JSONObject();

            JSONArray outputJSONArray = new JSONArray();

            JSONArray linesList = (JSONArray) obj;

            Iterator<JSONObject> iterator = linesList.iterator();
            while (iterator.hasNext()) {
                JSONObject lineObject = iterator.next();

                JSONObject routeObj = (JSONObject) lineObject.get("routeForward");
                JSONArray pointsJsonArray = (JSONArray) routeObj.get("routeWayPoints");
                routeObj.put("routeWayPointsEncoded", encode(pointsJsonArray));
                routeObj.put("routeWayPoints", new JSONArray());

                JSONObject routeBackwardObj = (JSONObject) lineObject.get("routeBackward");
                if(routeBackwardObj != null){
                    JSONArray bPointsJsonArray = (JSONArray) routeBackwardObj.get("routeWayPoints");
                    routeBackwardObj.put("routeWayPointsEncoded", encode(bPointsJsonArray));
                    routeBackwardObj.put("routeWayPoints", new JSONArray());
                }
                outputJSONArray.add(lineObject);
            }

            output.put("allLines", outputJSONArray);
            //writer.write(output.toJSONString());
            writer.write(minify.minify(output.toJSONString()));

            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String encode(JSONArray pointsList) {
        Track track = new Track();

        try {
            Iterator<JSONObject> iterator = pointsList.iterator();

            while (iterator.hasNext()) {
                JSONObject innerObj = iterator.next();
                JSONObject newInnerObj = new JSONObject();

                Object lat = innerObj.get("lat");
                Object lng = innerObj.get("lng");

                track.addTrackpoint(new Trackpoint(Double.valueOf(lat.toString()).doubleValue(),Double.valueOf(lng.toString()).doubleValue()));
            }

            Map<String, String> result = encoder.dpEncode(track);
            return result.get("encodedPoints");
            //return result.get("encodedPoints").replace("\\", "\\");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
