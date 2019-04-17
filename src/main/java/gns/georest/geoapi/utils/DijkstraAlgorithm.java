package gns.georest.geoapi.utils;

import gns.georest.geoapi.model.GeoPoint;
import gns.georest.geoapi.model.Graph;
import gns.georest.geoapi.model.Road;

import java.util.*;

public class DijkstraAlgorithm {

    private final List<GeoPoint> nodes;
    private final List<Road> edges;
    private Set<GeoPoint> settledNodes;
    private Set<GeoPoint> unSettledNodes;
    private Map<GeoPoint, GeoPoint> predecessors;
    private Map<GeoPoint, Integer> distance;

    public DijkstraAlgorithm(Graph graph) {
        // create a copy of the array so that we can operate on this array
        this.nodes = new ArrayList<GeoPoint>(graph.getGeopoints());
        this.edges = new ArrayList<Road>(graph.getRoads());
    }

    public void execute(GeoPoint source) {
        settledNodes = new HashSet<GeoPoint>();
        unSettledNodes = new HashSet<GeoPoint>();
        distance = new HashMap<GeoPoint, Integer>();
        predecessors = new HashMap<GeoPoint, GeoPoint>();
        distance.put(source, 0);
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            GeoPoint node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    private void findMinimalDistances(GeoPoint node) {
        List<GeoPoint> adjacentNodes = getNeighbors(node);
        for (GeoPoint target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node)
                    + getDistance(node, target)) {
                distance.put(target, getShortestDistance(node)
                        + getDistance(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }

    }

    private int getDistance(GeoPoint node, GeoPoint target) {
        for (Road road : edges) {
            if (road.getStart().equals(node) && road.getEnd().equals(target)){
                    return road.getDistance();
            }
        }
        throw new RuntimeException();
    }

    private List<GeoPoint> getNeighbors(GeoPoint node) {
        List<GeoPoint> neighbors = new ArrayList<GeoPoint>();
        for (Road edge : edges) {
            if (edge.getStart().equals(node)
                    && !isSettled(edge.getEnd())) {
                neighbors.add(edge.getEnd());
            }
        }
        return neighbors;
    }

    private GeoPoint getMinimum(Set<GeoPoint> geopoints) {
        GeoPoint minimum = null;
        for (GeoPoint geopoint : geopoints) {
            if (minimum == null) {
                minimum = geopoint;
            } else {
                if (getShortestDistance(geopoint) < getShortestDistance(minimum)) {
                    minimum = geopoint;
                }
            }
        }
        return minimum;
    }

    private boolean isSettled(GeoPoint geopoint) {
        return settledNodes.contains(geopoint);
    }

    public int getShortestDistance(GeoPoint destination) {
        Integer d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    /*
     * This method returns the path from the start geopoint to the end geopoint and
     * NULL if no path exists
     */
    public LinkedList<GeoPoint> getPath(GeoPoint target) {
        LinkedList<GeoPoint> path = new LinkedList<GeoPoint>();
        GeoPoint step = target;
        // check if a path exists
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        // Put it into the correct order
        Collections.reverse(path);
        return path;
    }

}
