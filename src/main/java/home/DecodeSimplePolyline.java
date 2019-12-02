package home;

import it.rambow.master.javautils.PolylineEncoder;
import it.rambow.master.javautils.Track;
import it.rambow.master.javautils.Trackpoint;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.scoutant.polyline.Point;
import org.scoutant.polyline.PolylineDecoder;

import java.util.List;
import java.util.Map;

public class DecodeSimplePolyline {

    protected static final PolylineDecoder decoder = new PolylineDecoder();

    protected static final PolylineEncoder encoder = new PolylineEncoder();


    public static void main(String[] args) {
        JSONObject newRouteObj = new JSONObject();
        JSONObject newReverseRouteObj = new JSONObject();



        JSONArray arr = new JSONArray();
        JSONArray reversearr = new JSONArray();

        arr.addAll(calculatePolylineForOneLine("e_xaHwgc_D?g@|@|AVh@`@fAn@fAXPb@FXQXYv@}@hBiBV[p@m@xA}A\\BP^\\lAf@jAv@tBv@lCbCzGh@hAFh@Nf@bAtBTj@d@nAPp@v@nBPj@Kd@iAzAk@lAeD`FQr@@h@Jd@lCjGnAdDTb@`@HNa@R_@zEgIb@{@tBqDVWZMR]Gi@Oe@iNqa@c@sA]oAiCgHq@{BKm@Q]][c@MdAoAgGrFo@|@o@h@{C~CoAbAs@t@W\\WT[KU[[UWYk@w@sAaCwB{EiBqDcCiE{BkEWUwEkJcCuEe@aAMe@Om@QyAIsADyCGyEGeAEoBMuACk@AcCM{E\\IXFrBLx@P~C|@fAh@`@VhBtArCjBf@VnAb@hAJdA?|@IZGdA[ZOLe@Ek@KmE?oCKsNKwHKwDEkGAyJEk@Ii@a@gAu@u@kC{A{Ao@[C}@]cFyB}@m@c@CUYE}A_@yAS][sAe@_AW_@y@u@y@k@g@Sa@KcAQa@Ce@@m@Lm@Rm@Zi@d@}B~Bk@^iCnAk@`@g@h@e@l@oBvCiApAc@^g@ZkAd@iARcB?{@OqAMcBIiAMkA_@g@Ye@]yDkDw@g@mAkAyN_M_@SSY_@_@s@g@g@SOk@P_@^?XIVUNc@XuALuAZyAlA{D`AkCNe@Ns@?i@SwAAu@Ri@^FYVXYFh@Ch@Kn@Qp@u@xBcAdDu@rC[~AIp@Er@Ax@Kn@}@hBW\\a@QQe@Y_@gB{AqAoAeCsBwB_BgA_A_As@y@i@U[YW{DgDqAaAa@_@[c@}@_AyD}EcDsEuB_DoCqDyAuBmAsBwAuBQa@cE{Hi@aAY]a@cAS_@Mc@g@iAm@w@Dm@VUl@kBTa@L_@Ra@dBcFLe@TiB?mCCo@Is@KcDCkDGaBDuA?u@Cu@Qk@a@M]Vm@r@WT[FoGNkIEcBEe@NKj@Hr@lAhGn@fCRr@Vh@j@`AH`@ARON"));
        newRouteObj.put("routeWayPoints", arr);
        System.out.println(newRouteObj + ",");

        Track reversetrack = new Track();
        for(int i=arr.size()-1; i>=0;i--){
            Point obj = (Point)arr.get(i);
            reversearr.add(obj);


            reversetrack.addTrackpoint(new Trackpoint(obj.getLat(),obj.getLng()));
        }
        newReverseRouteObj.put("routeWayPoints", reversearr);
        System.out.println("reverse");
        System.out.println(newReverseRouteObj + ",");




        Map result = encoder.dpEncode(reversetrack);
        System.out.println("reverse polyline: " + result);


    }

    private static List<Point> calculatePolylineForOneLine(String polyline) {
        List<Point> line = decoder.decode(polyline);
        return line;
    }


}
