package home;

/*
According to apache license

This is fork of christocracy cordova-plugin-background-geolocation plugin
https://github.com/christocracy/cordova-plugin-background-geolocation

This is a new class
*/

import java.util.HashMap;
import java.util.Iterator;

import org.json.simple.JSONObject;

/**
 * Config class
 */
public class BackgroundLocationConfig
{
    public static final int ANDROID_DISTANCE_FILTER_PROVIDER = 0;
    public static final int ANDROID_ACTIVITY_PROVIDER = 1;

    // actual values should be read from strings.xml
    public static final String ACCOUNT_NAME_RESOURCE = "account_name";
    public static final String ACCOUNT_TYPE_RESOURCE = "account_type";
    public static final String CONTENT_AUTHORITY_RESOURCE = "content_authority";

    private float stationaryRadius = 50;
    private Integer distanceFilter = 500;
    private Integer desiredAccuracy = 100;
    private Boolean debug = false;
    private String notificationTitle = "Background tracking";
    private String notificationText = "ENABLED";
    private String notificationIconLarge;
    private String notificationIconSmall;
    private String notificationIconColor;
    private Integer locationProvider = ANDROID_DISTANCE_FILTER_PROVIDER;
    private Integer interval = 600000; //milliseconds
    private Integer fastestInterval = 120000; //milliseconds
    private Integer activitiesInterval = 10000; //milliseconds
    private Boolean stopOnTerminate = true;
    private Boolean startOnBoot = false;
    private Boolean startForeground = true;
    private Boolean stopOnStillActivity = true;
    private String url;
    private String syncUrl;
    private Integer syncThreshold = 100;
    private HashMap httpHeaders = new HashMap<String, String>();
    private Integer maxLocations = 10000;

    public BackgroundLocationConfig () {
    }

    public int describeContents() {
        return 0;
    }


    public float getStationaryRadius() {
        return stationaryRadius;
    }

    public void setStationaryRadius(float stationaryRadius) {
        this.stationaryRadius = stationaryRadius;
    }

    public Integer getDesiredAccuracy() {
        return desiredAccuracy;
    }

    public void setDesiredAccuracy(Integer desiredAccuracy) {
        this.desiredAccuracy = desiredAccuracy;
    }

    public Integer getDistanceFilter() {
        return distanceFilter;
    }

    public void setDistanceFilter(Integer distanceFilter) {
        this.distanceFilter = distanceFilter;
    }

    public Boolean isDebugging() {
        return debug;
    }

    public void setDebugging(Boolean debug) {
        this.debug = debug;
    }

    public String getNotificationIconColor() {
        return notificationIconColor;
    }

    public void setNotificationIconColor(String notificationIconColor) {
        if (!"null".equals(notificationIconColor)) {
            this.notificationIconColor = notificationIconColor;
        }
    }

    public String getNotificationTitle() {
        return notificationTitle;
    }

    public void setNotificationTitle(String notificationTitle) {
        this.notificationTitle = notificationTitle;
    }

    public String getNotificationText() {
        return notificationText;
    }

    public void setNotificationText(String notificationText) {
        this.notificationText = notificationText;
    }

    public String getLargeNotificationIcon () {
        return notificationIconLarge;
    }

    public void setLargeNotificationIcon (String icon) {
        this.notificationIconLarge = icon;
    }

    public String getSmallNotificationIcon () {
        return notificationIconSmall;
    }

    public void setSmallNotificationIcon (String icon) {
        this.notificationIconSmall = icon;
    }

    public Boolean getStopOnTerminate() {
        return stopOnTerminate;
    }

    public void setStopOnTerminate(Boolean stopOnTerminate) {
        this.stopOnTerminate = stopOnTerminate;
    }

    public Boolean getStartOnBoot() {
        return startOnBoot;
    }

    public void setStartOnBoot(Boolean startOnBoot) {
        this.startOnBoot = startOnBoot;
    }

    public Boolean getStartForeground() {
        return this.startForeground;
    }

    public void setStartForeground(Boolean startForeground) {
        this.startForeground = startForeground;
    }

    public Integer getLocationProvider() {
        return this.locationProvider;
    }

