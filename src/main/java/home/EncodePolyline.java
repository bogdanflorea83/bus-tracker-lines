package home;

import it.rambow.master.javautils.PolylineEncoder;
import it.rambow.master.javautils.Track;
import it.rambow.master.javautils.Trackpoint;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

public class EncodePolyline {

    protected static final PolylineEncoder encoder = new PolylineEncoder();

    public static void main(String[] args) {

    encode();

    }

    public static void encode() {
        ///
        JSONParser parser = new JSONParser();
        String routePolylineAsJson = "{" +
                "\"routeWayPoints\": []" +
                     "}" ;
        Track track = new Track();
        Track reversetrack = new Track();

        try {
            Object obj = parser.parse(routePolylineAsJson);

            JSONObject jsonObject = (JSONObject) obj;

            JSONArray companyList = (JSONArray) jsonObject.get("routeWayPoints");

            Iterator<JSONObject> iterator = companyList.iterator();

            while (iterator.hasNext()) {
                JSONObject innerObj = iterator.next();
                JSONObject newInnerObj = new JSONObject();

                Object lat = innerObj.get("lat");
                Object lng = innerObj.get("lng");

                track.addTrackpoint(new Trackpoint(Double.valueOf(lat.toString()).doubleValue(),Double.valueOf(lng.toString()).doubleValue()));
            }
            for (int i = track.getTrackpoints().size()-1; i>=0; i--)
                reversetrack.addTrackpoint(track.getTrackpoints().get(i));

            Map result = encoder.dpEncode(track);

            System.out.println(result);
            System.out.println("reverse");
            result = encoder.dpEncode(reversetrack);
            System.out.println(result);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
