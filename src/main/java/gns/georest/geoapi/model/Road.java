package gns.georest.geoapi.model;

/*
* Class Handler for roads
* */
public class Road {

    public Road(GeoPoint start, GeoPoint end, int distance) {
        this.start = start;
        this.end = end;
        this.distance = distance;
    }

    private GeoPoint start;
    private GeoPoint end;
    private int distance;

    public GeoPoint getStart() {
        return start;
    }

    public void setStart(GeoPoint start) {
        this.start = start;
    }

    public GeoPoint getEnd() {
        return end;
    }

    public void setEnd(GeoPoint end) {
        this.end = end;
    }


    public int getDistance() {
        return distance;
    }


    public void setDistance(int distance) {
        this.distance = distance;
    }
    @Override
    public String toString() {
        return "Road{" +
                "start=" + start +
                ", end=" + end +
                ", distance=" + distance +
                '}';
    }

}
