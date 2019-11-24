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
                "\"routeWayPoints\": [{\"lng\":26.1117732,\"lat\":44.479617},{\"lng\":26.104112,\"lat\":44.4804389},{\"lng\":26.1078269,\"lat\":44.4826886},{\"lng\":26.1180846,\"lat\":44.4889943},{\"lng\":26.1229967,\"lat\":44.4910709},{\"lng\":26.1271767,\"lat\":44.4929336},{\"lng\":26.1304431,\"lat\":44.4954384},{\"lng\":26.1343556,\"lat\":44.4993454},{\"lng\":26.1358107,\"lat\":44.5027699},{\"lng\":26.136758333333333,\"lat\":44.505028333333335},{\"lng\":26.1374758,\"lat\":44.5067486},{\"lng\":26.139111,\"lat\":44.5104962},{\"lng\":26.1403075,\"lat\":44.5133189},{\"lng\":26.141656,\"lat\":44.5164832},{\"lng\":26.1431431,\"lat\":44.5204259},{\"lng\":26.1430789,\"lat\":44.5292441},{\"lng\":26.1425361,\"lat\":44.5397732},{\"lng\":26.1424427,\"lat\":44.5418658},{\"lng\":26.1420732,\"lat\":44.5450829},{\"lng\":26.1421239,\"lat\":44.5501727}]" +
                "  " +
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