    public void setLocationProvider(Integer locationProvider) {
        this.locationProvider = locationProvider;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public Integer getFastestInterval() {
        return fastestInterval;
    }

    public void setFastestInterval(Integer fastestInterval) {
        this.fastestInterval = fastestInterval;
    }

    public Integer getActivitiesInterval() {
        return activitiesInterval;
    }

    public void setActivitiesInterval(Integer activitiesInterval) {
        this.activitiesInterval = activitiesInterval;
    }

    public Boolean getStopOnStillActivity() {
        return stopOnStillActivity;
    }

    public void setStopOnStillActivity(Boolean stopOnStillActivity) {
        this.stopOnStillActivity = stopOnStillActivity;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean hasUrl() {
        return this.url != null && !this.url.isEmpty();
    }

    public String getSyncUrl() {
        return syncUrl;
    }

    public void setSyncUrl(String syncUrl) {
        this.syncUrl = syncUrl;
    }

    public Boolean hasSyncUrl() {
        return this.syncUrl != null && !this.syncUrl.isEmpty();
    }

    public Integer getSyncThreshold() {
        return syncThreshold;
    }

    public void setSyncThreshold(Integer syncThreshold) {
        this.syncThreshold = syncThreshold;
    }

    public HashMap<String, String> getHttpHeaders() {
        return this.httpHeaders;
    }

    public void setHttpHeaders(HashMap httpHeaders) {
        this.httpHeaders = httpHeaders;
    }

    public Integer getMaxLocations() {
        return maxLocations;
    }

    public void setMaxLocations(Integer maxLocations) {
        this.maxLocations = maxLocations;
    }

    @Override
    public String toString () {
        return new StringBuffer()
                .append("Config[distanceFilter=").append(getDistanceFilter())
                .append(" stationaryRadius=").append(getStationaryRadius())
                .append(" desiredAccuracy=").append(getDesiredAccuracy())
                .append(" interval=").append(getInterval())
                .append(" fastestInterval=").append(getFastestInterval())
                .append(" activitiesInterval=").append(getActivitiesInterval())
                .append(" isDebugging=").append(isDebugging())
                .append(" stopOnTerminate=" ).append(getStopOnTerminate())
                .append(" stopOnStillActivity=").append(getStopOnStillActivity())
                .append(" startOnBoot=").append(getStartOnBoot())
                .append(" startForeground=").append(getStartForeground())
                .append(" locationProvider=").append(getLocationProvider())
                .append(" nTitle=").append(getNotificationTitle())
                .append(" nText=").append(getNotificationText())
                .append(" nIconLarge=").append(getLargeNotificationIcon())
                .append(" nIconSmall=").append(getSmallNotificationIcon())
                .append(" nIconColor=").append(getNotificationIconColor())
                .append(" url=").append(getUrl())
                .append(" syncUrl=").append(getSyncUrl())
                .append(" syncThreshold=").append(getSyncThreshold())
                .append(" httpHeaders=").append(getHttpHeaders().toString())
                .append(" maxLocations=").append(getMaxLocations())
                .append("]")
                .toString();
    }


    public JSONObject toJSONObject() {
        JSONObject json = new JSONObject();
        json.put("stationaryRadius", getStationaryRadius());
        json.put("distanceFilter", getDistanceFilter());
        json.put("desiredAccuracy", getDesiredAccuracy());
        json.put("debug", isDebugging());
        json.put("notificationTitle", getNotificationTitle());
        json.put("notificationText", getNotificationText());
        json.put("notificationIconLarge", getLargeNotificationIcon());
        json.put("notificationIconSmall", getSmallNotificationIcon());
        json.put("notificationIconColor", getNotificationIconColor());
        json.put("stopOnTerminate", getStopOnTerminate());
        json.put("startOnBoot", getStartOnBoot());
        json.put("startForeground", getStartForeground());
        json.put("locationProvider", getLocationProvider());
        json.put("interval", getInterval());
        json.put("fastestInterval", getFastestInterval());
        json.put("activitiesInterval", getActivitiesInterval());
        json.put("stopOnStillActivity", getStopOnStillActivity());
        json.put("url", getUrl());
        json.put("syncUrl", getSyncUrl());
        json.put("syncThreshold", getSyncThreshold());
        json.put("httpHeaders", new JSONObject(getHttpHeaders()));
        json.put("maxLocations", getMaxLocations());

        return json;
  	}
    
    public static void main(String[] args) {
		System.out.println(new BackgroundLocationConfig().toJSONObject());
	}
}

