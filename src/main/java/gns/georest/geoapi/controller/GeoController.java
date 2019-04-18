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
    Random r = new Random();
    int rangeMin=1;
    int rangeMax=20;

    GeoPoint gpA = new GeoPoint("A", "Point A", rangeMin + (rangeMax - rangeMin) * r.nextDouble(),rangeMin + (rangeMax - rangeMin) * r.nextDouble(), r.nextBoolean());
    GeoPoint gpB = new GeoPoint("B", "Point B", rangeMin + (rangeMax - rangeMin) * r.nextDouble(),rangeMin + (rangeMax - rangeMin) * r.nextDouble(), r.nextBoolean());
    GeoPoint gpC = new GeoPoint("C", "Point C", rangeMin + (rangeMax - rangeMin) * r.nextDouble(),rangeMin + (rangeMax - rangeMin) * r.nextDouble(), r.nextBoolean());
    GeoPoint gpD = new GeoPoint("D", "Point D", rangeMin + (rangeMax - rangeMin) * r.nextDouble(),rangeMin + (rangeMax - rangeMin) * r.nextDouble(), r.nextBoolean());
    GeoPoint gpE = new GeoPoint("E", "Point E", rangeMin + (rangeMax - rangeMin) * r.nextDouble(),rangeMin + (rangeMax - rangeMin) * r.nextDouble(), r.nextBoolean());
    GeoPoint gpF = new GeoPoint("F", "Point F", rangeMin + (rangeMax - rangeMin) * r.nextDouble(),rangeMin + (rangeMax - rangeMin) * r.nextDouble(), r.nextBoolean());
    GeoPoint gpG = new GeoPoint("G", "Point G", rangeMin + (rangeMax - rangeMin) * r.nextDouble(),rangeMin + (rangeMax - rangeMin) * r.nextDouble(), r.nextBoolean());


    Road rAB = new Road(gpA, gpB, r.nextInt(20));
    Road rBA = new Road(gpB, gpA, r.nextInt(20));

    Road rAC = new Road(gpA, gpC, r.nextInt(20));
    Road rCA = new Road(gpC, gpA, r.nextInt(20));

    Road rAE = new Road(gpA, gpE, r.nextInt(20));
    Road rEA = new Road(gpE, gpA, r.nextInt(20));

    Road rBD = new Road(gpB, gpD, r.nextInt(20));
    Road rDB = new Road(gpD, gpB, r.nextInt(20));

    Road rBC = new Road(gpB, gpC, r.nextInt(20));
    Road rCB = new Road(gpC, gpB, r.nextInt(20));

    Road rCD = new Road(gpC, gpD, r.nextInt(20));
    Road rDC = new Road(gpD, gpC, r.nextInt(20));

    Road rCE = new Road(gpC, gpE, r.nextInt(20));
    Road rEC = new Road(gpE, gpC, r.nextInt(20));

    Road rDE = new Road(gpD, gpE, r.nextInt(20));
    Road rED = new Road(gpE, gpD, r.nextInt(20));

    Road rDF = new Road(gpD, gpF, r.nextInt(20));
    Road rFD = new Road(gpF, gpD, r.nextInt(20));

    Road rDG = new Road(gpD, gpG, r.nextInt(20));
    Road rGD = new Road(gpG, gpD, r.nextInt(20));

    Road rEG = new Road(gpE, gpG, r.nextInt(20));
    Road rGE = new Road(gpG, gpE, r.nextInt(20));

    Road rGF = new Road(gpG, gpF, r.nextInt(20));
    Road rFG = new Road(gpF, gpG, r.nextInt(20));

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

    @GetMapping(path="allpoints/{distance}")
    public List<GeoPoint> getAllPoints(@PathVariable int distance) {
        this.fillSights(distance);
        return geopoints;
    }

    @GetMapping("allroads")
    public List<Road> getAllRoads() {
        return roads;
    }

    @GetMapping(path = "shortestDistance/{from}/{to}")
    public Map<String,Integer> getShortestDistance(@PathVariable String from, @PathVariable String to){
      //  this.fillSights(distance);
        Map<String,Integer> shortDist= new HashMap<String, Integer>();
        Graph graph = new Graph(geopoints, roads);
        DijkstraAlgorithm da = new DijkstraAlgorithm(graph);
        da.execute(getStart(from));
        shortDist.put("Shortest Distance",da.getShortestDistance(getEnd(to)));

        return shortDist;
    }

    @GetMapping(path = "shortestRoute/{from}/{to}/{distance}")
    public LinkedList<GeoPoint> getShortestRoute(@PathVariable String from, @PathVariable String to,@PathVariable int distance) {
        this.fillSights(distance);
        Graph graph = new Graph(geopoints, roads);
        DijkstraAlgorithm da = new DijkstraAlgorithm(graph);
        da.execute(getStart(from));
        return da.getPath(getEnd(to));

    }

    @GetMapping(path="highlightsbypoint/{lat}/{lon}/{distance}")
    public List<GeoPoint> getHightlightsByPoint(@PathVariable Double lat,@PathVariable Double lon,@PathVariable int distance){
        List<GeoPoint> pointsbycoord =new ArrayList<GeoPoint>();
        for (GeoPoint gp : geopoints){
            if (gp.getLat()<=lat+distance && gp.getLon()<=lon+distance && gp.isSight()){
                pointsbycoord.add(gp);
            }
        }
        return pointsbycoord;
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


    private void fillSights(int distance){
       for (GeoPoint gp: geopoints){
           String hightlights="";
           for (Road rd : roads){
               if (rd.getStart().equals(gp) && rd.getDistance()<=distance && rd.getEnd().isSight()){
                   hightlights=hightlights+rd.getEnd().getPointName()+";";
               }
           }
          gp.setHightlights(hightlights);
       }

    }

}
