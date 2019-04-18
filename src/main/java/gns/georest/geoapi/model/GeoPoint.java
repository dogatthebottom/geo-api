package gns.georest.geoapi.model;

import javax.validation.constraints.Max;
import java.util.ArrayList;
import java.util.List;

/*
 * Class Handler for GeoPoints
 * */
public class GeoPoint {

    //fields
    private String pointName; //Name of geo point
    private String pointDesc; //Description of geo point
    private Double lat;     //Latitude
    private Double lon;     //Longitude
    private boolean sight;    // Flag for determening if is it interesting place or not
    private String hightlights;


    public String getHightlights() {
        return hightlights;
    }

    public void setHightlights(String hightlights) {
        this.hightlights = hightlights;
    }


    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "GeoPoint{" +
                "pointName='" + pointName + '\'' +
                ", pointDesc='" + pointDesc + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                ", sight=" + sight +
                ", hightlights='" + hightlights + '\'' +
                '}';
    }

    public GeoPoint() {

    }

    public GeoPoint(String pointName, String pointDesc, Double lat, Double lon, boolean sight) {
        this.pointName = pointName;
        this.pointDesc = pointDesc;
        this.lat = lat;
        this.lon = lon;
        this.sight = sight;
    }

    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName;
    }

    public String getPointDesc() {
        return pointDesc;
    }

    public void setPointDesc(String pointDesc) {
        this.pointDesc = pointDesc;
    }

    public boolean isSight() {
        return sight;
    }

    public void setSight(boolean sight) {
        this.sight = sight;
    }


}
