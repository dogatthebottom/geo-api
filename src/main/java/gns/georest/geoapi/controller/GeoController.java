package gns.georest.geoapi.controller;

import gns.georest.geoapi.model.GeoPoint;
import gns.georest.geoapi.model.Graph;
import gns.georest.geoapi.model.Road;
import gns.georest.geoapi.utils.DijkstraAlgorithm;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("geo")
public class GeoController {
    //Init and fill random model contains 7 geopoints and 24 roads
    Random generator = new Random();

    GeoPoint gpA = new GeoPoint("A", "Point A", generator.nextDouble(), generator.nextBoolean());
    GeoPoint gpB = new GeoPoint("B", "Point B", generator.nextDouble(), generator.nextBoolean());
    GeoPoint gpC = new GeoPoint("C", "Point C", generator.nextDouble(), generator.nextBoolean());
    GeoPoint gpD = new GeoPoint("D", "Point D", generator.nextDouble(), generator.nextBoolean());
    GeoPoint gpE = new GeoPoint("E", "Point E", generator.nextDouble(), generator.nextBoolean());
    GeoPoint gpF = new GeoPoint("F", "Point F", generator.nextDouble(), generator.nextBoolean());
    GeoPoint gpG = new GeoPoint("G", "Point G", generator.nextDouble(), generator.nextBoolean());


    Road rAB = new Road(gpA, gpB, generator.nextInt(20));
    Road rBA = new Road(gpB, gpA, generator.nextInt(20));

    Road rAC = new Road(gpA, gpC, generator.nextInt(20));
    Road rCA = new Road(gpC, gpA, generator.nextInt(20));

    Road rAE = new Road(gpA, gpE, generator.nextInt(20));
    Road rEA = new Road(gpE, gpA, generator.nextInt(20));

    Road rBD = new Road(gpB, gpD, generator.nextInt(20));
    Road rDB = new Road(gpD, gpB, generator.nextInt(20));

    Road rBC = new Road(gpB, gpC, generator.nextInt(20));
    Road rCB = new Road(gpC, gpB, generator.nextInt(20));

    Road rCD = new Road(gpC, gpD, generator.nextInt(20));
    Road rDC = new Road(gpD, gpC, generator.nextInt(20));

    Road rCE = new Road(gpC, gpE, generator.nextInt(20));
    Road rEC = new Road(gpE, gpC, generator.nextInt(20));

    Road rDE = new Road(gpD, gpE, generator.nextInt(20));
    Road rED = new Road(gpE, gpD, generator.nextInt(20));

    Road rDF = new Road(gpD, gpF, generator.nextInt(20));
    Road rFD = new Road(gpF, gpD, generator.nextInt(20));

    Road rDG = new Road(gpD, gpG, generator.nextInt(20));
    Road rGD = new Road(gpG, gpD, generator.nextInt(20));

    Road rEG = new Road(gpE, gpG, generator.nextInt(20));
    Road rGE = new Road(gpG, gpE, generator.nextInt(20));

    Road rGF = new Road(gpG, gpF, generator.nextInt(20));
    Road rFG = new Road(gpF, gpG, generator.nextInt(20));

    private List<GeoPoint> geopoints = new ArrayList<GeoPoint>() {{


        add(gpA);
        add(gpB);
        add(gpC);
        add(gpD);
        add(gpE);
        add(gpF);
        add(gpG);
    }};

    private List<Road> roads = new ArrayList<Road>() {{

        add(rAB);
        add(rBA);
        add(rAC);
        add(rCA);

        add(rAE);
        add(rEA);
        add(rBD);
        add(rDB);

        add(rBC);
        add(rCB);
        add(rCD);
        add(rDC);

        add(rCE);
        add(rEC);
        add(rDE);
        add(rED);

        add(rDF);
        add(rFD);
        add(rDG);
        add(rGD);

        add(rEG);
        add(rGE);
        add(rGF);
        add(rFG);


    }};

    @GetMapping
    public String defaultService() {

        return "Geo Rest Controller is Running";
    }

    @GetMapping("allpoints")
    public List<GeoPoint> getAllPoints() {
        return geopoints;
    }

    @GetMapping("allroads")
    public List<Road> getAllRoads() {
        return roads;
    }

    @GetMapping(path = "shortestDistance/{from}/{to}")
    public Map<String,Integer> getShortestDistance(@PathVariable String from, @PathVariable String to){
        Map<String,Integer> shortDist= new HashMap<String, Integer>();
        Graph graph = new Graph(geopoints, roads);
        DijkstraAlgorithm da = new DijkstraAlgorithm(graph);
        da.execute(getStart(from));
        shortDist.put("Short Distance",da.getShortestDistance(getEnd(to)));

        return shortDist;
    }

    @GetMapping(path = "shortestRoute/{from}/{to}")
    public LinkedList<GeoPoint> getShortestRoute(@PathVariable String from, @PathVariable String to) {
        Graph graph = new Graph(geopoints, roads);
        DijkstraAlgorithm da = new DijkstraAlgorithm(graph);
        da.execute(getStart(from));
        return da.getPath(getEnd(to));

    }


    private GeoPoint getStart(String from){
        GeoPoint start=null;
        for (GeoPoint gp: geopoints){
            if(from.equalsIgnoreCase(gp.getPointName())){
                start=gp;
                break;
            }
        }
        return start;


    }

    private GeoPoint getEnd(String to){
        GeoPoint end=null;
        for (GeoPoint gp: geopoints){
            if(to.equalsIgnoreCase(gp.getPointName())){
                end=gp;
                break;
            }
        }
        return end;


    }

}
