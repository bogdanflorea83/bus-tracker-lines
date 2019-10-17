package home;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.scoutant.polyline.Point;
import org.scoutant.polyline.PolylineDecoder;

import java.util.List;

public class DecodeSimplePolyline {

    protected static final PolylineDecoder decoder = new PolylineDecoder();

    public static void main(String[] args) {
        JSONObject newRouteObj = new JSONObject();
        JSONObject newReverseRouteObj = new JSONObject();



        JSONArray arr = new JSONArray();
        JSONArray reversearr = new JSONArray();

        arr.addAll(calculatePolylineForOneLine("sdw~Gwb_gDbCaB`@[v@k@k@}BS}@]mA_AgE{@aD}@sDu@{CnLkf@E{@Mc@gBuASm@OcAIuCEeI?{W}C{[cF}j@c@uD]i@c@]wBsAe@a@Y]YiAIe@Cw@Hk@jCkIh@yAx@kB|B_EnDiIrF}MdAaGrDeMnDgHvEyIN]`CiKj@qAb@m@zEsFaCwBqAo@cLaDuCeA_j@cc@"));
        newRouteObj.put("routeWayPoints", arr);
        System.out.println(newRouteObj + ",");

        for(int i=arr.size()-1; i>=0;i--){
            reversearr.add(arr.get(i));
        }
        newReverseRouteObj.put("routeWayPoints", reversearr);
        System.out.println("reverse");
        System.out.println(newReverseRouteObj + ",");


    }

    private static List<Point> calculatePolylineForOneLine(String polyline) {
        List<Point> line = decoder.decode(polyline);
        return line;
    }


}
