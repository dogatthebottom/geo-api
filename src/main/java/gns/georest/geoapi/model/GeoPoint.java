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
    private Double coord;     //Coordinates of geo point (not lat long, just Double)
    private boolean sight;    // Flag for determening if is it interesting place or not

    public String getHightlights() {
        return hightlights;
    }

    public void setHightlights(String hightlights) {
        this.hightlights = hightlights;
    }

    private String hightlights;



    @Override
    public String toString() {
        return "GeoPoint{" +
                "pointName='" + pointName + '\'' +
                ", pointDesc='" + pointDesc + '\'' +
                ", coord=" + coord +
                ", sight=" + sight +
                ", hightlights='" + hightlights + '\'' +
                '}';
    }

    public GeoPoint(){

    }
    public GeoPoint(String pointName, String pointDesc, Double coord, boolean sight) {
        this.pointName = pointName;
        this.pointDesc = pointDesc;
        this.coord = coord;
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

    public Double getCoord() {
        return coord;
    }

    public void setCoord(Double coord) {
        this.coord = coord;
    }

    public boolean isSight() {
        return sight;
    }

    public void setSight(boolean sight) {
        this.sight = sight;
    }





}
