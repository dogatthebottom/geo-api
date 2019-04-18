Spring Boot application<br>
Find shortest distance and path using Diijkstra Algorithm.

#DESCRIPTION<br>
This app builds geo-model contains random 7 geopoint and 24 roads.<br>
Geo-coordinates of each geopoint is just pair of Double values<br><br>

#BUILD<br>
You can build app using Maven command<br><br>

**mvn package**<br><br>

#RUN<br>
java -jar _geo-api-0.0.1-SNAPSHOT.jar_ in target folder<br><br>

#ENDPOINTS<br>
`/geo` - test service page<br>
`/allpoints/{distance}` - returns allpoints **distance** is parameter for search hightlights<br>
`/allroads` - returns all routes<br>
`/shortestDistance/{from}/{to}/{distance}` - returns shortest distance between **from** and **to** parameters in (A,B,C,D,E,F,G)<br> 
`/shortestRoute/{from}/{to}/{distance}` - returns shortest path between **from** and **to** parameters in (A,B,C,D,E,F,G)   with searching list of hightlights for each point using parametr **distance**<br>
`/highlightsbypoint/{lat}/{lon}/{distance}` - returns the list of hightlights that are near random point<br><br>
#USING<br>
http://localhost:8080/geo/allpoints/10 returns all geopoints and list of hightlighs are nearer than 10 units(km or something else)<br>
http://localhost:8080/geo/allroads  returns all the roads<br>
http://localhost:8080/geo/shortestDistance/a/d returns shortest distance beetween A and D points<br>
http://localhost:8080/geo/shortestRoute/a/g/12 returns shortest path beetween A and G points with highlights are nearer than 12 units(km or something else)
http://localhost:8080/highlightsbypoint/2.5/4.6/7 returns the list of highligs that are nearer than 7 unit(km or something else) from point with coordinate 2.5 4.6