package gns.georest.geoapi.model;

import java.util.List;

/*
 * Class Handler for List of geopoints and List of roads
 * */
public class Graph {

    private final List<GeoPoint> geopoints;
    private final List<Road> roads;

    public Graph(List<GeoPoint> geopoints, List<Road> roads) {
        this.geopoints = geopoints;
        this.roads = roads;
    }

    public List<GeoPoint> getGeopoints() {
        return geopoints;
    }

    public List<Road> getRoads() {
        return roads;
    }


}
