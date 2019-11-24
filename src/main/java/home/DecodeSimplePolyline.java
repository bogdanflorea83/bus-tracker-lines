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

        arr.addAll(calculatePolylineForOneLine("qe|nGg{p~Cx^HbSiA`LQx`AkBbv@MrWhHld@zNlVfIzWjIjTbHlWlWrNlSrJbY~Kt]lf@b_A`MdVbD{n@"));
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
