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
                "\"routeWayPoints\": [{\"lat\": 47.14593, \"lng\": 27.52577, \"geohash\": \"u87s4qesuy\"},{\"lat\": 47.14587, \"lng\": 27.52581, \"geohash\": \"u87s4qestg\"},{\"lat\": 47.1459, \"lng\": 27.52572, \"geohash\": \"u87s4qesgb\"},{\"lat\": 47.14592, \"lng\": 27.5258, \"geohash\": \"u87s4qesvs\"},{\"lat\": 47.14588, \"lng\": 27.52584, \"geohash\": \"u87s4qeswt\"},{\"lat\": 47.1459, \"lng\": 27.52573, \"geohash\": \"u87s4qesgb\"},{\"lat\": 47.14588, \"lng\": 27.5257, \"geohash\": \"u87s4qesem\"},{\"lat\": 47.14603, \"lng\": 27.52675, \"geohash\": \"u87s4qsmeb\"},{\"lat\": 47.14658, \"lng\": 27.53009, \"geohash\": \"u87s4qyb9x\"},{\"lat\": 47.1466, \"lng\": 27.53064, \"geohash\": \"u87s4qz0y7\"},{\"lat\": 47.14646, \"lng\": 27.53126, \"geohash\": \"u87s4qz8hc\"},{\"lat\": 47.14593, \"lng\": 27.53281, \"geohash\": \"u87s4w8uby\"},{\"lat\": 47.14426, \"lng\": 27.53769, \"geohash\": \"u87s4wk74x\"},{\"lat\": 47.14429, \"lng\": 27.53799, \"geohash\": \"u87s4wke3s\"},{\"lat\": 47.14436, \"lng\": 27.53817, \"geohash\": \"u87s4wkevc\"},{\"lat\": 47.14488, \"lng\": 27.5386, \"geohash\": \"u87s4wkyzf\"},{\"lat\": 47.14498, \"lng\": 27.53883, \"geohash\": \"u87s4wmpmj\"},{\"lat\": 47.14506, \"lng\": 27.53917, \"geohash\": \"u87s4wmrvh\"},{\"lat\": 47.14511, \"lng\": 27.53992, \"geohash\": \"u87s4wtbnt\"},{\"lat\": 47.14514, \"lng\": 27.54155, \"geohash\": \"u87s4wx0ke\"},{\"lat\": 47.14513, \"lng\": 27.54394, \"geohash\": \"u87s4y8bk3\"},{\"lat\": 47.14514, \"lng\": 27.54553, \"geohash\": \"u87s4yd037\"},{\"lat\": 47.14538, \"lng\": 27.54691, \"geohash\": \"u87s4ye19x\"},{\"lat\": 47.14593, \"lng\": 27.55015, \"geohash\": \"u87s4ytkvn\"},{\"lat\": 47.14707, \"lng\": 27.55718, \"geohash\": \"u87s5nfe8f\"},{\"lat\": 47.14725, \"lng\": 27.55809, \"geohash\": \"u87s5nghwh\"},{\"lat\": 47.1474, \"lng\": 27.5583, \"geohash\": \"u87s5ngmdb\"},{\"lat\": 47.14758, \"lng\": 27.55845, \"geohash\": \"u87s5ngqw3\"},{\"lat\": 47.14818, \"lng\": 27.55887, \"geohash\": \"u87s5p5f01\"},{\"lat\": 47.14837, \"lng\": 27.55904, \"geohash\": \"u87s5p5ghj\"},{\"lat\": 47.1485, \"lng\": 27.55919, \"geohash\": \"u87s5p5gzt\"},{\"lat\": 47.14863, \"lng\": 27.55956, \"geohash\": \"u87s5phk8m\"},{\"lat\": 47.14868, \"lng\": 27.55975, \"geohash\": \"u87s5phkuw\"},{\"lat\": 47.1487, \"lng\": 27.56003, \"geohash\": \"u87s5pht54\"},{\"lat\": 47.14865, \"lng\": 27.56025, \"geohash\": \"u87s5phub3\"},{\"lat\": 47.14795, \"lng\": 27.56191, \"geohash\": \"u87s5pjbxn\"},{\"lat\": 47.14774, \"lng\": 27.56236, \"geohash\": \"u87s5nyr3x\"},{\"lat\": 47.14745, \"lng\": 27.5629, \"geohash\": \"u87s5nyty1\"},{\"lat\": 47.14682, \"lng\": 27.56386, \"geohash\": \"u87s5nz6hk\"},{\"lat\": 47.14594, \"lng\": 27.56551, \"geohash\": \"u87s5q8t4b\"},{\"lat\": 47.14472, \"lng\": 27.5679, \"geohash\": \"u87s5q6mfs\"},{\"lat\": 47.14437, \"lng\": 27.56919, \"geohash\": \"u87s5q77be\"},{\"lat\": 47.14347, \"lng\": 27.57146, \"geohash\": \"u87s5qhyte\"},{\"lat\": 47.14259, \"lng\": 27.57294, \"geohash\": \"u87s5qn12p\"},{\"lat\": 47.14151, \"lng\": 27.57467, \"geohash\": \"u87s5mz70q\"},{\"lat\": 47.14143, \"lng\": 27.57482, \"geohash\": \"u87s5mz6ez\"},{\"lat\": 47.14078, \"lng\": 27.57679, \"geohash\": \"u87s5t8ycy\"},{\"lat\": 47.14056, \"lng\": 27.5772, \"geohash\": \"u87s5t9jem\"},{\"lat\": 47.14038, \"lng\": 27.57743, \"geohash\": \"u87s5t9k8e\"},{\"lat\": 47.13937, \"lng\": 27.57857, \"geohash\": \"u87s5t6ner\"},{\"lat\": 47.13993, \"lng\": 27.57916, \"geohash\": \"u87s5td9cp\"},{\"lat\": 47.1403, \"lng\": 27.57936, \"geohash\": \"u87s5tdsjs\"},{\"lat\": 47.14246, \"lng\": 27.58021, \"geohash\": \"u87s5w529r\"},{\"lat\": 47.14334, \"lng\": 27.58063, \"geohash\": \"u87s5w5tg7\"},{\"lat\": 47.1509, \"lng\": 27.58698, \"geohash\": \"u87s5z81zh\"},{\"lat\": 47.15134, \"lng\": 27.58697, \"geohash\": \"u87s5z8hqy\"},{\"lat\": 47.15151, \"lng\": 27.58731, \"geohash\": \"u87s5z8mqy\"},{\"lat\": 47.15149, \"lng\": 27.58759, \"geohash\": \"u87s5z8tm6\"},{\"lat\": 47.1513, \"lng\": 27.58788, \"geohash\": \"u87s5z8uhp\"},{\"lat\": 47.15102, \"lng\": 27.58793, \"geohash\": \"u87s5z8ft7\"},{\"lat\": 47.1509, \"lng\": 27.58774, \"geohash\": \"u87s5z8cbu\"},{\"lat\": 47.15088, \"lng\": 27.58739, \"geohash\": \"u87s5z89bc\"},{\"lat\": 47.1509, \"lng\": 27.58712, \"geohash\": \"u87s5z83fk\"},{\"lat\": 47.14964, \"lng\": 27.58605, \"geohash\": \"u87s5xrd99\"}]" +
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
