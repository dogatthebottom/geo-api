Spring Boot application
Find shortest distance and path using Diijkstra Algorithm.

#DESCRIPTION
This app builds geo-model contains random 7 geopoint and 24 roads 
geo-coordinates of each geopoint is just Double value

#BUILD


#RUN
java -jar _compiled jar file_ in target folder

#ENDPOINTS
`/geo` - test service page<br>
`/allpoints/{distance}**` - returns allpoints **distance** is parameter for search hightlights<br>
`/allroads` - returns all routes<br>
`/shortestDistance/{from}/{to}/{distance}` - returns shortest distance between **from** and **to** parameters in (A,B,C,D,E,F,G) 
`/shortestRoute/{from}/{to}/{distance}` - returns shortest path between **from** and **to** parameters in (A,B,C,D,E,F,G)   with searching list of hightlights for each point using parametr **distance**   

#USING
http://localhost:8080/geo/allpoints/10 returns all geopoints and list of hightlighs are nearer than 10 units(km or something else)
http://localhost:8080/geo/allroads  returns all the roads
http://localhost:8080/geo/shortestDistance/a/d returns shortest distance beetween A and D points
http://localhost:8080/geo/shortestRoute/a/g/12 returns shortest path beetween A and G points with highlights are nearer than 12 units(km or something else)